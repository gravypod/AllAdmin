package com.gravypod.alladmin.protection;

import com.gravypod.alladmin.IUser;

public interface IProtection {
	int getDimension();
	boolean canEdit(IUser user, int x, int y, int z);
}
