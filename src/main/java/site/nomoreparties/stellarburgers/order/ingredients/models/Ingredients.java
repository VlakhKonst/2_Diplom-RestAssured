package site.nomoreparties.stellarburgers.order.ingredients.models;

import java.util.List;

public class Ingredients {

    private boolean success;
    private List<Data> data;

    public boolean isSuccess() {
        return success;
    }

    public List<Data> getData() {
        return data;
    }
}
