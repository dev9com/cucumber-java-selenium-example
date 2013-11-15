package com.dynacrongroup.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

/**
 * User: yurodivuie
 * Date: 11/15/13
 * Time: 8:22 AM
 */
public class TestConf {

    private static final Logger LOG = LoggerFactory.getLogger(TestConf.class);
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    private static final Config BASE_CONFIG = ConfigFactory.load();
    private static final ObjectMapper MAPPER = buildObjectMapper();
    private static final TestConf TEST_CONF = buildTestConfSingleton(BASE_CONFIG);

    @Valid
    @Min(1)
    private Integer ajaxWaitSeconds;

    @Valid
    @NotNull
    private String searchUrl;

    public static TestConf get() {
        return TEST_CONF;
    }

    public Integer getAjaxWaitSeconds() {
        return ajaxWaitSeconds;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    private static ObjectMapper buildObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    private static TestConf buildTestConfSingleton(Config config) {
        Map<String, Object> unwrappedConfig = config.root().unwrapped();
        TestConf testConf = MAPPER.convertValue(unwrappedConfig, TestConf.class);
        Set<ConstraintViolation<TestConf>> constraintViolations = VALIDATOR.validate(testConf);
        if (!constraintViolations.isEmpty()) {
            StringBuilder message = new StringBuilder();
            message.append("Configuration is invalid.  The following problems must be corrected for the application to start:\n");
            for (ConstraintViolation<TestConf> violation : constraintViolations) {
                message.append("  ");
                message.append(violation.getPropertyPath());
                message.append(" : ");
                message.append(violation.getMessage());
                message.append('\n');
            }
            LOG.error(message.toString());
            throw new IllegalStateException("Application's test configuration file invalid.");
        }
        return testConf;
    }
}
