package com.logicfishsoftware.cloudlet.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ConfigurationPropogation {
	private final static ConfigurationPropogation _CONFIGPROPOGATION = new ConfigurationPropogation();
	private List<IConfigurationPropogator> propogators = new ArrayList<IConfigurationPropogator>();
	
	private ConfigurationPropogation() {
		if(_CONFIGPROPOGATION!=null) {
			throw new RuntimeException("Singleton object was initialised twice.");
		}
	}
	
	IConfigurationSegment lookup(final String point) throws ConfigurationException {
		final Map<String,IConfiguration<?>> configs = new HashMap<String, IConfiguration<?>>();
		IConfigurationSegment segment = new IConfigurationSegment() {
			public <T> void registerConfig(String name, IConfiguration<T> config) throws ConfigurationException {
				if(configs.containsKey(name)) {
					throw new ConfigurationException("Duplicate configuration "+name);
				}
				configs.put(name,config);
			}

			public String getSegmentName() {
				return point;
			}

			public <T> IConfiguration<T> getConfiguration(String name) {
				return ConfigurationUtil.asType(configs.get(name));
			}
		};
		for (IConfigurationPropogator propogator : propogators) {
			propogator.populateSegment(segment);
		}
		return segment;
	}
	
	// API Methods
	
	protected void register(IConfigurationPropogator propogator) {
		propogators.add(propogator);
	}
	
	static protected ConfigurationPropogation getDefault() {
		return _CONFIGPROPOGATION;
	}

}
