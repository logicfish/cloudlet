package com.logicfishsoftware.cloudlet.config;

/**
 * 
 * An instance of this class is passed by the framework to clients exposing the {@link IConfigurationPropogator}
 * interface, to the {@link IConfigurationPropogator#populateSegment(IConfigurationSegment)} method where clients
 * can register {@link IConfiguration} objects. 
 * 
 * @author logicfish@gmail.com
 *
 */
public interface IConfigurationSegment {
	String getSegmentName();
	<T> void registerConfig(String name,IConfiguration<T> config) throws ConfigurationException;
	<T> IConfiguration<T> getConfiguration(String name); 
}
