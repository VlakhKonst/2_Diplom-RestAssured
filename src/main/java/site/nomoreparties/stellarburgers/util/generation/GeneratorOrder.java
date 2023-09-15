package site.nomoreparties.stellarburgers.util.generation;

import site.nomoreparties.stellarburgers.order.create.models.request.CreateOrderRequest;

import java.util.List;

public class GeneratorOrder {

    public static CreateOrderRequest createOrderRequest (List<String> ingredients) {
        return new CreateOrderRequest()
                .withIngredients(ingredients);
    }

}
