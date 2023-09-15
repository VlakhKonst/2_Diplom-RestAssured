package site.nomoreparties.stellarburgers.order.create.models.response;

import site.nomoreparties.stellarburgers.order.ingredients.models.Data;

import java.util.List;

public class Order {
    private List<Data> ingredients;
    private String _id;
    private Owner owner;
    private String status;
    private String name;
    private String createdAt;
    private String updatedAt;
    private int number;
    private int price;

    public List<Data> getIngredients() {
        return ingredients;
    }

    public String get_id() {
        return _id;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }
}
