package com.logicfishsoftware.cloudlet.config;

public class IncompatibleConfigurationTypesException extends
		ConfigurationException {

	private static final long serialVersionUID = 7438107151326292063L;

	public IncompatibleConfigurationTypesException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IncompatibleConfigurationTypesException(String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public IncompatibleConfigurationTypesException(String message,
			Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IncompatibleConfigurationTypesException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IncompatibleConfigurationTypesException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public IncompatibleConfigurationTypesException(Class<?> type,Class<?> requestedType) {
		super("Configuration type <"+type.getCanonicalName()+"> is incompatible with the requested type <"+type.getCanonicalName()+">.");
		// TODO Auto-generated constructor stub
	}
}
