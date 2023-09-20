package site.nomoreparties.stellarburgers.user.delete.models;

public class Delete {

    private String email;
    private String password;
    public Delete withEmail(String email) {
        this.email = email;
        return this;
    }
    public Delete withPassword(String password) {
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
