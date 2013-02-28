package com.logicfishsoftware.cloudlet.config;

import com.logicfishsoftware.cloudlet.config.tool.Configurations;
import com.logicfishsoftware.cloudlet.config.util.Configuration;
import com.logicfishsoftware.cloudlet.config.util.SimpleConfigurationPropogator;

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
    
    public void testUnkownSegmentHandler() throws Exception {
    	final String testSetting = "Test Setting";
    	// TODO this simplifies the test
    	// or, move this test to a different case (confusion arises between the propogator
    	// created inside this method and the one in the class).
    	// this.propogator.setUnknownSegmentHandler(...);
		SimpleConfigurationPropogator simplePropogator = new SimpleConfigurationPropogator("test.point",new SimpleConfigurationPropogator.UnknownConfigurationHandler() {
			
			private IConfiguration<String> configuration = new Configuration<String>(testSetting);

			public IConfiguration<?> handleUnknownConfiguration(String point,
					String name) {
				return configuration;
			}
		});
		String string = configurationPoint.querySetting("No setting");
		assertSame(testSetting,string);
		
		IConfigurationPoint pointLocal = Configurations.findConfigurationPoint("No name");
		string = pointLocal.querySetting("No setting again");
		assertSame(testSetting,string);
		
		simplePropogator.unregister();
		try {
			string = pointLocal.querySetting("No setting again");
    		fail("Expected exception.");
    	} catch(ConfigurationNotPresentException e) {
    	}
		
	}

}
