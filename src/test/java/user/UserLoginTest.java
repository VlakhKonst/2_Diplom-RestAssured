package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.user.delete.models.Delete;
import site.nomoreparties.stellarburgers.user.login.models.Login;
import site.nomoreparties.stellarburgers.user.registration.models.request.RequestRegistration;
import site.nomoreparties.stellarburgers.Method;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static site.nomoreparties.stellarburgers.Environment.BASE_URL;
import static site.nomoreparties.stellarburgers.user.registration.models.response.ResponseRegistration.gettingResponse;
import static site.nomoreparties.stellarburgers.util.generation.GeneratorUserLogin.*;
import static site.nomoreparties.stellarburgers.util.generation.GeneratorUsersDelete.userDelete;
import static site.nomoreparties.stellarburgers.util.generation.GeneratorUsersRegistration.fakeUser;

public class UserLoginTest {
    Method method = new Method();
    private RequestRegistration requestRegistration;
    private Login login;
    private Delete delete;
    private Response response;

    @Before
    public void setUp() {
        baseURI = BASE_URL;
        requestRegistration = fakeUser();
        login = userLogin(requestRegistration);
        method.registrationUser(requestRegistration);
        response = method.loginUser(login);
    }
    @Test
    @DisplayName("Успешный вход в систему, проверка статус-кода и тела ответа")
    public void loginUserReturnStatus200AndSuccessfulResponse() {
        response.then().assertThat().statusCode(200);

        assertTrue("Параметр (success) != true", gettingResponse(response).isSuccess());
        assertEquals("Параметр (user.email) не совпадает", requestRegistration.getEmail(), gettingResponse(response).getUser().getEmail());
        assertEquals("Параметр (user.name) не совпадает", requestRegistration.getName(), gettingResponse(response).getUser().getName());

        assertTrue("Не верный токен", gettingResponse(response).getAccessToken().contains("Bearer"));
        assertFalse("Параметр (refreshToken) пуст", gettingResponse(response).getRefreshToken().isEmpty());
    }
    @Test
    @DisplayName("Вход с некорректным email-ом, проверка статус-кода и тела ответа")
    public void loginUserIncorrectEmailReturnStatus401AndMBodyMistake() {
        login = userLoginIncorrectMail(requestRegistration);
        method.loginUser(login)
                .then().statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
    @Test
    @DisplayName("Вход с некорректным паролем, проверка статус-кода и тела ответа")
    public void loginUserIncorrectPasswordReturnStatus401AndMBodyMistake() {
        login = userLoginIncorrectPassword(requestRegistration);
        method.loginUser(login)
                .then().statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
    @After
    public void tearDown() {
        delete = userDelete(requestRegistration);
        method.deleteUser(delete,gettingResponse(response));
    }
}
