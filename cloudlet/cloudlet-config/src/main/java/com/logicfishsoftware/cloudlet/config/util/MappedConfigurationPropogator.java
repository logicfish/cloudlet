package com.logicfishsoftware.cloudlet.config.util;

import java.util.HashMap;
import java.util.Map;

import com.logicfishsoftware.cloudlet.config.ConfigurationException;
import com.logicfishsoftware.cloudlet.config.IConfiguration;
import com.logicfishsoftware.cloudlet.config.IConfigurationPropogator;
import com.logicfishsoftware.cloudlet.config.IConfigurationSegment;
import com.logicfishsoftware.cloudlet.config.ConfigurationIncompatibleTypesException;
import com.logicfishsoftware.cloudlet.config.tool.ConfigurationUtil;

public class MappedConfigurationPropogator implements IConfigurationPropogator {
	Map<String,IConfiguration<?>> configurations = new HashMap<String, IConfiguration<?>>();
	private String point;
	
	public MappedConfigurationPropogator(String point) {
		this.point = point;
	}

	public void populateSegment(IConfigurationSegment segment)
			throws ConfigurationException {
		if(!point.equals(segment.getSegmentName())) {
			return;
		}
		for (String name : configurations.keySet()) {
			segment.registerConfig(name, configurations.get(name));
		}
	}
	
	// Helpers
	
	public <T> IConfiguration<T> findConfiguration(String name,Class<T> type) throws ConfigurationIncompatibleTypesException {
		IConfiguration<?> configuration = configurations.get(name);
		if(type!=null&&configuration!=null&& !configuration.getType().isAssignableFrom(type)) {
			throw new ConfigurationIncompatibleTypesException(configuration.getType(),type);
		}
		return ConfigurationUtil.asType(configuration);
	}
	public <T> IConfiguration<T> findConfiguration(String name) throws ConfigurationIncompatibleTypesException {
		return findConfiguration(name,null);
	}
	public void addConfiguration(String name,IConfiguration<?> configuration) {
		configurations.put(name, configuration);
	}

	public <T> void addConfiguration(String name,T configuration, Class<T> type) {
		addConfiguration(name, new Configuration<T>(type,configuration));
	}

	@SuppressWarnings("unchecked")
	public <T> void addConfiguration(String name,T configuration) {
		addConfiguration(name, new Configuration<T>((Class<T>) configuration.getClass(),configuration));
	}
}
