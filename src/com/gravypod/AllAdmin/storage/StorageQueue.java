package com.gravypod.AllAdmin.storage;

import java.util.ArrayList;
import java.util.List;

public class StorageQueue {
	
	private static final List<ISave> wrightJobs = new ArrayList<ISave>();
	
	public static void addJob(final ISave job) {
	
		synchronized(StorageQueue.wrightJobs) {
			
			StorageQueue.wrightJobs.add(job);
			
		}
		
	}
	
	public static List<ISave> getJob() {
	
		synchronized(StorageQueue.wrightJobs) {
			
			final List<ISave> temps = new ArrayList<ISave>();
			
			temps.addAll(StorageQueue.wrightJobs);
			
			StorageQueue.wrightJobs.removeAll(temps);
			
			return temps;
			
		}
		
	}
	
}
