package pojoModels;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static constants.Routes.*;

public class User {

    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User() {
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    private String accessToken;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Step("Создание тестового пользователя")
    public void createUser(User newUser) {
        Response response;
        response = given()
                .header("Content-type", "application/json")
                .and()
                .body(newUser)
                .when()
                .post(USER_REGISTER_ROUTE);
        User createdUser = response.as(User.class);
        newUser.setAccessToken(createdUser.getAccessToken());
        System.out.println("Создан тестовый пользователь: " + newUser.getName());
    }

    @Step("Логин созданного тестового пользователя")
    public User loginUser(String name, String email, String password) {
        String json = "{\n    \"email\": \"" + email +"\", \n    \"password\": \""+ password +"\" \n}";
        Response response;
        response = given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(USER_LOGIN);
        User loginedUser = response.as(User.class);
        loginedUser.setName(name);
        if (loginedUser.getAccessToken() != null) {
            System.out.println("Авторизован пользователь: " + loginedUser.getName());
        }
        return loginedUser;
    }

    @Step("Удаление тестового пользователя")
    public void deleteUser(User createdUser) {
        if (createdUser == null) return;
        if (createdUser.getAccessToken() != null) {
            given()
                    .auth()
                    .oauth2(createdUser.getAccessToken()
                            .replace("Bearer ", ""))
                    .delete(USER_ROUTE)
                    .then()
                    .statusCode(202);
            System.out.println("Тестовый пользователь " + createdUser.getName() + " успешно удалён.\n");
        }
    }
}