package com.lee.html;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class JSoupTest {

	@Test
	public void test1() throws IOException {

		for (int i = 0; i < 20; i++) {
			Document doc = Jsoup.connect("http://www.xiaoshuwu.net/page/" + (i + 1) + "/").get();
			Elements elements = doc.select("a:contains(下载地址)");
			for (Element e : elements) {
				System.out.println(e.attr("href"));
			}
		}
	}

	@Test
	public void testDouban()   {
		/**
		 * <div class="pl2"> <a href=
		 * "https://movie.douban.com/subject/26678760/" class=""> 禁止的爱：善良的小姨子 /
		 * <span style="font-size:12px;">Good Sister-in-law: Forbidden love /
		 * Nice Sister In Law</span> </a>
		 * <p class="pl">
		 * 2015-11-19(韩国) / 妃深 / 李圣涛 / 姜盛弼 / 韩国 / 姜熏 / 97分钟 / 禁止的爱：善良的小姨子 / 剧情 /
		 * 爱情 / 情色 / 姜熏 / 韩语
		 * </p>
		 * <div class="star clearfix"> <span class="allstar30"></span>
		 * <span class="rating_nums">5.5</span>
		 * <span class="pl">(2115人评价)</span> </div> </div>
		 */
		
		//https://jsoup.org/apidocs/org/jsoup/select/Selector.html
		Map<String, String> datas = new TreeMap<String, String>();
		for (int i = 0; i < 394; i++) {
			try {
				Document doc = Jsoup
						.connect("https://movie.douban.com/tag/%E7%88%B1%E6%83%85?start=" + (i * 20) + "&type=T").get();
				Elements elements = doc.select("div[class=pl2]");
				for (Element e : elements) {
					datas.put( e.select("div > span[class=rating_nums]").get(0).text(),
							e.select("a").get(0).text());
					// name
					 System.out.println(e.select("a").get(0).text());
					// star
					 System.out.println("\t\t" + e.select("div > span[class=rating_nums]").get(0).text());
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		
		int counter = 0 ;
		for (Map.Entry<String, String> e : datas.entrySet()) {
			System.out.println(++counter+" : "+e.getKey()+"\t"+e.getValue());
		}
	}

}
