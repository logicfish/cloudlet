package com.logicfishsoftware.cloudlet.config;

public class ConfigurationIncompatibleTypesException extends
		ConfigurationException {

	private static final long serialVersionUID = 7438107151326292063L;

	public ConfigurationIncompatibleTypesException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConfigurationIncompatibleTypesException(String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ConfigurationIncompatibleTypesException(String message,
			Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ConfigurationIncompatibleTypesException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ConfigurationIncompatibleTypesException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ConfigurationIncompatibleTypesException(Class<?> type,Class<?> requestedType) {
		super("Configuration type <"+type.getCanonicalName()+"> is incompatible with the requested type <"+type.getCanonicalName()+">.");
		// TODO Auto-generated constructor stub
	}
}
