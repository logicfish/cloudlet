package com.logicfishsoftware.cloudlet.config.util;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.logicfishsoftware.cloudlet.config.ConfigurationException;
import com.logicfishsoftware.cloudlet.config.ConfigurationIncompatibleTypesException;
import com.logicfishsoftware.cloudlet.config.IConfiguration;
import com.logicfishsoftware.cloudlet.config.IConfigurationPropogator;
import com.logicfishsoftware.cloudlet.config.IConfigurationSegment;
import com.logicfishsoftware.cloudlet.config.tool.ConfigurationUtil;
import com.logicfishsoftware.cloudlet.config.tool.Configurations;
import com.logicfishsoftware.cloudlet.config.util.SimpleConfigurationPropogator.UnknownConfigurationHandler;

/**
 * Helper class providing a default implementation of {@link IConfigurationPropogator}.
 * <p>This class maps a single named segment to a configuration point, storing the configuration
 * objects in a hash map.</p>  
 * <p>Public helper methods are provided so that applications can expose
 * their {@link IConfiguration} objects.  These methods are safe because the {@link SimpleConfigurationPropogator}
 * object is never exposed to clients.</p>  
 * 
 * @author Home
 *
 */
public class SimpleConfigurationPropogator implements IConfigurationPropogator {
	public interface UnknownConfigurationHandler {

		IConfiguration<?> handleUnknownConfiguration(String point, String name);
		
	}
	
	Map<String,IConfiguration<?>> configurations = new HashMap<String, IConfiguration<?>>();
	
	@Getter
	@Setter
	private String point;
	
	@Getter 
	@Setter 
	UnknownConfigurationHandler unknownConfigurationHandler;
	
	public SimpleConfigurationPropogator(String point) {
		this(point,null);
	}

	public SimpleConfigurationPropogator(String point,
			UnknownConfigurationHandler unknownConfigurationHandler) {
		this.point = point;
		this.unknownConfigurationHandler = unknownConfigurationHandler;
		this.register();
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
	
	@Override
	public IConfiguration<?> unknownSegmentHandler(
			IConfigurationSegment iConfigurationSegment, String name) {
		if(unknownConfigurationHandler==null) {
			return null;
		}
		return unknownConfigurationHandler.handleUnknownConfiguration(point,name);
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

	public void removeConfiguration(String name) {
		configurations.remove(name);
	}

	public SimpleConfigurationPropogator register() {
		Configurations.registerPropogationHandler(this);
		return this;
	}
	public SimpleConfigurationPropogator unregister() {
		Configurations.unregisterPropogationHandler(this);
		return this;
	}


}
