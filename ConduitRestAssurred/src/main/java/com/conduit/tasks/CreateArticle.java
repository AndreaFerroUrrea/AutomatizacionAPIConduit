package com.conduit.tasks;

import com.conduit.interactions.Post;
import com.conduit.models.Article;
import com.conduit.models.DataArticles;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.Map;

import static com.conduit.utils.Constants.TOKEN_USER;


public class CreateArticle implements Task {
    Article article = new Article();
    DataArticles dataArticles = new DataArticles();


    private final Map<String,String>dataArticle;
    public CreateArticle(Map<String, String> dataArticle) {
        this.dataArticle = dataArticle;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
       // dataArticles.setTagList(Collections.singletonList("fruta"));
        dataArticles.setTitle(dataArticle.get("title"));
        dataArticles.setDescription(dataArticle.get("description"));
        dataArticles.setBody(dataArticle.get("body"));


        article.setArticle(dataArticles);

        actor.attemptsTo(
                Post.to("/articles/")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .auth()
                                .oauth2(TOKEN_USER)
                                .body(article)
                        )
        );
    }
    public static Performable withInfo(Map<String,String> dataArticle){
        return Tasks.instrumented(CreateArticle.class,dataArticle);
    }
}
