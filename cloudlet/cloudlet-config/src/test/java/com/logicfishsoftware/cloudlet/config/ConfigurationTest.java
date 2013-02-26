package com.logicfishsoftware.cloudlet.config;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ConfigurationTest 
    extends TestCase
{
	
	static {
		DummyPropogator.getDefault();
	}
	IConfigurationPoint configurationPoint = ConfigurationPoints.getDefault().findConfigurationPoint(DummyPropogator.DUMMY_POINT);

	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ConfigurationTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ConfigurationTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws ConfigurationException 
     */
    public void testReadWriteSetting() throws ConfigurationException
    {
    	assertNotNull(configurationPoint);
    	String string = configurationPoint.querySetting(DummyPropogator.DUMMY_CONFIG);
    	assertEquals(string,DummyPropogator.DUMMY_SETTING);

    	configurationPoint.updateSetting(DummyPropogator.DUMMY_CONFIG, "New Setting");
    	string = configurationPoint.querySetting(DummyPropogator.DUMMY_CONFIG);
    	assertEquals(string,"New Setting");

    	    	
    }
    public void testReadOnlySetting() throws Exception {
    	String string = configurationPoint.querySetting(DummyPropogator.DUMMY_CONFIG_READONLY);
    	assertEquals(string,DummyPropogator.DUMMY_READONLY_SETTING);

    	try {
    		configurationPoint.updateSetting(DummyPropogator.DUMMY_CONFIG_READONLY, "New Setting");
    		fail("Expected IllegalAccessError");
    	} catch (IllegalAccessError e) {
    	}
    	string = configurationPoint.querySetting(DummyPropogator.DUMMY_CONFIG_READONLY);
    	assertEquals(string,DummyPropogator.DUMMY_READONLY_SETTING);		
	}
    public void testIncompatibleTypeAssignment() throws Exception {
    	try {
    		configurationPoint.updateSetting(DummyPropogator.DUMMY_CONFIG2, 1);
    		fail("Expected exception.");
    	} catch (ConfigurationException e) {
    		e.printStackTrace();
    	}		
	}
}
