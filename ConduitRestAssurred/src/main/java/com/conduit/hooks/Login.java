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

    User user;

    public Login(User user) {
        this.user = user;
    }

    DataUser dataUser = new DataUser();

String response;
    @Override
    public <T extends Actor> void performAs(T actor) {
        dataUser.setUser(user);
        actor.attemptsTo(
                Post.to("/users/login").with(requestSpecification -> requestSpecification
                        .contentType(ContentType.JSON)
                        .body(dataUser)));

    }
    //public static  String TOKEN = SerenityRest.lastResponse().jsonPath().getString("token");

    public static Performable withInfo(User dataUsuario){
        return Tasks.instrumented(Login.class, dataUsuario);
    }
}
