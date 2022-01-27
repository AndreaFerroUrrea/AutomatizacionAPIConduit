package com.conduit.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/tags.feature",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = "com.conduit.stepDefinitions",
        dryRun = false
)
public class TagsRunner {
}
