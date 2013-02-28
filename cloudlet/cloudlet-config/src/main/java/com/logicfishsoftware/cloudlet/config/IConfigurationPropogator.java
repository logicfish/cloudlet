package com.logicfishsoftware.cloudlet.config;

import com.logicfishsoftware.cloudlet.config.tool.Configurations;

/**
 * Allows applications to expose their configuration setup.
 * <h3>How to use</h3> 
 * <p>Create an instance of this class, then use 
 * {@link Configurations#registerPropogationHandler(IConfigurationPropogator)}
 * to register your handler with the system.  Use the {@link #populateSegment(IConfigurationSegment)}
 * method to expose {@link IConfiguration} objects to the segments that you want your configs to be
 * visible under.
 * 
 * 
 * @author logicfish@gmail.com
 *
 */
public interface IConfigurationPropogator {
	
	void populateSegment(IConfigurationSegment segment) throws ConfigurationException;

	IConfiguration<?> unknownSegmentHandler(IConfigurationSegment iConfigurationSegment, String name);
}
