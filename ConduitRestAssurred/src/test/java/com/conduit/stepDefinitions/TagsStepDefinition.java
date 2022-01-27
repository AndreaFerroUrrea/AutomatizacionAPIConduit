package com.conduit.stepDefinitions;

import com.conduit.questions.StatusCode;
import com.conduit.tasks.Tags;
import com.conduit.utils.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static org.hamcrest.Matchers.equalTo;

public class TagsStepDefinition {
    private static final String restApiUrl = "https://api.realworld.io/api";
    Actor andrea = Actor.named("andrea");

    @Given("Andrea is a client that can get tags")
    public void andreaIsAClientThatCanGetTags() {
        andrea.whoCan(CallAnApi.at(restApiUrl));
    }


    @When("she send petition")
    public void sheSendPetition() {
        andrea.attemptsTo(Tags.withInfo());

    }

    @Then("she must see the tags articles")
    public void sheMustSeeTheTagsArticles() {
        andrea.should(GivenWhenThen.seeThat(Constants.MENSAJE,
                StatusCode.was(), equalTo(200)));
    }
}
