package com.gravypod.AllAdmin;

import java.io.IOException;
import java.util.Collection;

import com.gravypod.AllAdmin.user.IUser;

public class SaveAll {
	
	public SaveAll(final Collection<IUser> users) {
	
		startSave(users);
		
	}
	
	private void startSave(final Collection<IUser> users) {
	
		for (final IUser user : users) {
			user.saveData();
		}
		
		try {
			Settings.warpsYamlFile.save(Settings.warpsList);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
