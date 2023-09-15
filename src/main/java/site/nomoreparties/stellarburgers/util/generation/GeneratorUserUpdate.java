package site.nomoreparties.stellarburgers.util.generation;

import com.github.javafaker.Faker;
import site.nomoreparties.stellarburgers.user.update.models.request.Update;

public class GeneratorUserUpdate {

    public static Update userUpdate() {
        Faker faker = new Faker();
        return new Update()
                .withEmail(faker.internet().emailAddress())
                .withPassword(faker.letterify("1?2?3?4?5"));
    }

}
