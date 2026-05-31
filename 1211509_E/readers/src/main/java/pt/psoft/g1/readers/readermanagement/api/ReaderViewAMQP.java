package pt.psoft.g1.readers.readermanagement.api;
import lombok.*;


import java.util.List;

@Data
public class ReaderViewAMQP {
    private String email;
    private String fullName;
    private String birthDate;
    private String phoneNumber;
    private String photo;
    private boolean gdprConsent;
    private boolean marketingConsent;
    private boolean thirdPartySharingConsent;
    private List<String> interestList;
}
