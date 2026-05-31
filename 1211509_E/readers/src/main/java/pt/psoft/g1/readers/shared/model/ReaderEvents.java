package pt.psoft.g1.readers.shared.model;

public interface ReaderEvents {

    // Saga step 1: Readers → Auth
    String TEMP_READER_CREATED = "TEMP_READER_CREATED";

    // Saga step 2: Auth → Readers
    String TEMP_READER_PERSISTED = "TEMP_READER_PERSISTED";

    // Final domain events
    String READER_CREATED = "READER_CREATED";
    String READER_UPDATED = "READER_UPDATED";
    String READER_DELETED = "READER_DELETED";
}
