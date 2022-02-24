package com.conduit.hooks;

import com.conduit.interactions.Post;
import com.conduit.models.DataUser;
import com.conduit.models.User;
import com.conduit.questions.StatusCode;
import io.cucumber.java.Before;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.*;

public class Login implements Task {

    User user;

    public Login(User user) {this.user = user;}

    DataUser dataUser = new DataUser();

    //String response;

    @Before
    public <T extends Actor> void performAs(T actor) {
        dataUser.setUser(user);
        actor.attemptsTo(
                Post.to("/users/login").with(requestSpecification -> requestSpecification
                        .contentType(ContentType.JSON)
                        .body(dataUser)));
    }
  //public static  String TOKEN =  SerenityRest.lastResponse().jsonPath().getString("user.token");
    public static Performable withInfo(User dataUsuario){
        return Tasks.instrumented(Login.class, dataUsuario);
    }}