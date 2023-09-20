package site.nomoreparties.stellarburgers.user.update.models.response;

import com.google.gson.Gson;
import io.restassured.response.Response;

public class UpdateResponse {
    private boolean success;
    private User user;
    public boolean isSuccess() {
        return success;
    }
    public User getUser() {
        return user;
    }
    public static UpdateResponse gettingResponse (Response response) {
        Gson gson = new Gson();
        return gson.fromJson(response.body().asString(), UpdateResponse.class);
    }
}