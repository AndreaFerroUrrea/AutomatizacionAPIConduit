package com.conduit.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ArticlesCount implements Question {
    @Override
    public Object answeredBy(Actor actor) {
            String response = SerenityRest.lastResponse().jsonPath().getString("favoritesCount");
        return response;
    }
    public static ArticlesCount enter(){return new ArticlesCount();}
}