package site.nomoreparties.stellarburgers.user.registration.models.request;

public class RequestRegistration {

    private String email;
    private String password;
    private String name;
    public RequestRegistration withEmail(String email) {
        this.email = email;
        return this;
    }
    public RequestRegistration withPassword(String password) {
        this.password = password;
        return this;
    }
    public RequestRegistration withName(String name) {
        this.name = name;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
}
