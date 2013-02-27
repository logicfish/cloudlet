package com.logicfishsoftware.cloudlet.config.tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.logicfishsoftware.cloudlet.config.ConfigurationException;
import com.logicfishsoftware.cloudlet.config.IConfiguration;
import com.logicfishsoftware.cloudlet.config.IConfigurationPropogator;
import com.logicfishsoftware.cloudlet.config.IConfigurationSegment;

public final class ConfigurationPropogation {
	private final static List<IConfigurationPropogator> propogators = Collections.synchronizedList(new ArrayList<IConfigurationPropogator>());
		
	protected static IConfigurationSegment lookup(final String point) throws ConfigurationException {
		return new IConfigurationSegment() {
			final Map<String,IConfiguration<?>> configs = new HashMap<String, IConfiguration<?>>();
			{
				for (IConfigurationPropogator propogator : propogators) {
					propogator.populateSegment(this);
				}				
			}
			public <T> void registerConfig(String name, IConfiguration<T> config) throws ConfigurationException {
				if(configs.containsKey(name)) {
					throw new ConfigurationException("Duplicate configuration <"+name+"> for segment <"+point+">.");
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
	}
	
	// API Methods
	
	protected static void register(IConfigurationPropogator propogator) {
		propogators.add(propogator);
	}
	
	protected static void unregister(IConfigurationPropogator propogator) {
		propogators.remove(propogator);
	}
}
