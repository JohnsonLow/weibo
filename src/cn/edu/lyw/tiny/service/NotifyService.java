package cn.edu.lyw.tiny.service;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.lyw.tiny.task.NotificationTask;

/**
 * @description  
 * @version 1.0
 * 
 */
public class NotifyService {
	public static void startNotifyService(String token,String uid){
		Timer timer =  new Timer();
		TimerTask task = new NotificationTask(token,uid);
		timer.scheduleAtFixedRate(task, 100,30000);
	}
}
