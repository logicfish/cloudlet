package com.logicfishsoftware.cloudlet.config;

public final class Configurations {
	public static void registerPropogationHandler(IConfigurationPropogator propogator) {
		ConfigurationPropogation.getDefault().register(propogator);
	}
	public IConfigurationPoint findConfigurationPoint(final String point) {
		return ConfigurationPoints.getDefault().findConfigurationPoint(point);
	}
}
