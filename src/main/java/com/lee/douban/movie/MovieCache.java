package com.lee.douban.movie;

import java.util.concurrent.ConcurrentHashMap;

import org.jsoup.select.Elements;

public class MovieCache {

	private static final ConcurrentHashMap<String, Elements> CACHE = new ConcurrentHashMap<String, Elements>();

	private static final ConcurrentHashMap<String, String> CURRENT_HANDLING_TAGS = new ConcurrentHashMap<String, String>();

	public static void putTag(String tag) {
		CURRENT_HANDLING_TAGS.put(tag, tag);
	}

	public static void removeTag(String tag) {
		CACHE.remove(tag);
		CURRENT_HANDLING_TAGS.remove(tag);
		System.out.println("tag " + tag + " removed.");
	}

	public static boolean hasTag(String tag) {
		return CURRENT_HANDLING_TAGS.containsKey(tag);
	}

	public static void add(String tag, Elements elements) {
		if (CACHE.containsKey(tag)) {
			addElements(tag, elements);
		} else {
			synchronized (CACHE) {
				if (!CACHE.containsKey(tag)) {
					CACHE.put(tag, elements);
				} else {
					addElements(tag, elements);
				}
			}
		}
	}

	public static Elements nextElementsByTag(String tag) {
		Elements elements = CACHE.get(tag);
		if (elements == null) {
			return new Elements();
		}
		Elements result = new Elements(elements);
		CACHE.put(tag, new Elements());
		return result;
	}

	private static void addElements(String tag, Elements elements) {
		Elements old = CACHE.get(tag);
		old.addAll(elements);
		CACHE.put(tag, old);
	}

}
