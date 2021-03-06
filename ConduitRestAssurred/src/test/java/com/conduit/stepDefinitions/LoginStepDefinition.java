package com.conduit.stepDefinitions;

import com.conduit.models.User;
import com.conduit.questions.StatusCode;
import com.conduit.hooks.Login;
import com.conduit.utils.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static com.conduit.utils.Constants.TOKEN_USER;
import static org.hamcrest.Matchers.equalTo;

public class LoginStepDefinition {

    private static final String restApiUrl = "https://api.realworld.io/api";
Actor andrea = Actor.named("Andrea is actor");

    @Given("Andrea is a client that can login")
    public void andreaIsAClientThatCanLogin() {
        andrea.whoCan(CallAnApi.at(restApiUrl));
    }

    @When("she enters the data {string} y  {string} correctly")
    public void sheEntersTheDataYCorrectly(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        andrea.attemptsTo(Login.withInfo(user));

        System.out.println(TOKEN_USER);
    }
    @Then("she must make a correct record")
    public void sheMustMakeACorrectRecord() {
        andrea.should(GivenWhenThen.seeThat(Constants.MENSAJE,
               StatusCode.was(), equalTo(200)));
    }
}
