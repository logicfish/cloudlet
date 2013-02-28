package com.logicfishsoftware.cloudlet.config;

/**
 * Instances of this class expose a configuration setting to the system that may be accessed
 * by applications using the framework.  Clients never see the {@link IConfiguration} object.
 * When a configuration is requested, the system does a series of checks; if these are passed, the
 * client is provided with whatever object is returned by the {@link #getSetting()} method. 
 * 
 * @author logicfish@gmail.com
 *
 * @param <T> The class representing the type of this configuration setting.
 */
public interface IConfiguration<T> {
	Class<T> getType();
	T getSetting();
	void setSetting(T setting);
}
