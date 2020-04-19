package com.jedaway;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 * Test that log4j is configured correctly.
 */
public class Log4jTest {
    @Test
    public void test() {
//        System.setProperty("log4j.debug", "true");
        Logger logger = LogManager.getLogger(Log4jTest.class);
        logger.debug("hi");
    }
}
