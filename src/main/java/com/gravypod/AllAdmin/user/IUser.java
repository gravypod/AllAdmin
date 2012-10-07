/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.user;


public interface IUser {

    public boolean hasPermission(String node);

    public void sendCommandFaliure(String command);

    public void sendMessage(String message);

}
