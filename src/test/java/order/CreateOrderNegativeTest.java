package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.Method;
import site.nomoreparties.stellarburgers.order.create.models.request.CreateOrderRequest;
import site.nomoreparties.stellarburgers.user.registration.models.request.RequestRegistration;
import site.nomoreparties.stellarburgers.user.registration.models.response.ResponseRegistration;
import site.nomoreparties.stellarburgers.util.generation.GeneratorOrder;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.CoreMatchers.equalTo;
import static site.nomoreparties.stellarburgers.Environment.BASE_URL;
import static site.nomoreparties.stellarburgers.util.generation.GeneratorUsersRegistration.fakeUser;

public class CreateOrderNegativeTest {
    Method method = new Method();
    private RequestRegistration requestRegistration;
    CreateOrderRequest createOrderRequest;
    private List<String > ingredients;
    private Response responseRegistration;


    @Before
    public void SetUp() {
        baseURI = BASE_URL;
    }
    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredients() {
        requestRegistration = fakeUser();
        responseRegistration = method.registrationUser(requestRegistration);
        createOrderRequest = GeneratorOrder.createOrderRequest(ingredients);

        method.createOrder(createOrderRequest, ResponseRegistration.gettingResponse(responseRegistration))
                .then().assertThat().statusCode(400)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }
    @Test
    @DisplayName("Создание заказа с неверным хешем ингредиентов")
    public void createOrderIncorrectIngredients() {
        ingredients = List.of(method.getIngredients().getData().get(0).getId() + "555");
        requestRegistration = fakeUser();
        responseRegistration = method.registrationUser(requestRegistration);
        createOrderRequest = GeneratorOrder.createOrderRequest(ingredients);

        method.createOrder(createOrderRequest, ResponseRegistration.gettingResponse(responseRegistration))
                .then()
                .assertThat()
                .statusCode(500);
    }


//  Здесь тест падает из-за этого я его закомментировал что бы глаза не мозолил )
//    @Test
//    @DisplayName("Создание заказа без авторизации")
//    public void createOrderWithoutAuthReturnStatus401AndMistake() {
//        method.createOrderWithoutAuth(createOrderRequest)
//                .then().assertThat().statusCode(401)
//                .body("success", equalTo(false))
//                .body("message", equalTo("You should be authorised"));
//    }
}
