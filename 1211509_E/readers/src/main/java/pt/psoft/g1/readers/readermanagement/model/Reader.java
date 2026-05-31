package pt.psoft.g1.readers.readermanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "readers")
public class Reader
{
    @Id
    private String readerId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    protected Reader() {
        // for ORM only
    }

    public Reader(String readerId, String userId, String name, String email) {
        this.readerId = readerId;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getReaderId() { return readerId; }
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}