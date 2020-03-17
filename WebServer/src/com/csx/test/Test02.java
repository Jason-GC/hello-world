package com.csx.test;

import java.util.List;

import com.csx.basic.normalclass.Person;
import com.csx.basic.tool.SAXParserXMLHandler;

public class Test02 {

	public static void main(String[] args) {
		
		List<Person> persons = SAXParserXMLHandler.ParseProcess("com/csx/xml/personalMessages.xml");
		
		for(Person person : persons) {
			System.out.println("message: "+person.toString());
		}
		
	}

}
