package com.lee.douban.util;

import java.util.Random;

public abstract class CookUtil {

	private static final char[] CHARS = new char[52];

	private static final String COOKIE_VALUE = "bid={}; ll\"118159\"; __utma=30149280.1984884674.1463926942.1464492354.1464497534.7; __utmz=30149280.1464492354.6.2.utmcsr=douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/; __utma=223695111.1726480643.1463926942.1464492368.1464497534.7; __utmz=223695111.1464442801.4.3.utmcsr=douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1464497531%2C%22https%3A%2F%2Fwww.douban.com%2F%22%5D; _pk_id.100001.4cf6=d7e535fa90337ef8.1463926942.7.1464498275.1464494900.; __utmc=30149280; __utmc=223695111; ct=y; _pk_ses.100001.4cf6=*; __utmb=30149280.0.10.1464497534; __utmb=223695111.0.10.1464497534";

	static {
		for (int i = 0; i < 26; i++) {
			CHARS[i] = (char) ('A' + i);
			CHARS[26 + i] = (char) (CHARS[i] + 32);
		}
	}

	private static String generateBID() {
		StringBuffer sb = new StringBuffer();

		sb.append(new Random().nextInt(10));
		for (int i = 0; i < 10; i++) {
			sb.append(CHARS[new Random().nextInt(52)]);
		}
		return sb.toString();
	}

	public static String randomCookie() {
		return COOKIE_VALUE.replace("{}", generateBID());
	}
}
