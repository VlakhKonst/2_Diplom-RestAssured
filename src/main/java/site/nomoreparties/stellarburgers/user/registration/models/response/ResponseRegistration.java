package site.nomoreparties.stellarburgers.user.registration.models.response;

import com.google.gson.Gson;
import io.restassured.response.Response;

public class ResponseRegistration {
    private boolean success;
    private User user;
    private String accessToken;
    private String  refreshToken;

    public boolean isSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public static ResponseRegistration gettingResponse (Response response) {
        Gson gson = new Gson();
        return gson.fromJson(response.body().asString(), ResponseRegistration.class);
    }
}
