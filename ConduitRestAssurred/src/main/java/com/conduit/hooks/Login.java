package com.conduit.hooks;

import com.conduit.interactions.Post;
import com.conduit.models.DataUser;
import com.conduit.models.User;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.junit.Before;

public class Login implements Task {
    User user = new User();
    DataUser dataUser = new DataUser();
@Before
    @Override
    public <T extends Actor> void performAs(T actor) {
        user.setEmail("johanaandrea1000@gmail.com");
        user.setPassword("14Empanadas.");

        dataUser.setUser(user);
        actor.attemptsTo(
                Post.to("/users/login").with(requestSpecification -> requestSpecification
                        .contentType(ContentType.JSON)
                        .body(dataUser)));

    }
    public static  String TOKEN = SerenityRest.lastResponse().jsonPath().getString("token");

    public static Performable withInfo(){
        return Tasks.instrumented(Login.class);
    }
}
