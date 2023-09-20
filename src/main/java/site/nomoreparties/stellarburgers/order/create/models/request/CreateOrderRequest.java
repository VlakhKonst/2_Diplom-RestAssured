package site.nomoreparties.stellarburgers.order.create.models.request;

import java.util.List;

public class CreateOrderRequest {
    private List<String> ingredients;
    public CreateOrderRequest withIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }
    public List<String> getIngredients() {
        return ingredients;
    }
}
