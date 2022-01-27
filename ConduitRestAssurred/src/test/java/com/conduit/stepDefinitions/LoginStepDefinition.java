package com.conduit.stepDefinitions;

import com.conduit.questions.StatusCode;
import com.conduit.hooks.Login;
import com.conduit.utils.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static org.hamcrest.Matchers.equalTo;

public class LoginStepDefinition {

    private static final String restApiUrl = "https://api.realworld.io/api";
Actor andrea = Actor.named("Andrea is actor");

    @Given("Andrea is a client that can login")
    public void andreaIsAClientThatCanLogin() {
        andrea.whoCan(CallAnApi.at(restApiUrl));
    }

    @When("she enters all the data correctly")
    public void sheEntersAllTheDataCorrectly() {
    andrea.attemptsTo(Login.withInfo());
    }

    @Then("she must make a correct record")
    public void sheMustMakeACorrectRecord() {
        andrea.should(GivenWhenThen.seeThat(Constants.MENSAJE,
               StatusCode.was(), equalTo(200)));
    }
}
