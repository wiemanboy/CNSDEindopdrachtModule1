package com.wiemanboy.board;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty", "html:target/cucumber"},
        glue = "com.wiemanboy.board.features"
)
public class CucumberIntegrationTest {
}
