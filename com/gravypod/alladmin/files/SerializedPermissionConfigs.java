package com.gravypod.alladmin.files;

import java.util.ArrayList;
import java.util.List;

import com.gravypod.alladmin.permissions.Group;

public class SerializedPermissionConfigs {
	public SerializedGroup[] groups;
	public String defaultGroup;
	public List<String> subgroups = new ArrayList<String>();
}
