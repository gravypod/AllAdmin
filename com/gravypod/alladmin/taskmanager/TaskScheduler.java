package com.gravypod.alladmin.taskmanager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;


public class TaskScheduler {
	
	private static final SyncTaskManager taskManager;
	private static final ExecutorService executor;
	static {
		taskManager = new SyncTaskManager();
		executor = Executors.newFixedThreadPool(50);
		TickRegistry.registerTickHandler(taskManager, Side.SERVER);
	}
	
	public static SyncTaskManager getSyncTaskmanager() {
		return taskManager;
	}
	
	public static ExecutorService getAsyncExecutor() {
		return executor;
	}
	
}
