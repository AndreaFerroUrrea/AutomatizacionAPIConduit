package com.conduit.tasks;

import com.conduit.interactions.Delete;
import com.gargoylesoftware.htmlunit.javascript.host.performance.Performance;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static com.conduit.utils.Constants.SLUG;
import static com.conduit.utils.Constants.TOKEN_USER;

public class DeleteArticle implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/articles/"+SLUG).with(requestSpecification -> requestSpecification.contentType(ContentType.JSON).auth().oauth2(TOKEN_USER))
        );
    }
    public static Performable withInfo(){return Tasks.instrumented(DeleteArticle.class);}
}
