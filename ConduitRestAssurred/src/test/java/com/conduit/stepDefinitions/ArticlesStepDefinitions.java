package com.conduit.stepDefinitions;

import com.conduit.questions.FavoriteCount;
import com.conduit.questions.ModifyArticle;
import com.conduit.questions.StatusCode;
import com.conduit.tasks.CreateArticle;
import com.conduit.tasks.DeleteArticle;
import com.conduit.tasks.GetArticle;
import com.conduit.tasks.PutArticle;
import com.conduit.utils.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class ArticlesStepDefinitions {

    private static final String restApiUrl = "https://api.realworld.io/api";
    Actor andrea = Actor.named("andrea is the actor");



    //////////////////////////////////////
    //Get
    @Given("Andrea need look the articles")
    public void andreaNeedLookTheArticles() {andrea.whoCan(CallAnApi.at(restApiUrl));}
    @When("she sends the correct request for the get request")
    public void sheSendsTheCorrectRequestForTheGetRequest() {
        andrea.attemptsTo(GetArticle.withInfo());
    }
    @Then("she must see the articles")
    public void sheMustSeeTheArticles() {andrea.should(GivenWhenThen.seeThat(Constants.MENSAJE,
                StatusCode.was(), equalTo(200)));
    }
    //////////////////////////////////////////////////////////////////////////////////////
    //Post
    @Given("Andrea needs to submit an article")
    public void andreaNeedsToSubmitAnArticle() {
         andrea.whoCan(CallAnApi.at(restApiUrl));
    }

    @When("she sends the correct fields")
    public void sheSendsTheCorrectFields(Map<String,String> dataArticle) {
        andrea.attemptsTo(CreateArticle.withInfo(dataArticle));
    }

    @Then("she must create the article correctly")
    public void sheMustCreateTheArticleCorrectly() {
       andrea.should(GivenWhenThen.seeThat("COMPARAR", FavoriteCount.enter(),equalTo(null)));

    }
    /////////////////////////////////////////////////////////////////////////////////////
    //Put
    @Given("Andrea need to update")
    public void andreaNeedToUpdate() {
        andrea.whoCan(CallAnApi.at(restApiUrl));
    }
    @When("she sends the correct information")
    public void sheSendsTheCorrectInformation() {
        andrea.attemptsTo(PutArticle.withInfo());
    }
    @Then("she must update the article correctly")
    public void sheMustUpdateTheArticleCorrectly() {
        andrea.should(GivenWhenThen.seeThat("COMPARACION ", ModifyArticle.enter(),equalTo("PURPURA")));
    }


    //////////////////////////////////////
    //Delete
    @Given("Andrea need delete the article")
    public void andreaNeedDeleteTheArticle() {
        andrea.whoCan(CallAnApi.at(restApiUrl));
    }
    @When("she sends the correct petition")
    public void sheSendsTheCorrectPetition() {
        andrea.attemptsTo(DeleteArticle.withInfo());
    }
    @Then("she must delete the article correctly")
    public void sheMustDeleteTheArticleCorrectly() {
        andrea.should(GivenWhenThen.seeThat(StatusCode.was(),equalTo(204)));
    }
}