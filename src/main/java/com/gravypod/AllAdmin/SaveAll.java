package com.gravypod.AllAdmin;

import java.io.IOException;
import java.util.Collection;

import com.gravypod.AllAdmin.user.IUser;

public class SaveAll {
	
	public SaveAll(Collection<IUser> users) {
	
		startSave(users);
	}
	
	private void startSave(Collection<IUser> users) {
	
		for (IUser user : users) {
			user.saveData();
		}
		
		try {
	        Settings.warpsYamlFile.save(Settings.warpsList);
        } catch (IOException e) {
	        e.printStackTrace();
        }
		
	}
	
}
