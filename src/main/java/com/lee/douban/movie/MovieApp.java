package com.lee.douban.movie;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MovieApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
		MovieWalker mw = applicationContext.getBean("movie.walker", MovieWalker.class);
		mw.launch();
		applicationContext.close();
	}

}
