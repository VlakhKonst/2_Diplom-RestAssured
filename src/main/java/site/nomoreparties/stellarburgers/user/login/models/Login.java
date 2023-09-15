package site.nomoreparties.stellarburgers.user.login.models;

public class Login {

    private String email;
    private String password;
    public Login withEmail(String email) {
        this.email = email;
        return this;
    }
    public Login withPassword(String password) {
        this.password = password;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
