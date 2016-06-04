package com.lee.douban.movie;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.concurrent.Executors;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.lee.douban.util.CookUtil;
import com.lee.douban.util.MovieContants;
import com.lee.douban.util.ThreadUtil;

public class MovieFetcher implements Runnable {

	private String tag;

	private MovieWalker movieWalker;

	private MovieSaver movieSaver;

	public MovieFetcher(String tag, MovieWalker movieWalker, MovieSaver movieSaver) {
		this.tag = tag;
		this.movieWalker = movieWalker;
		this.movieSaver = movieSaver;
	}

	public void run() {
		int counter = 0;
		Document doc = movieWalker.getSoupExt().buildDocument(buildURLParam(counter), CookUtil.randomCookie());
		Elements elements = doc.select("tr[class=item]");
		boolean isReboot = false;
		Executors.newSingleThreadExecutor().submit(this.movieSaver);
		while (elements.size() > 0 || isReboot) {
			try {
				MovieCache.add(tag, elements);
				counter += MovieContants.PAGE_SIZE;
				doc = movieWalker.getSoupExt().buildDocument(buildURLParam(counter), CookUtil.randomCookie());
				elements = doc.select("tr[class=item]");
				isReboot = false;
				ThreadUtil.sleep(new Random().nextInt(MovieContants.FETCHER_NORMAL_MAX_SLEEP),
						Thread.currentThread().getName(), "Fetch "+tag);
			}  catch (Exception e) {
				ThreadUtil.sleep(MovieContants.FETCHER_ABNORMAL_MAX_SLEEP, Thread.currentThread().getName(),"Failed to Fetch "+ tag);
				counter -= MovieContants.PAGE_SIZE;
				elements = new Elements();
				isReboot = true;
				System.out.println("reboot thread " + Thread.currentThread().getName() + " , tag is " + tag);
				e.printStackTrace();
			}
		}
		MovieCache.removeTag(tag);
	}

	private String buildURLParam(int startPage) {
		try {
			return "tag/" + URLEncoder.encode(tag, "UTF-8") + "?start=" + startPage + "&type=T";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
