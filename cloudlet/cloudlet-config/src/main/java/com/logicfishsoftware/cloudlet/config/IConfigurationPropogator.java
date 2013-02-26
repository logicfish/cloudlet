package com.logicfishsoftware.cloudlet.config;

public interface IConfigurationPropogator {
	void populateSegment(IConfigurationSegment segment) throws ConfigurationException;
//	void lookupSegment(String point);
}
