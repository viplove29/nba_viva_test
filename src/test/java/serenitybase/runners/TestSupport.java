package serenitybase.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = {"src/test/resources/features"},
    glue = {"serenitybase.steps"},
    plugin = "rerun:target/rerun.txt")
public class TestSupport {}
