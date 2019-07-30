package com.ascending.estate.jdbc;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoTest {
    private static Logger logger = LoggerFactory.getLogger(DemoTest.class);

    @BeforeClass
    public static void initAllTest(){
        logger.info("Before class: start testing...");
    }

    @AfterClass
    public static void endAllTest(){
        logger.info("afterclass: the tests are done");
    }

    @Before
    public void initTest(){
        logger.info(("before: start unit testing"));
    }

    @After
    public void endTest(){
        logger.info("after: unite test are done");
    }
    @Test
    public void test1() {
        logger.trace("########## Test1 - Trace: test1 is done. ##########");
        logger.debug("########## Test1 - Debug: test1 is done. ##########");
        logger.info("########## Test1 - Info: test1 is done. ##########");
        logger.warn("########## Test1 - Warn: test1 is done. ##########");
        logger.error("########## Test1 - Error: test1 is done. ##########");
    }
    @Test
    public void test2() {
        logger.trace("########## Test2 - Trace: test1 is done. ##########");
        logger.debug("########## Test2 - Debug: test1 is done. ##########");
        logger.info("########## Test2 - Info: test1 is done. ##########");
        logger.warn("########## Test2 - Warn: test1 is done. ##########");
        logger.error("########## Test2 - Error: test1 is done. ##########");
    }
}
