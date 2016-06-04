package com.lee.douban.util;

import java.util.concurrent.TimeUnit;

public abstract class ThreadUtil {

	public static void sleep(int second, String threadName, String tag) {
		try {
			TimeUnit.SECONDS.sleep(second);
			System.out.println(threadName + " has sleep " + second + " seconds.\t tag : " + tag);
		} catch (InterruptedException e) {
		}
	}

}
