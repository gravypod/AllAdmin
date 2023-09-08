package com.gravypod.alladmin.files;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gravypod.alladmin.permissions.Group;

public class SerializedPermissionConfigs {
	public SerializedGroup[] groups;
	public String defaultGroup;
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SerializedPermissionConfigs) {
			SerializedPermissionConfigs p = (SerializedPermissionConfigs) obj;
			boolean groupsEqual = p.groups.length == groups.length;
			if (groupsEqual) {
				for (int i = 0; i < groups.length; i++) {
					SerializedGroup g = p.groups[i];
					SerializedGroup tg = groups[i];
					if (!g.equals(tg)) {
						return false;
					}
				}
			}
			boolean defaultEqual = p.defaultGroup.equals(defaultGroup);
			return groupsEqual && defaultEqual;
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "SerializedPermissionConfigs [groups=" + Arrays.toString(groups)
				+ ", defaultGroup=" + defaultGroup + "]";
	}
}
