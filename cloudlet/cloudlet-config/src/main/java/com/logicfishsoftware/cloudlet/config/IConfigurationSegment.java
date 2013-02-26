package com.logicfishsoftware.cloudlet.config;

public interface IConfigurationSegment {
	String getSegmentName();
	<T> void registerConfig(String name,IConfiguration<T> config) throws ConfigurationException;
	<T> IConfiguration<T> getConfiguration(String name); 
}
