package com.cagiris.coho;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	private static final Logger logger = LoggerFactory.getLogger("test.logger");

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		MDC.put("requestContext", "testUser");
		logger.info("Load testing");
		assertTrue(true);
	}
}
