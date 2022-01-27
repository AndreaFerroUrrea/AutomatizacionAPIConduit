package com.conduit.tasks;

import com.conduit.interactions.Put;
import com.conduit.models.Data;
import com.conduit.models.Article;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static com.conduit.utils.Constants.SLUG;
import static com.conduit.utils.Constants.TOKEN_USER;

public class PutArticle implements Task {
Data dataArticle = new Data();
Article article = new Article();

    @Override
    public <T extends Actor> void performAs(T actor) {
        dataArticle.setDescription("verde");
        article.setArticle(dataArticle);

        actor.attemptsTo(
                Put.to("/articles/"+SLUG).with(requestSpecification -> requestSpecification.contentType(ContentType.JSON).auth().oauth2(TOKEN_USER).body(article)
                        //.body(article)
                        )
        );
    }
    public static Performable withInfo(){
        return Tasks.instrumented(PutArticle.class);
    }
}
