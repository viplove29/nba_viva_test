package serenitybase.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    //    tags = "@TC-900",
    features = {"src/test/resources/features/reports/nbaHomepage"},
    glue = {"serenitybase.steps"},
    plugin = "rerun:target/rerun.txt")
public class TestSmoke {}
