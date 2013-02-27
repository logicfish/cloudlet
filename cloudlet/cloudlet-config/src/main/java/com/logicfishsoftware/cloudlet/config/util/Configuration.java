package com.logicfishsoftware.cloudlet.config.util;

import com.logicfishsoftware.cloudlet.config.IConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Configuration<T> implements IConfiguration<T> {

	Class<T> type;
	T setting;
	
}
