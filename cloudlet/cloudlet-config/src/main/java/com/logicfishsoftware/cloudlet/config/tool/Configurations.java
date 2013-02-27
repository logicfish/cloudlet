package com.logicfishsoftware.cloudlet.config.tool;

import com.logicfishsoftware.cloudlet.config.IConfigurationPoint;
import com.logicfishsoftware.cloudlet.config.IConfigurationPropogator;

public final class Configurations {
	public static void registerPropogationHandler(IConfigurationPropogator propogator) {
		ConfigurationPropogation.register(propogator);
	}
	public static IConfigurationPoint findConfigurationPoint(final String point) {
		return ConfigurationPoints.findConfigurationPoint(point);
	}
	public static void unregisterPropogationHandler(
			IConfigurationPropogator propogator) {
		ConfigurationPropogation.unregister(propogator);
	}
}
