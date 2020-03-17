package com.csx.webserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.csx.basic.normalclass.Person;

/**
 * 
 * 适用于解析拥有以下标签的XML文件 name age gender
 * 
 * @author Cuisx
 *
 */
public class CsxSaxParseXml extends DefaultHandler {

	private List<Person> persons;
	private Person person;
	private String tag;
	
	/**
	 * 
	 * @param xmlFlieName XML文件名称
	 * @return 结果集
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<Person> CsxHandler(String xmlFlieName) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		SAXParser parser = factory.newSAXParser();
		
		CsxSaxParseXml myHandler = new CsxSaxParseXml();
		
		parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlFlieName), 
				myHandler);
		
		List<Person> persons = myHandler.getPersons();
		
		return persons;
	}
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("解析XML文档开始");
		persons = new ArrayList<Person>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		System.out.println("解析标签" + qName + "开始");
		if (qName != null) {
			tag = qName;
		}
		if (tag != null) {
			if ("person".equals(tag)) {
				person = new Person();
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (tag != null) {
			String contents = new String(ch, start, length).trim();

			if ("name".equals(tag)) {
				person.setName(contents);
			} else if ("age".equals(tag)) {
				person.setAge(contents);
			} else if ("gender".equals(tag)) {
				person.setGender(contents);
			}

		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("解析标签" + qName + "结束");

		if ("person".equals(qName)) {
			persons.add(person);
			person = null;
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
