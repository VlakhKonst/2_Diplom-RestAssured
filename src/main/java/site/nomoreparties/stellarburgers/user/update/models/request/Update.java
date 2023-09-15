package site.nomoreparties.stellarburgers.user.update.models.request;
public class Update {

    private String email;
    private String password;
    public Update withEmail(String email) {
        this.email = email;
        return this;
    }
    public Update withPassword(String password) {
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
