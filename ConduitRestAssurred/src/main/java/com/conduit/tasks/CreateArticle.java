package com.conduit.tasks;

import com.conduit.interactions.Post;
import com.conduit.models.Data;
import com.conduit.models.Article;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.Collections;

import static com.conduit.utils.Constants.TOKEN_USER;


public class CreateArticle implements Task {
    Data data;

  //  public CreateArticle(Data data) {
   //     this.data = data;
    //}

    Data dataArticle = new Data();
    Article article = new Article();

    @Override
    public <T extends Actor> void performAs(T actor) {
        data.setTitle(data.getTitle());
        data.setBody(data.getBody());
        data.setDescription(data.getDescription());

        data.setTagList(Collections.singletonList("Fruta"));
        article.setArticle(data);

        actor.attemptsTo(
                Post.to("/articles/")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .auth()
                                .oauth2(TOKEN_USER).body(article)
                        )
        );
    }
    public static Performable withInfo(Data data){
        return Tasks.instrumented(CreateArticle.class,data);
    }
}
