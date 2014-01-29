package com.gravypod.alladmin.files;

import java.util.HashMap;

public class SerializedUser {
	public String name;
	public String rank;
	public HashMap<String, SerializedLocation> homes;
	public boolean isMuted;
	public boolean isInvisible;
	public boolean godmode;
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SerializedUser) {
			SerializedUser u = (SerializedUser) obj;
			boolean homesEqual =  u.homes.entrySet().containsAll(homes.entrySet());
			boolean usernameEqual = u.name.equals(name);
			boolean rankEqual = u.rank.equals(rank);
			boolean invisEqual = u.isInvisible == isInvisible;
			boolean muteEqual = u.isMuted == isMuted;
			boolean godEqual = u.godmode == godmode;
			return usernameEqual && rankEqual && homesEqual && invisEqual && muteEqual && godEqual;
		}
		return super.equals(obj);
	}
}
