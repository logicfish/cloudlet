package com.logicfishsoftware.cloudlet.config.tool;

import com.logicfishsoftware.cloudlet.config.IConfigurationPoint;
import com.logicfishsoftware.cloudlet.config.IConfigurationPropogator;
/**
 * Entry points for the configurations utility.
 * <h3>How to use:</h3>
 * <p>If your class provides configuration settings, or a place to store settings, register
 * an instance of {@link IConfigurationPropogator} to expose your setup.</p>
 * <p>If you class reads or updates configuration settings, use the method 
 * {@link Configurations#findConfigurationPoint(String)} to obtain an instance of the 
 * {@link IConfigurationPoint} to address the segment where your settings have been registered. 
 * 
 * @author logicfish@gmail.com
 *
 */
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
