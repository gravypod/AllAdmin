package com.gravypod.AllAdmin.storage;

import java.io.IOException;
import java.util.List;

public class StorageThread implements Runnable { // TODO: Queue up in a thread
	
	                                             // for the MCAPI
	
	@Override
	public void run() {
	
		final List<ISave> jobs = StorageQueue.getJob();
		
		if (jobs.isEmpty()) {
			return;
		}
		
		for (final ISave job : jobs) {
			
			try {
				job.save();
			} catch (final IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
