package com.logicfishsoftware.cloudlet.config.tool;

import com.logicfishsoftware.cloudlet.config.ConfigurationException;
import com.logicfishsoftware.cloudlet.config.ConfigurationNotPresentException;
import com.logicfishsoftware.cloudlet.config.IConfiguration;
import com.logicfishsoftware.cloudlet.config.IConfigurationPoint;
import com.logicfishsoftware.cloudlet.config.IncompatibleConfigurationTypesException;

public final class ConfigurationPoints {
	protected static IConfigurationPoint findConfigurationPoint(final String point) {
		return new IConfigurationPoint() {

			private <T> IConfiguration<T> lookupConfiguration(String name) throws ConfigurationException {
				return ConfigurationPropogation.lookup(point).getConfiguration(name);
			}
			
			private <T> void checkConfigType(IConfiguration<T> configuration,Class<T> type) throws IncompatibleConfigurationTypesException {
				if(type==null||configuration==null) {
					return;
				}
				Class<T> configurationType = configuration.getType();
				if(!type.isAssignableFrom(configurationType)) {
					throw new IncompatibleConfigurationTypesException(configurationType,type);
				}				
			}
			
			public <T> T querySetting(String name,Class<T> type) throws ConfigurationException {
				IConfiguration<T> configuration = lookupConfiguration(name);
				checkConfigType(configuration,type);
				if(configuration==null) {
					throw new ConfigurationNotPresentException("No such configuration: <"+name+">.");
				}
				return (T) configuration.getSetting();
			}

			public <T> void updateSetting(String name, T setting,Class<T> type) throws ConfigurationException {
				IConfiguration<T> configuration = lookupConfiguration(name);
				checkConfigType(configuration,type);
				configuration.setSetting(setting);
			}

			public <T> T querySetting(String name)
					throws ConfigurationException {
				return querySetting(name,null);
			}

			@SuppressWarnings("unchecked")
			public <T> void updateSetting(String name, T setting)
					throws ConfigurationException {
				updateSetting(name,setting,(Class<T>)setting.getClass());
			}
		};
	}
}
