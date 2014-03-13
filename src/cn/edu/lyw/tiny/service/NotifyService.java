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
	public static boolean flag = true;
	public synchronized static void startNotifyService(String token,String uid){
		if(flag){
			Timer timer =  new Timer();
			TimerTask task = new NotificationTask(token,uid);
			timer.scheduleAtFixedRate(task, 100,30000);
			flag = false;
		}
	}
}
