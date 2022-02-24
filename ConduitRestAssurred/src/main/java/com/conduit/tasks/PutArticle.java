package com.conduit.tasks;

import com.conduit.interactions.Put;
import com.conduit.models.DataArticles;
import com.conduit.models.Article;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static com.conduit.utils.Constants.*;

public class PutArticle implements Task {
DataArticles dataArticle = new DataArticles();
Article article = new Article();

    @Override
    public <T extends Actor> void performAs(T actor) {
        dataArticle.setDescription("PURPURA");
        article.setArticle(dataArticle);

        actor.attemptsTo(
                Put.to("/articles/"+SLUG_EDIT)
                        .with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                                        .auth().oauth2(TOKEN_USER)
                                .body(article)
                        )
        );
    }
    public static Performable withInfo(){
        return Tasks.instrumented(PutArticle.class);
    }
}
