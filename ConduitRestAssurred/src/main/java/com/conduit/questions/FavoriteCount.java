package com.conduit.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class FavoriteCount implements Question {
    @Override
    public Object answeredBy(Actor actor) {
            String response = SerenityRest.lastResponse().jsonPath().getString("favoritesCount");
        return response;
    }
    public static FavoriteCount enter(){return new FavoriteCount();}
}