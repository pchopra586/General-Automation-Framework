package com.sph.runners;

import com.sph.listeners.ExtentProperties;
import com.sph.listeners.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

import org.junit.runner.RunWith;

import org.testng.ITestContext;
import org.testng.annotations.*;


import java.io.File;

import static com.sph.driverFactory.LocalWebDriverListener.browserName;

/**
 * Please notice that cucumber.examples.java.testNG.stepDefinitions.BeforeAfterHooks class
 * is in the same package as the steps definitions.
 * It has two methods that are executed before or after scenario.
 * I'm using it to delete cookies and take a screenshot if scenario fails.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/mobile/iphone",
        glue = {"com.sph.stepDefinitions.mobile"},
        format = {"pretty","rerun:target/cucumber-reports/rerun.txt",
                "html:target/cucumber-report/android/cucumber-pretty",
                "json:target/cucumber-report/android/cucumber.json",
                "junit:target/cucumber-report/android/cucumber.xml"},
        plugin = {"com.sph.listeners.ExtentCucumberFormatter:"})
public class RunCukesTestIPhone extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("output/report.html");

    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][]features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("Browser Name",browserName);
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Mac OSX");
        Reporter.setTestRunnerOutput("Sample test runner output message");
        testNGCucumberRunner.finish();
    }

}