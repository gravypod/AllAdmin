package com.gravypod.alladmin.protection;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.SerializedLocation;

public class CubeProtection implements IProtection {
	public int dimension;
	public SerializedLocation firstSelection;
	public SerializedLocation secondSelection;
	
	@Override
	public boolean canEdit(IUser user, int x, int y, int z) {
		boolean stopEvent = isInY(y);
		return !stopEvent;
	}
	
	@Override
	public int getDimension() {
		return dimension;
	}
	
	public boolean isInsideBox(int x, int z) {
		
		int minX = (int) Math.min(firstSelection.x, secondSelection.x);
		int maxX = (int) Math.max(firstSelection.x, secondSelection.x);
		
		int minZ = (int) Math.min(firstSelection.z, secondSelection.z);
		int maxZ = (int) Math.max(firstSelection.z, secondSelection.z);
		
		return (x >= minX && x <= maxX) && (z >= minZ && z <= maxZ);
		
	}
	
	public boolean isInY(int y) {
		
		int minY = (int) Math.min(firstSelection.y, secondSelection.y);
		int maxY = (int) Math.max(firstSelection.y, secondSelection.y);
		
		return y >= minY && y <= maxY;
		
	}
	
}
