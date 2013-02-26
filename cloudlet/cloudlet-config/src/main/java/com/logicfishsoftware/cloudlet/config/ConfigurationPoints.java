package com.logicfishsoftware.cloudlet.config;

public final class ConfigurationPoints {
	private final static ConfigurationPoints _CONFIGPOINTS = new ConfigurationPoints();
	
	private ConfigurationPoints() {
		if(_CONFIGPOINTS!=null) {
			throw new RuntimeException("Singleton object was initialised twice.");
		}
	}
	
	static protected ConfigurationPoints getDefault() {
		return _CONFIGPOINTS;
	}
	
	protected IConfigurationPoint findConfigurationPoint(final String point) {
		return new IConfigurationPoint() {

			private <T> IConfiguration<T> lookupConfiguration(String name) throws ConfigurationException {
				return ConfigurationPropogation.getDefault().lookup(point).getConfiguration(name);
			}
			
			private <T> void checkConfigType(IConfiguration<T> configuration,Class<T> type) throws IncompatibleConfigurationTypesException {
				Class<T> configurationType = configuration.getType();
				if(type!=null && !type.isAssignableFrom(configurationType)) {
					throw new IncompatibleConfigurationTypesException(configurationType,type);
				}				
			}
			
			public <T> T querySetting(String name,Class<T> type) throws ConfigurationException {
				IConfiguration<T> configuration = lookupConfiguration(name);
				checkConfigType(configuration,type);
				if(configuration==null) {
					throw new ConfigurationException("No such point.");
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
