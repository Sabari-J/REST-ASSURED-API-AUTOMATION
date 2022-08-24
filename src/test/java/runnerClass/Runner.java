package runnerClass;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		features = "src/test/java/featurefiles/PlaceValidation.feature", 
		glue = "stepDefinitions"

)
public class Runner extends AbstractTestNGCucumberTests{

}
