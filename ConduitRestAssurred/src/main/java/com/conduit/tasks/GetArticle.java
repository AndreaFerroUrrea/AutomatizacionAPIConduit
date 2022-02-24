package com.conduit.tasks;

import com.conduit.interactions.Get;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static com.conduit.utils.Constants.TOKEN_USER;

public class GetArticle implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/articles?favorited=Andrea&limit=10&offset=0")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .auth()
                                .oauth2(TOKEN_USER))
        );
    }

    public static Performable withInfo() {
        return Tasks.instrumented(GetArticle.class);
    }
}
