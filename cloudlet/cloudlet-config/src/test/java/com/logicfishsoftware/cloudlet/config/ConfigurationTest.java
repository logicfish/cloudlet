package com.logicfishsoftware.cloudlet.config;

import com.logicfishsoftware.cloudlet.config.tool.Configurations;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ConfigurationTest 
    extends TestCase
{
	
	IConfigurationPoint configurationPoint = Configurations.findConfigurationPoint(DummyPropogator.DUMMY_POINT);
	private IConfigurationPropogator propogator = new DummyPropogator();

	@Override
	protected void setUp() throws Exception {
		Configurations.registerPropogationHandler(propogator);
	}
	
	@Override
	protected void tearDown() throws Exception {
		Configurations.unregisterPropogationHandler(propogator);
	}
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
    	assertNotNull(configurationPoint);

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
    	}		
	}
    
    public void testConfigurationNotPresent() throws Exception {
    	try {
    		configurationPoint.querySetting("A random String");
    		fail("Expected exception.");
    	} catch(ConfigurationNotPresentException e) {
    	}
	}

}
