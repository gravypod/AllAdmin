package com.gravypod.alladmin.files;

import java.util.HashMap;

public class SerializedUser {
	public String name;
	public String rank;
	public HashMap<String, SerializedLocation> homes;
	public boolean isMuted;
	public boolean isInvisible;
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SerializedUser) {
			SerializedUser u = (SerializedUser) obj;
			boolean homesEqual =  u.homes.entrySet().containsAll(homes.entrySet());
			return u.name.equals(name) && u.rank.equals(rank) && homesEqual && u.isInvisible == isInvisible && u.isMuted == isMuted;
		}
		return super.equals(obj);
	}
}
