package com.gravypod.AllAdmin.user;


public class UpdateInfo implements Runnable {
	
	AllAdminUser user;
	
	public UpdateInfo(AllAdminUser _user) {
		user = _user;
    }
	
	@Override
    public void run() {
		
		user.updateLastLocation();
		user.saveData();
		
    }
	
}
