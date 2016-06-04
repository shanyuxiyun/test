package com.lee.douban.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lee.douban.util.CookUtil;
import com.lee.douban.util.MovieContants;
import com.lee.jsoup.ext.JSoupExt;

public class MovieWalker {

	private JSoupExt soupExt;

	private MovieExcelWorker excelWorker;

	public void setExcelWorker(MovieExcelWorker excelWorker) {
		this.excelWorker = excelWorker;
	}

	public JSoupExt getSoupExt() {
		return soupExt;
	}

	public void setSoupExt(JSoupExt soupExt) {
		this.soupExt = soupExt;
	}

	public void launch() {
		ExecutorService executorService = Executors.newFixedThreadPool(MovieContants.MAX_WORKER_THREAD);
		List<String> tags = getMovieTags();
		for (int i = 0; i < tags.size(); i++) {
			System.out.println("(" + (i + 1) + ") tag name : " + tags.get(i));
			MovieCache.putTag(tags.get(i));
			executorService.execute(new MovieFetcher(tags.get(i), this, new MovieSaver(tags.get(i), this.excelWorker)));
		}
	}

	private List<String> getMovieTags() {
		List<String> tags = new ArrayList<String>();
		Document document = soupExt.buildDocument("tag/",CookUtil.randomCookie());
		Elements elements = document.select("table.tagCol:nth-child(3) a[class=tag]");
		for (Element e : elements) {
			tags.add(e.text());
		}
		return tags;
	}

}
