package com.logicfishsoftware.cloudlet.config;

public interface IConfigurationPoint {
	<T> T querySetting(String name) throws ConfigurationException;
	<T> T querySetting(String name,Class<T> type) throws ConfigurationException;
	<T> void updateSetting(String name,T setting) throws ConfigurationException;
	<T> void updateSetting(String name,T setting,Class<T> type) throws ConfigurationException;
}
