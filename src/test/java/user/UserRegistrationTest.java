package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.user.delete.models.Delete;
import site.nomoreparties.stellarburgers.user.registration.models.request.RequestRegistration;
import site.nomoreparties.stellarburgers.Method;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static site.nomoreparties.stellarburgers.Environment.BASE_URL;
import static site.nomoreparties.stellarburgers.user.registration.models.response.ResponseRegistration.gettingResponse;
import static site.nomoreparties.stellarburgers.util.generation.GeneratorUsersDelete.userDelete;
import static site.nomoreparties.stellarburgers.util.generation.GeneratorUsersRegistration.*;


public class UserRegistrationTest {
    Method method = new Method();
    private RequestRegistration requestRegistration;
    private Delete delete;
    private Response response;

    @Before
    public void setUp() {
        baseURI = BASE_URL;
        requestRegistration = fakeUser();
        response = method.registrationUser(requestRegistration);
    }

    @Test
    @DisplayName("Регистрация пользователя c проверкой статус-кода и тела ответа")
    public void registrationUserReturnStatus200AndSuccessfulResponse() {
        response.then().assertThat().statusCode(200);

        assertTrue("Параметр (success) != true", gettingResponse(response).isSuccess());
        assertEquals("Параметр (user.email) не совпадает", requestRegistration.getEmail(), gettingResponse(response).getUser().getEmail());
        assertEquals("Параметр (user.name) не совпадает", requestRegistration.getName(), gettingResponse(response).getUser().getName());

        assertTrue("Не верный токен", gettingResponse(response).getAccessToken().contains("Bearer"));
        assertFalse("Параметр (refreshToken) пуст", gettingResponse(response).getRefreshToken().isEmpty());
    }

    @Test
    @DisplayName("Регистрация зарегистрированного пользователя, проверка статус-кода и тела ответа")
    public void registeredUserReturnStatus403AndBodyMistake() {
        method.registrationUser(requestRegistration).then().assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }
    @Test
    @DisplayName("Регистрация без обязательного параметра (email), проверка статус-кода и тела ответа")
    public void noRequiredFieldEmailReturnStatus401AndBodyMistake() {
        requestRegistration = fakeUserWithoutEmail();
        method.registrationUser(requestRegistration).then().assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }
    @Test
    @DisplayName("Регистрация без обязательного параметра (name), проверка статус-кода и тела ответа")
    public void noRequiredFieldNameReturnStatus403AndBodyMistake() {
        requestRegistration = fakeUserWithoutName();
        method.registrationUser(requestRegistration).then().assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }
    @After
    public void tearDown() {
        delete = userDelete(requestRegistration);
        method.deleteUser(delete,gettingResponse(response));
    }
}
