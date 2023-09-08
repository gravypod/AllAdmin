package com.gravypod.alladmin.files;

import java.util.List;

public class SerializedGroup {
	public String name;
	public List<String> permissions;
	public List<String> subgroups;
	public String messageFormat;
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SerializedGroup) {
			
			SerializedGroup g = (SerializedGroup) obj;
			boolean nameEquals = g.name.equals(name);
			boolean permsEqual = g.permissions.containsAll(permissions);
			boolean messageFormatEQ = g.messageFormat.equals(messageFormat);
			boolean subsEq = g.subgroups.containsAll(subgroups);
			return messageFormatEQ && subsEq && nameEquals && permsEqual;
			
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "SerializedGroup [name=" + name + ", permissions=" + permissions
				+ ", subgroups=" + subgroups + ", messageFormat="
				+ messageFormat + "]";
	}
}
