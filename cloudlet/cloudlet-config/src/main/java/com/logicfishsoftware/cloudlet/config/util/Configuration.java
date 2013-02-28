package com.logicfishsoftware.cloudlet.config.util;

import com.logicfishsoftware.cloudlet.config.IConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Configuration<T> implements IConfiguration<T> {
	
	Class<T> type;
	T setting;
	
	public Configuration() {
		this(null,null);
	}
	@SuppressWarnings("unchecked")
	public Configuration(T setting) {
		this((Class<T>) setting.getClass(),setting);
	}
}
