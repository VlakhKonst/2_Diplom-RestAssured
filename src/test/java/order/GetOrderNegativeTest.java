package order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.Method;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.CoreMatchers.equalTo;
import static site.nomoreparties.stellarburgers.Environment.BASE_URL;

public class GetOrderNegativeTest {

//  Здесь решил расписать только негативный тест, т.к. получение заказа проверил уже в тесте на создание заказа..
//  Наверное нужно было и отдельно получение проверить, но в требованиях не говориться, а что конкретно проверить, по этому не стал дублировать тесты

    Method method = new Method();

    @Before
    public void setUp() {
        baseURI = BASE_URL;
    }
    @Test
    @DisplayName("Получение заказа без авторизации")
    public void getOrderReturnStatus401AndMistake() {
        method.getOrdersWithoutAuth()
                .then()
                .assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}
