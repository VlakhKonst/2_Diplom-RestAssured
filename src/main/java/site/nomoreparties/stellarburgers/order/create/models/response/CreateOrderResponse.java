package site.nomoreparties.stellarburgers.order.create.models.response;

import com.google.gson.Gson;
import io.restassured.response.Response;

public class CreateOrderResponse {
    private boolean success;
    private String name;
    private Order order;

    public boolean isSuccess() {
        return success;
    }

    public String getName() {
        return name;
    }

    public Order getOrder() {
        return order;
    }

    public static CreateOrderResponse gettingResponse (Response response) {
        Gson gson = new Gson();
        return gson.fromJson(response.body().asString(), CreateOrderResponse.class);
    }
}
