package pt.psoft.g1.readers.readermanagement.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pt.psoft.g1.readers.exceptions.ConflictException;
import pt.psoft.g1.readers.exceptions.NotFoundException;
import pt.psoft.g1.readers.readermanagement.dto.UserDTO;
import pt.psoft.g1.readers.readermanagement.model.*;
import pt.psoft.g1.readers.readermanagement.repositories.*;
import pt.psoft.g1.readers.shared.model.ReaderEvents;
import pt.psoft.g1.readers.shared.repositories.ForbiddenNameRepository;
import pt.psoft.g1.readers.shared.repositories.PhotoRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {
    private final ReaderDetailsRepository readerDetailsRepository;

    private final ReaderDetailsTempRepository readerDetailsTempRepository;

    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;
    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;
    private final ReaderTempRepository readerTempRepo;
    private final ForbiddenNameRepository forbiddenNameRepository;
    private final PhotoRepository photoRepository;


    @Override
    public ReaderDetails create(CreateReaderRequest request, String photoURI) {

        // 1. Check if username already exists in final or temp tables
        if (readerRepository.findByEmail(request.getUsername()).isPresent() ||
                readerTempRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new ConflictException("Reader with username " + request.getUsername() + " already exists");
        }

        // 2. Forbidden name validation
        for (String word : request.getFullName().split("\\s+")) {
            if (!forbiddenNameRepository.findByForbiddenNameIsContained(word).isEmpty()) {
                throw new IllegalArgumentException("Name contains a forbidden word");
            }
        }

        // 3. Photo consistency rule
        MultipartFile photo = request.getPhoto();
        if ((photo == null && photoURI != null) || (photo != null && photoURI == null)) {
            request.setPhoto(null);
        }

        // 4. Create temporary Reader
        ReaderTemp readerTemp = new ReaderTemp(
                UUID.randomUUID().toString(),
                "",
                request.getFullName(),
                request.getUsername()
        );

        readerTempRepo.save(readerTemp);

        Reader reader=new Reader(readerTemp.getReaderId(),"",readerTemp.getName(), readerTemp.getEmail());

        // 5. Create temporary ReaderDetails
        ReaderDetailsTemp tempDetails = readerMapper.createReaderDetailsTemp(
                readerTemp,
                request,
                photoURI
        );

        ReaderDetails Details = readerMapper.createReaderDetails(
                0,
                reader,
                request,
                photoURI,
                request.getInterestList()
        );
        Details.setId(UUID.randomUUID().toString());

        tempDetails.setId(UUID.randomUUID().toString());

        ReaderDetailsTemp savedTempDetails = readerDetailsTempRepository.save(tempDetails);

        // 6. Create OutboxEvent(TEMP_READER_CREATED)
        try {
            UserDTO dto = new UserDTO(
                    "",
                    readerTemp.getReaderId(),
                    request.getUsername(),
                    request.getPassword(),
                    request.getFullName(),
                    0L
            );

            String payload = objectMapper.writeValueAsString(dto);

            OutboxEvent event = new OutboxEvent(
                    readerTemp.getReaderId(),
                    ReaderEvents.TEMP_READER_CREATED,
                    payload
            );

            outboxEventRepository.save(event);

        } catch (Exception e) {
            throw new RuntimeException("Error saving Outbox event", e);
        }

        // 7. Return temporary ReaderDetails
        return Details;
    }



    @Override
    public ReaderDetails update(String username, final UpdateReaderRequest request, final long desiredVersion, String photoURI){
        final ReaderDetails readerDetails = readerDetailsRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cannot find reader"));

        List<String> stringInterestList = request.getInterestList();

        MultipartFile photo = request.getPhoto();
        if(photo == null && photoURI != null || photo != null && photoURI == null) {
            request.setPhoto(null);
        }

        readerDetails.applyPatch(desiredVersion, request, photoURI, stringInterestList);
        return readerDetailsRepository.save(readerDetails);
    }


    @Override
    public Optional<ReaderDetails> findByReaderNumber(String readerNumber) {
        return this.readerDetailsRepository.findByReaderNumber(readerNumber);
    }

    @Override
    public List<ReaderDetails> findByPhoneNumber(String phoneNumber) {
        return this.readerDetailsRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<ReaderDetails> findByUsername(final String username) {
        return this.readerDetailsRepository.findByUsername(username);
    }


    @Override
    public Iterable<ReaderDetails> findAll() {
        return this.readerDetailsRepository.findAll();
    }

    @Override
    public Optional<ReaderDetails> removeReaderPhoto(String readerNumber, long desiredVersion) {
        ReaderDetails readerDetails = readerDetailsRepository.findByReaderNumber(readerNumber)
                .orElseThrow(() -> new NotFoundException("Cannot find reader"));

        String photoFile = readerDetails.getPhoto().getPhotoFile();
        readerDetails.removePhoto(desiredVersion);
        Optional<ReaderDetails> updatedReader = Optional.of(readerDetailsRepository.save(readerDetails));
        photoRepository.deleteByPhotoFile(photoFile);
        return updatedReader;
    }
    @Override
    @Transactional
    public void persistTemporary(UserDTO dto) {

        System.out.println(dto.getId() + dto.getUsername());

        String readerId = dto.getReaderId();

        // 1. Load ReaderTemp
        ReaderTemp temp = readerTempRepo.findByReaderId(readerId)
                .orElseThrow(() -> new IllegalStateException("Temp reader not found: " + readerId));

        temp.setUserId(dto.getId());

        Reader reader=new Reader(temp.getReaderId(),dto.getId(), temp.getName(), temp.getEmail());

        try {
            readerRepository.save(reader);
        } catch (Exception e) {
            System.err.println("Error saving Reader: " + e.getMessage());
            e.printStackTrace();
        }
        Reader saved = readerRepository.findByEmail(reader.getEmail())
                .orElseThrow(() -> new IllegalStateException("Reader was not saved!"));

        System.out.println("Saved reader: " + saved.getReaderId() + ", name=" + saved.getName());

        // 2. Load ReaderDetailsTemp
        ReaderDetailsTemp detailsTemp = readerDetailsTempRepository.findByReaderId(readerId)
                .orElseThrow(() -> new IllegalStateException("Temp reader details not found: " + readerId));


        // Set the correct ID
        detailsTemp.setReader(temp);

        int count = readerDetailsRepository.getCountFromCurrentYear();
        detailsTemp.setReaderNumber(new ReaderNumber(count+1));

        ReaderDetails readerDetails = new ReaderDetails( count+1, reader, detailsTemp.getBirthDate().getBirthDate().toString(), detailsTemp.getPhoneNumber().toString(), detailsTemp.isGdprConsent(), detailsTemp.isMarketingConsent(), detailsTemp.isThirdPartySharingConsent(), detailsTemp.getPhoto() != null ? detailsTemp.getPhoto().getPhotoFile() : null, detailsTemp.getInterestList() );
        readerDetails.setId(readerId);

        // 4. Save final entity
        readerDetailsRepository.save(readerDetails);
        System.out.println("[x] FINAL ReaderDetails persisted with ID=" + readerDetails.getId());
        ReaderDetails fetchedReaderDetails = readerDetailsRepository.findByUsername(readerDetails.getReader().getEmail())
                .orElseThrow(() -> new IllegalStateException("Saved readerDetails not found!"));
        System.out.println("[v] Fetched ReaderDetails: ID=" + fetchedReaderDetails.getId() +
                ", Phone=" + fetchedReaderDetails.getPhoneNumber() +
                ", Interests=" + fetchedReaderDetails.getInterestList());

        // 5. Delete temp entities
        readerTempRepo.deleteById(temp.getUserId());
        readerDetailsTempRepository.deleteById(detailsTemp.getId());

        OutboxEvent event = new OutboxEvent(
                dto.getId(),
                ReaderEvents.TEMP_READER_PERSISTED,
                dto.getId()
        );

        outboxEventRepository.save(event);
    }
}
