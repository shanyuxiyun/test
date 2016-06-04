package com.lee.jsoup.ext;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public abstract class AbstractJSoup {

	private String baseUri;

	public String getBaseUri() {
		return baseUri;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	protected String buildURI(String urlParam) {
		return getBaseUri() + urlParam;
	}

	protected abstract Document buildDocument(String urlParam,String cookieValue);

	public static  String textOfFirstElement(Elements elements) {
		if (elements.size() > 0) {
			return elements.get(0).text();
		}
		return "";
	}

}
