package pt.psoft.g1.users.usermanagement.publishers;

public interface UserEventsPublisher {

    void publishTempUserCreated(String payload);
}
