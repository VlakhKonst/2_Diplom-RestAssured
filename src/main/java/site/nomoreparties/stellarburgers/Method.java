package site.nomoreparties.stellarburgers;

import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.order.create.models.request.CreateOrderRequest;
import site.nomoreparties.stellarburgers.order.get.models.GetOrders;
import site.nomoreparties.stellarburgers.order.ingredients.models.Ingredients;
import site.nomoreparties.stellarburgers.user.delete.models.Delete;
import site.nomoreparties.stellarburgers.user.login.models.Login;
import site.nomoreparties.stellarburgers.user.registration.models.request.RequestRegistration;
import site.nomoreparties.stellarburgers.user.registration.models.response.ResponseRegistration;
import site.nomoreparties.stellarburgers.user.update.models.request.Update;

import static io.restassured.RestAssured.given;
import static site.nomoreparties.stellarburgers.Environment.*;

public class Method {

    public Response registrationUser(RequestRegistration requestRegistration) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(requestRegistration)
                .when()
                .post(URL_REGISTRATION);
    }

    public Response deleteUser(Delete delete, ResponseRegistration responseRegistration) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", responseRegistration.getAccessToken())
                .and()
                .body(delete)
                .when()
                .delete(URL_DELETE);
    }

    public Response loginUser(Login login) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(login)
                .when()
                .post(URL_LOGIN);
    }
    public Response updateUser(Update update, ResponseRegistration responseRegistration) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", responseRegistration.getAccessToken())
                .and()
                .body(update)
                .when()
                .patch(URL_UPDATE);
    }
    public Response updateUserWithoutAuthorization(Update update) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(update)
                .when()
                .patch(URL_UPDATE);
    }
    public Ingredients getIngredients() {
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .get(URL_INGREDIENTS)
                .body()
                .as(Ingredients.class);
    }

    public Response createOrder(CreateOrderRequest createOrderRequest, ResponseRegistration responseRegistration) {
        return given()
                .header("Authorization", responseRegistration.getAccessToken())
                .header("Content-type", "application/json")
                .and()
                .body(createOrderRequest)
                .when()
                .post(URL_ORDERS);
    }
    public Response createOrderWithoutAuth(CreateOrderRequest createOrderRequest) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createOrderRequest)
                .when()
                .post(URL_ORDERS);
    }

    public GetOrders getOrders(ResponseRegistration responseRegistration) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", responseRegistration.getAccessToken())
                .and()
                .when()
                .get(URL_ORDERS)
                .body()
                .as(GetOrders.class);
    }
    public Response getOrdersWithoutAuth() {
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .get(URL_ORDERS);
    }
}
