package com.dynacrongroup.runner;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@Cucumber.Options(tags = "@Single", format = "junit:target/junit.xml")
public class CucumberTest {
}
