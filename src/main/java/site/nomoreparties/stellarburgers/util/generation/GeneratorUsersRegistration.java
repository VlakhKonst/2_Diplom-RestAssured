package site.nomoreparties.stellarburgers.util.generation;

import com.github.javafaker.Faker;
import site.nomoreparties.stellarburgers.user.registration.models.request.RequestRegistration;

public class GeneratorUsersRegistration {

    public static RequestRegistration fakeUser() {
        Faker faker = new Faker();
        return new RequestRegistration()
                .withEmail(faker.internet().emailAddress())
                .withPassword(faker.letterify("1?2?3?4?5"))
                .withName(faker.name().firstName());
    }

    public static RequestRegistration fakeUserWithoutEmail() {
        Faker faker = new Faker();
        return new RequestRegistration()
                .withEmail("")
                .withPassword(faker.letterify("1?2?3?4?5"))
                .withName(faker.name().firstName());
    }
    public static RequestRegistration fakeUserWithoutName() {
        Faker faker = new Faker();
        return new RequestRegistration()
                .withEmail(faker.internet().emailAddress())
                .withPassword(faker.letterify("1?2?3?4?5"))
                .withName("");
    }
}
