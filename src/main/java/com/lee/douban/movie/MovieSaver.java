package com.lee.douban.movie;

import java.util.Random;

import org.jsoup.select.Elements;

import com.lee.douban.util.MovieContants;
import com.lee.douban.util.ThreadUtil;

public class MovieSaver implements Runnable {

	private String tag;

	private MovieExcelWorker excelWorker;

	public MovieSaver(String tag, MovieExcelWorker excelWorker) {
		this.tag = tag;
		this.excelWorker = excelWorker;
	}

	public void run() {
		Elements elements = null;
		while (MovieCache.hasTag(tag)) {
			try {
				elements = MovieCache.nextElementsByTag(tag);
				if (elements.size() > 0) {
					excelWorker.saveElements(tag, elements);
				}
				ThreadUtil.sleep(new Random().nextInt(MovieContants.SAVE_THREAD_MAX_WAIT),
						Thread.currentThread().getName(), "Write " + tag);
			} catch (Exception e) {
				e.printStackTrace();
				ThreadUtil.sleep(MovieContants.SAVE_FAIL_MAX_WAIT, Thread.currentThread().getName(),
						"Write " + tag + " failed.");
			}
		}

	}

}
