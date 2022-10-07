package rs.raf.demo.entities;

import org.apache.commons.codec.digest.DigestUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

    private int id;

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String email;

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String name;

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String surname;

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String userType;

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String status;

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String password;

    public User() {
    }

    public User(int id, String email, String name, String surname, String userType, String status, String password) {
        this();
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.userType = userType;
        this.status = status;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String hashPassword() {
        String sha256hex = DigestUtils.sha256Hex(this.password);
        return sha256hex;
    }
}
