package com.logicfishsoftware.cloudlet.config;

import lombok.Data;


public class DummyPropogator implements IConfigurationPropogator {
	
	public static final String DUMMY_POINT = "point.dummy";
	
	public static final String DUMMY_CONFIG = "config.dummy";
	public static final String DUMMY_CONFIG_READONLY = "config.dummy.readonly";
	public static final String DUMMY_CONFIG2 = "config.dummy2";

	public static final String DUMMY_SETTING = "Dummy Setting";
	public static final String DUMMY_READONLY_SETTING = "Dummy Read Only Setting";
	public static final String DUMMY_SETTING2 = "Dummy Setting 2";

	private static final DummyPropogator _DUMMY_PROPOGATOR = new DummyPropogator();
	
	static { 
		ConfigurationPropogation.getDefault().register(getDefault());
	}

	private IConfiguration<String> dummyConfig = new Configuration<String>(String.class,DUMMY_SETTING);
	private IConfiguration<String> dummyReadOnlyConfig = new ConfigurationReadOnly<String>(String.class,DUMMY_READONLY_SETTING);

	private IConfiguration<String> dummyConfig2 = new IConfiguration<String>() {
		Class<String> type = String.class;
		String setting = DUMMY_SETTING2;
		public Class<String> getType() {
			return type;
		}
		public String getSetting() {
			return setting;
		}
		public void setSetting(String setting) {
			this.setting = setting;
		}

		
	};

	// The actual settings storage, using fields as our dummy storage pattern
	
	private DummyPropogator() {
		if(_DUMMY_PROPOGATOR!=null) {
			throw new RuntimeException("Singleton object was initialised twice.");
		}
	}
	
	public void populateSegment(IConfigurationSegment segment) throws ConfigurationException {
		if(!DUMMY_POINT.equals(segment.getSegmentName())) {
			return;
		}
		segment.registerConfig(DUMMY_CONFIG, dummyConfig);
		segment.registerConfig(DUMMY_CONFIG_READONLY, dummyReadOnlyConfig);
		segment.registerConfig(DUMMY_CONFIG2, dummyConfig2);
	}
	
	public static DummyPropogator getDefault() {
		return _DUMMY_PROPOGATOR;
	}

}
