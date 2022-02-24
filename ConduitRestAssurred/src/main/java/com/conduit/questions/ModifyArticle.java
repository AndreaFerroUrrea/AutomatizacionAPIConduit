package com.conduit.questions;

import com.conduit.tasks.PutArticle;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Tasks;

public class ModifyArticle implements Question {

    @Override
    public Object answeredBy(Actor actor) {
        String response = SerenityRest.lastResponse().jsonPath().getString("article.description");
    return response;
    }
    public static ModifyArticle enter(){
        return new  ModifyArticle();
    }
}