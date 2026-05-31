package pt.psoft.g1.readers.readermanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pt.psoft.g1.readers.shared.model.EntityWithPhoto;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "READER_DETAILS_TEMP")
@Getter
@Setter
public class ReaderDetailsTemp extends EntityWithPhoto {

    @Id
    private String id;   // TEMP ID (same as temp readerId)

    @OneToOne
    @JoinColumn(
            name = "reader_temp_id",      // 🔴 explicit FK column
            referencedColumnName = "readerId" // PK of ReaderTemp
    )
    private ReaderTemp reader;   // TEMP Reader entity

    private ReaderNumber readerNumber;

    @Embedded
    private BirthDate birthDate;

    @Embedded
    private PhoneNumber phoneNumber;

    private boolean gdprConsent;
    private boolean marketingConsent;
    private boolean thirdPartySharingConsent;

    private List<String> interestList;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected ReaderDetailsTemp() {
        // for ORM only
    }

    public ReaderDetailsTemp(
            String id,
            ReaderTemp reader,
            ReaderNumber readerNumber,
            BirthDate birthDate,
            PhoneNumber phoneNumber,
            boolean gdprConsent,
            boolean marketingConsent,
            boolean thirdPartySharingConsent,
            String photoUri,
            List<String> interestList
    ) {
        this.id = id;
        this.reader = reader;
        this.readerNumber = readerNumber;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gdprConsent = gdprConsent;
        this.marketingConsent = marketingConsent;
        this.thirdPartySharingConsent = thirdPartySharingConsent;
        this.interestList = interestList;
        this.createdAt = LocalDateTime.now();

        setPhotoInternal(photoUri);
    }
}
