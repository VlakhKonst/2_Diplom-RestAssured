package site.nomoreparties.stellarburgers.util.generation;
import com.github.javafaker.Faker;
import site.nomoreparties.stellarburgers.user.login.models.Login;
import site.nomoreparties.stellarburgers.user.registration.models.request.RequestRegistration;
import site.nomoreparties.stellarburgers.user.update.models.request.Update;

public class GeneratorUserLogin {

    public static Login userLogin(RequestRegistration requestRegistration) {
        return new Login()
                .withEmail(requestRegistration.getEmail())
                .withPassword(requestRegistration.getPassword());
    }
    public static Login userLoginIncorrectMail(RequestRegistration requestRegistration) {
        Faker faker = new Faker();
        return new Login()
                .withEmail(faker.internet().emailAddress())
                .withPassword(requestRegistration.getPassword());
    }
    public static Login userLoginIncorrectPassword(RequestRegistration requestRegistration) {
        Faker faker = new Faker();
        return new Login()
                .withEmail(requestRegistration.getEmail())
                .withPassword(faker.letterify("1?2?3?4?5"));
    }

    public static Login userLoginAfterUpdate(Update update) {
        return new Login()
                .withEmail(update.getEmail())
                .withPassword(update.getPassword());
    }

}
