package com.conduit.utils;

import net.serenitybdd.rest.SerenityRest;

public class TokenUsuario {
    public static  String TOKEN = SerenityRest.lastResponse().jsonPath().getString("token");
}
