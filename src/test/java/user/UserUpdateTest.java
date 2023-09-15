package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.user.delete.models.Delete;
import site.nomoreparties.stellarburgers.user.login.models.Login;
import site.nomoreparties.stellarburgers.user.registration.models.request.RequestRegistration;
import site.nomoreparties.stellarburgers.user.update.models.request.Update;
import site.nomoreparties.stellarburgers.Method;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static site.nomoreparties.stellarburgers.Environment.BASE_URL;
import static site.nomoreparties.stellarburgers.user.registration.models.response.ResponseRegistration.gettingResponse;
import static site.nomoreparties.stellarburgers.util.generation.GeneratorUserLogin.userLoginAfterUpdate;
import static site.nomoreparties.stellarburgers.util.generation.GeneratorUserUpdate.userUpdate;
import static site.nomoreparties.stellarburgers.util.generation.GeneratorUsersDelete.userDeleteAfterUpdate;
import static site.nomoreparties.stellarburgers.util.generation.GeneratorUsersRegistration.fakeUser;

public class UserUpdateTest {

    Method method = new Method();
    private RequestRegistration requestRegistration;
    private Delete delete;
    private Login login;
    private Update update;
    private Response responseRegistration;
    private Response responseLogin;
    private Response responseUpdate;

    @Before
    public void setUp() {
        baseURI = BASE_URL;
        requestRegistration = fakeUser();
        update = userUpdate();
        login = userLoginAfterUpdate(update);
        responseRegistration = method.registrationUser(requestRegistration);
        responseUpdate = method.updateUser(update,gettingResponse(responseRegistration));
    }
    @Test
    @DisplayName("Изменение данных пользователя и проверка статуса с телом ответа")
    public void updateUserReturnStatus200AndSuccessfulResponseBody() {
        responseUpdate.then().assertThat().statusCode(200);

        assertTrue("Параметр (success) != true", gettingResponse(responseUpdate).isSuccess());
        assertEquals("Параметр (user.email) не совпадает",update.getEmail(), gettingResponse(responseUpdate).getUser().getEmail());
        assertEquals("Параметр (user.name) не совпадает", requestRegistration.getName(), gettingResponse(responseUpdate).getUser().getName());
    }
    @Test
    @DisplayName("Изменение данных пользователя и проверка, что данные действительно поменялись")
    public void updateUserReturnStatus200AndLoginOfTheChangedUser() {
        responseLogin = method.loginUser(login);
        responseLogin.then().assertThat().statusCode(200);

        assertTrue("Параметр (success) != true", gettingResponse(responseLogin).isSuccess());
        assertEquals("Параметр (user.email) не совпадает",update.getEmail(), gettingResponse(responseLogin).getUser().getEmail());
        assertEquals("Параметр (user.name) не совпадает", requestRegistration.getName(), gettingResponse(responseLogin).getUser().getName());
    }
    @Test
    @DisplayName("Изменение данных без авторизации")
    public void updateUserWithoutAuthorizationReturnStatus401AndMistake() {
        responseLogin = method.loginUser(login);
        method.updateUserWithoutAuthorization(update).then().assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
    @After
    public void tearDown(){
        delete = userDeleteAfterUpdate(update);
        method.deleteUser(delete,gettingResponse(responseRegistration));
    }
}
