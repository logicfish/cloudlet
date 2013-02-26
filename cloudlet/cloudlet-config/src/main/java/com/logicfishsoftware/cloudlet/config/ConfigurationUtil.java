package com.logicfishsoftware.cloudlet.config;

public final class ConfigurationUtil {
//	@SuppressWarnings("unchecked")
//	public <T> IConfiguration<T> asType(IConfiguration<?> configuration,Class<T> type) {
//		return (IConfiguration<T>) configuration;
//	}
	@SuppressWarnings("unchecked")
	public static <T> IConfiguration<T> asType(IConfiguration<?> configuration) {
		return (IConfiguration<T>) configuration;
	}
}
