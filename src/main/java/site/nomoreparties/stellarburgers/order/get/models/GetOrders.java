package site.nomoreparties.stellarburgers.order.get.models;

import java.util.List;

public class GetOrders {

    private String success;
    private List<Orders> orders;
    private int total;
    private int totalToday;

    public String getSuccess() {
        return success;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalToday() {
        return totalToday;
    }
}
