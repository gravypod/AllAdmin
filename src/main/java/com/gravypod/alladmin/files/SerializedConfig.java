package com.gravypod.alladmin.files;

public class SerializedConfig {
	public String broadcastFormat;
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SerializedConfig) {
			SerializedConfig c = (SerializedConfig) obj;
			boolean broadcastEqual = c.broadcastFormat.equals(this.broadcastFormat);
			return broadcastEqual;
			
		}
		return super.equals(obj);
	}
}
