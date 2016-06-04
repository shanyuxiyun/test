package com.lee.jsoup.ext;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSoupExt extends AbstractJSoup {

	public Document buildDocument(String urlParam, String cookieValue) {
		try {
			String request = buildURI(urlParam);
			System.out.println("request - > " + request);
			return Jsoup.connect(request).header("Cookie", cookieValue).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
