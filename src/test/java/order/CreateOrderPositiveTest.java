package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.Method;
import site.nomoreparties.stellarburgers.order.create.models.request.CreateOrderRequest;
import site.nomoreparties.stellarburgers.order.create.models.response.CreateOrderResponse;
import site.nomoreparties.stellarburgers.user.registration.models.request.RequestRegistration;
import site.nomoreparties.stellarburgers.user.registration.models.response.ResponseRegistration;
import site.nomoreparties.stellarburgers.util.generation.GeneratorOrder;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static org.junit.Assert.assertEquals;;
import static site.nomoreparties.stellarburgers.Environment.BASE_URL;

import static site.nomoreparties.stellarburgers.util.generation.GeneratorUsersRegistration.fakeUser;

public class CreateOrderPositiveTest {

    Method method = new Method();
    private RequestRegistration requestRegistration;
    CreateOrderRequest createOrderRequest;
    private List<String > ingredients;
    private Response responseRegistration;

    @Before
    public void setUp() {
        baseURI = BASE_URL;
        requestRegistration = fakeUser();
        responseRegistration = method.registrationUser(requestRegistration);
        ingredients = List.of(method.getIngredients().getData().get(0).getId());
        createOrderRequest = GeneratorOrder.createOrderRequest(ingredients);
    }
    @Test
    @DisplayName("Создание заказа с авторизацией")
    public void createOrderWithAuthReturnStatus200AndIdAreEqual() {
        Response response = method.createOrder(createOrderRequest, ResponseRegistration.gettingResponse(responseRegistration));
        response.then().assertThat().statusCode(200);

        String expectResult = ingredients.get(0);
        String actualResult = CreateOrderResponse.gettingResponse(response).getOrder().getIngredients().get(0).getId();

        assertEquals(expectResult, actualResult);
    }
    @Test
    @DisplayName("Создание заказа и получение этого заказа")
    public void getOrderReturnIdAreEqual() {
        method.createOrder(createOrderRequest, ResponseRegistration.gettingResponse(responseRegistration));

        String expectResult = ingredients.get(0);
        String actualResult = method.getOrders(ResponseRegistration.gettingResponse(responseRegistration)).getOrders().get(0).getIngredients().get(0);

        assertEquals(expectResult, actualResult);
    }
}
