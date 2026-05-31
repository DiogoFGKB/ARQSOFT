package pt.psoft.g1.readers.readermanagement.publishers;

public interface ReaderEventsPublisher {

    void publishReaderTempCreatedEvent(String payload);

    void publishReaderPersistedEvent(String payload);
}
