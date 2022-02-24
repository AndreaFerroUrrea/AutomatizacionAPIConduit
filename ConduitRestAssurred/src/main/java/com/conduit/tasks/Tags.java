package com.conduit.tasks;

import com.conduit.interactions.Get;
import com.conduit.interactions.Post;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static com.conduit.utils.Constants.TOKEN_USER;


public class Tags implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/tags")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .auth().oauth2(TOKEN_USER)
                        ));
    }
    public static Performable withInfo(){return Tasks.instrumented(Tags.class);}
}
