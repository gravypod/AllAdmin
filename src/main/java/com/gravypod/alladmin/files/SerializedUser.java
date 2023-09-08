package com.gravypod.alladmin.files;

import java.util.HashMap;
import java.util.Map.Entry;

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
			
			for (Entry<String, SerializedLocation> location : u.homes.entrySet()) {
				
				if (!homes.containsKey(location.getKey())) {
					return false;
				}
				
				if (!homes.get(location.getKey()).equals(location.getValue())) {
					return false;
				}
			}
			
			boolean usernameEqual = u.name.equals(name);
			boolean rankEqual = u.rank.equals(rank);
			boolean invisEqual = u.isInvisible == isInvisible;
			boolean muteEqual = u.isMuted == isMuted;
			boolean godEqual = u.godmode == godmode;
			return usernameEqual && rankEqual && invisEqual && muteEqual && godEqual;
		}
		return super.equals(obj);
	}
}
