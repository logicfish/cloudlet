package com.logicfishsoftware.cloudlet.config.util;

import com.logicfishsoftware.cloudlet.config.IConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfigurationReadOnly<T> implements IConfiguration<T> {

	Class<T> type;
	T setting;
	
	public void setSetting(T setting) {
		throw new IllegalAccessError("Attempt to modify read-only configuration of type "+type.getCanonicalName());
	}
	
}
