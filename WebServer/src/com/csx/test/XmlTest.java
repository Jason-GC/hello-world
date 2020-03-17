package com.csx.test;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.csx.basic.normalclass.Person;
import com.csx.webserver.CsxSaxParseXml;

public class XmlTest {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		List<Person> persons = CsxSaxParseXml.CsxHandler("com/csx/webserver/person.xml");
		
		for(Person person : persons) {
			System.out.println("信息: "+person.toString());
		}
	}

}
