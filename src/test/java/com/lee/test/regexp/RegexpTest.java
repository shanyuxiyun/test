package com.lee.test.regexp;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexpTest {

	@Test
	public void test1() {
		String phone = "0086-158-5055-1234";
		Pattern p = Pattern.compile("(\\+?\\d{4}|\\+?\\d{2})?\\-?(\\d{3})\\-?(\\d{4})\\-?(\\d{4})");

		Matcher m = p.matcher(phone);
		// must call this
		assertEquals(true, m.matches());
		assertEquals(4, m.groupCount());
		assertEquals("0086", m.group(1));
		assertEquals("158", m.group(2));
		assertEquals("5055", m.group(3));
		assertEquals("1234", m.group(4));

		phone = "008615850551234";
		m = p.matcher(phone);
		// must call this
		assertEquals(true, m.matches());
		assertEquals(4, m.groupCount());
		assertEquals("0086", m.group(1));
		assertEquals("158", m.group(2));
		assertEquals("5055", m.group(3));
		assertEquals("1234", m.group(4));

		phone = "8615850551234";
		m = p.matcher(phone);
		// must call this
		assertEquals(true, m.matches());
		assertEquals(4, m.groupCount());
		assertEquals("86", m.group(1));
		assertEquals("158", m.group(2));
		assertEquals("5055", m.group(3));
		assertEquals("1234", m.group(4));

		phone = "+008615850551234";
		m = p.matcher(phone);
		// must call this
		assertEquals(true, m.matches());
		assertEquals(4, m.groupCount());
		assertEquals("+0086", m.group(1));
		assertEquals("158", m.group(2));
		assertEquals("5055", m.group(3));
		assertEquals("1234", m.group(4));

		phone = "+8615850551234";
		m = p.matcher(phone);
		// must call this
		assertEquals(true, m.matches());
		assertEquals(4, m.groupCount());
		assertEquals("+86", m.group(1));
		assertEquals("158", m.group(2));
		assertEquals("5055", m.group(3));
		assertEquals("1234", m.group(4));

	}

	@Test
	public void test2() {
		String line = "*.png;*.jpg,*.jpeg *.gif";
		Pattern p = Pattern.compile("[\\s;,]");
		String smalls[] = p.split(line);

		assertEquals("*.png", smalls[0]);
		assertEquals("*.jpg", smalls[1]);
		assertEquals("*.jpeg", smalls[2]);
		assertEquals("*.gif", smalls[3]);
	}

	@Test
	public void test3() {
		String ss[] = { "abc", "Abc", "aBc" };
		Pattern p = Pattern.compile("abc", Pattern.CASE_INSENSITIVE);
		Matcher m = null;
		for (String s : ss) {
			m = p.matcher(s);
			assertEquals(true, m.matches());
		}
		m = p.matcher("Abcd");
		assertEquals(false, m.matches());
	}

	@Test
	public void test4() {
		String xml = "<name>lee</name><age>22</age><sex>male</sex>";

		// \\1 引用第一个分组
		Pattern p = Pattern.compile("<(\\w+)>(\\w+)</\\1>");
		Matcher m = p.matcher(xml);

		assertEquals(true, m.find());
		assertEquals("lee", m.group(2));

		assertEquals(true, m.find());
		assertEquals("22", m.group(2));

		assertEquals(true, m.find());
		assertEquals("male", m.group(2));
	}

	@Test
	public void test5() {
		String s = "age30 name22 is a girl";
		Pattern p = Pattern.compile("(\\w+\\d+)");
		Matcher m = p.matcher(s);

		StringBuffer sb = new StringBuffer();

		// age30
		assertEquals(true, m.find());
		m.appendReplacement(sb, m.group(1).toUpperCase());

		// name22
		assertEquals(true, m.find());
		m.appendReplacement(sb, m.group(1).toUpperCase());

		assertEquals("AGE30 NAME22", sb.toString());

		m.appendTail(sb);

		assertEquals("AGE30 NAME22 is a girl", sb.toString());
	}

	@Test
	public void test6() {
		Matcher m = Pattern.compile("a;*b").matcher("a;;;b;;;;;;b;;;;;;;;;");
		assertEquals(true, m.find());
		assertEquals("a;;;b", m.group());
	}
	
	@Test
	public void test7() {
		// 量词 默认 贪婪
		// 从右向左去掉不匹配的
		Matcher m = Pattern.compile("a.*b").matcher("a;;;b;;;;;;b;;;;;;;;;");
		assertEquals(true, m.find());
		assertEquals("a;;;b;;;;;;b", m.group());
	}
	
	@Test
	public void test8() {
		// 量词  ? 不贪婪
		// 从左向右最小匹配
		Matcher m = Pattern.compile("a.*?b").matcher("a;;;b;;;;;;b;;;;;;;;;");
		assertEquals(true, m.find());
		assertEquals("a;;;b", m.group());
	}
	
	@Test
	public void test9() {
		// 量词 + 不贪婪,从右向左只匹配一次
		Matcher m = Pattern.compile("a.*+b").matcher("a;;;;;;;;;b;;;;;;;;;");
		assertEquals(false, m.find());
		
		// http://docs.oracle.com/javase/tutorial/essential/regex/quant.html
		m = Pattern.compile("a.*+").matcher("a;;;;;;;;;b");
		assertEquals(true, m.find());
		assertEquals("a;;;;;;;;;b", m.group());
	}
	
	@Test
	public void test10() {
		Matcher m = Pattern.compile("a.*?b").matcher("a;;;ba;;;;;;ba;;;;;;;;;b");
		assertEquals(true, m.find());
		assertEquals("a;;;b", m.group());
		assertEquals(0, m.start());
		assertEquals(5, m.end());
		
		assertEquals(true, m.find());
		assertEquals("a;;;;;;b", m.group());
		assertEquals(5, m.start());
		assertEquals(13, m.end());
		
		
		assertEquals(true, m.find());
		assertEquals("a;;;;;;;;;b", m.group());
	}
	
	
	@Test
	public void test11() {
		// 不捕获分组 ?:
		Matcher m = Pattern.compile("(\\d*)(?:\\.)(\\d{2})[￥$]$").matcher("9876.45$");
		assertEquals(true, m.matches());
		assertEquals("9876", m.group(1));
		assertEquals("45", m.group(2));
	}
	
	@Test
	public void test12() {
		// 不捕获分组 
		//http://stackoverflow.com/questions/22937618/reference-what-does-this-regex-mean/22944075#22944075
		
		// 1.1
		Matcher m = Pattern.compile("12(?=34)").matcher("1234");
		assertEquals(true, m.find());
		assertEquals("12", m.group());
		
		//1.2
		m = Pattern.compile("12(?!34)").matcher("1234");
		assertEquals(false, m.find());
		
		//1.3
		m = Pattern.compile("12(?!34)").matcher("1224");
		assertEquals(true, m.find());
		assertEquals("12", m.group());
		
		//2.1
		m = Pattern.compile("(?<=00)12").matcher("0012");
		assertEquals(true, m.find());
		assertEquals("12", m.group());
		
		//2.2
		m = Pattern.compile("(?<!00)12").matcher("9012");
		assertEquals(true, m.find());
		assertEquals("12", m.group());
		
		// equals to 1.1
		m = Pattern.compile("(?=1234)12").matcher("1234");
		assertEquals(true, m.find());
		assertEquals("12", m.group());
		
	}

}
