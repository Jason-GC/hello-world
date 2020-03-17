package com.csx.basic.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.csx.basic.normalclass.Person;

/**
 * 
 * 此类适用于解析拥有message父标签 name、age、gender、address子标签的XML文件
 * 
 * SAX解析XML文档步骤：
 * 01 获取SAX解析工厂
 * 02 从解析工厂获取解析器
 * 03 编写处理器(根据XML文档标签实际编写)
 * 		a 继承DefaultHandler类
 * 		b 重写startDocument() endDocument() startElement() endElement() characters()方法
 * 04 加载处理器
 * 05 解析文档
 * 
 * @author Cuisx
 *
 */
public class SAXParserXMLHandler extends DefaultHandler {

	private static List<Person> persons;
	private Person person;
	private String tag;// 存储标签名称

	/**
	 * 
	 * 解析处理
	 * 
	 * @param FileName: XML文件包名加文件名
	 * @return 结果集
	 */
	public static List<Person> ParseProcess(String FileName) {

		SAXParserXMLHandler handler = new SAXParserXMLHandler();

		try {
			SAXParserFactory.newInstance().newSAXParser()
					.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(FileName), handler);

		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.err.println("解析处理器出现未知错误!!!");
			e.printStackTrace();
		}

		return handler.getPersons();
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("解析XML文档开始");
		persons = new ArrayList<Person>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("解析标签/元素" + qName + "开始");
		
		if (qName != null) {
			tag = qName;
		}
		if ("message".equals(tag)) {
			person = new Person();
		}
		
		if("message".equals(qName)) {
			for(int i=0; i<attributes.getLength(); i++) {
				if("id".equals(attributes.getQName(i))) {
					person.setId(attributes.getValue(i));
					System.out.println(qName+"标签属性为: "+attributes.getQName(i)+attributes.getValue(i));
				}
			}
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		String content = new String(ch, start, length).trim();
		if (content.length() > 0) {
			if ("name".equals(tag)) {
				person.setName(content);
			} else if ("gender".equals(tag)) {
				person.setGender(content);
			} else if ("age".equals(tag)) {
				person.setAge(content);
			} else if ("address".equals(tag)) {
				person.setAddress(content);
			}
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("解析标签/元素" + qName + "结束");
		if ("message".equals(qName)) {
			persons.add(person);
		}

		tag = null;
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("解析XML文档结束");
	}

	public List<Person> getPersons() {
		return persons;
	}

}
