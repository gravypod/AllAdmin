/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.user;


public interface IUser {
	
	/**
	 * 
	 * Has a permission.
	 * 
	 * @param node - Permission node
	 * @return
	 * 
	 */
    public boolean hasPermission(String node);
    
    /**
     * 
     * Send a failiure notice.
     * @param command
     * 
     */
    public void sendCommandFaliure(String command);
    
    /**
     * 
     * Send a message to the user.
     * 
     * @param message
     * 
     */
    public void sendMessage(String message);
    
    /**
     * Save the user data.
     */
    public void saveData();

}
