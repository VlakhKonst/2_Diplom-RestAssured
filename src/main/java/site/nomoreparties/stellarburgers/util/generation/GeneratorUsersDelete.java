package site.nomoreparties.stellarburgers.util.generation;

import site.nomoreparties.stellarburgers.user.delete.models.Delete;
import site.nomoreparties.stellarburgers.user.registration.models.request.RequestRegistration;
import site.nomoreparties.stellarburgers.user.update.models.request.Update;

public class GeneratorUsersDelete {

    public static Delete userDelete(RequestRegistration requestRegistration) {
        return new Delete()
                .withEmail(requestRegistration.getEmail())
                .withPassword(requestRegistration.getPassword());
    }
    public static Delete userDeleteAfterUpdate(Update update) {
        return new Delete()
                .withEmail(update.getEmail())
                .withPassword(update.getPassword());
    }
}
