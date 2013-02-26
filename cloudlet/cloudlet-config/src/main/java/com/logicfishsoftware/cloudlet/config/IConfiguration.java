package com.logicfishsoftware.cloudlet.config;


public interface IConfiguration<T> {
	Class<T> getType();
	T getSetting();
	void setSetting(T setting);
}
