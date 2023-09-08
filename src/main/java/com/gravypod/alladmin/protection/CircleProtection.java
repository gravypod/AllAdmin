package com.gravypod.alladmin.protection;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.SerializedLocation;

public class CircleProtection implements IProtection {
	public int dimension;
	public int protectionRange;
	public SerializedLocation center;
	
	@Override
	public boolean canEdit(IUser user, int x, int y, int z) {
		int xCheck = (x - (int)center.x);
		int yCheck = (y - (int)center.y);
		return (xCheck * xCheck) + (yCheck * yCheck) < protectionRange * protectionRange;
	}
	
	@Override
	public int getDimension() {
		return dimension;
	}
}
