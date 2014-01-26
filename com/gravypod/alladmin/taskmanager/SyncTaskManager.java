package com.gravypod.alladmin.taskmanager;

import java.util.EnumSet;
import java.util.concurrent.ConcurrentLinkedQueue;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class SyncTaskManager implements ITickHandler {
	
	private final ConcurrentLinkedQueue<Runnable> runnables = new ConcurrentLinkedQueue<Runnable>();
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		Runnable r;
		while ((r = runnables.poll()) != null) {
			r.run();
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return null;
	}

	@Override
	public String getLabel() {
		return "AllAdmin TaskManager";
	}
	
	public void offerTask(Runnable r) {
		runnables.add(r);
	}
	
}
