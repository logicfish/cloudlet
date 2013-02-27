package com.logicfishsoftware.cloudlet.config.tool;

import com.logicfishsoftware.cloudlet.config.IConfiguration;

public final class ConfigurationUtil {
	@SuppressWarnings("unchecked")
	public static <T> IConfiguration<T> asType(IConfiguration<?> configuration) {
		return (IConfiguration<T>) configuration;
	}
}
