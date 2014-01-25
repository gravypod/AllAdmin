package com.gravypod.alladmin.files;

import java.util.HashMap;

public class SerializedUser {
	public String name;
	public String rank;
	public HashMap<String, SerializedLocation> homes;
	public boolean isMuted;
}
