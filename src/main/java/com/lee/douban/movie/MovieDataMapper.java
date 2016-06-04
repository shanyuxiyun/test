package com.lee.douban.movie;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

import com.lee.datamapper.DataMapper;
import com.lee.douban.util.RegexpUtil;
import com.lee.jsoup.ext.AbstractJSoup;

public class MovieDataMapper implements DataMapper<Element, Movie> {

	public Movie mapper(Element e) {
		Movie m = new Movie();
		m.setActors(RegexpUtil.getActors(AbstractJSoup.textOfFirstElement((e.select("td > div > p[class=pl]")))));
		m.setCommentPeople(Integer.valueOf(RegexpUtil
				.getCommentPeople(AbstractJSoup.textOfFirstElement(e.select("td > div > div > span[class=pl]")))));
		m.setStartDate(RegexpUtil.getStartDate(AbstractJSoup.textOfFirstElement(e.select("td > div > p[class=pl]"))));
		m.setRating(AbstractJSoup.textOfFirstElement(e.select("td > div > div >span[class=rating_nums]")));
		m.setLogoUrl(e.select("td > a[class=nbg] > img").attr("src"));
		m.setName(e.select("td > div > a").get(0).ownText().replaceAll("/", ""));
		m.setNameAliases(AbstractJSoup.textOfFirstElement(e.select("td > div > a > span")));
		// m.setTag(tag);
		// m.setUrl(url);
		return m;
	}

	public List<Movie> mapper(List<Element> s) {
		List<Movie> ms = new ArrayList<Movie>();
		for (Element e : s) {
			ms.add(mapper(e));
		}
		return ms;
	}

}
