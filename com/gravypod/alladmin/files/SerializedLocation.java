package com.gravypod.alladmin.files;

public class SerializedLocation {
	public double x, y, z;
	public int dim;
	public float pitch, yaw;
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof SerializedLocation) {
			SerializedLocation loc = (SerializedLocation) obj;
			return loc.dim == dim && loc.x == x && loc.y == y && loc.z == z;
		}
		
		return super.equals(obj);
	}
}
