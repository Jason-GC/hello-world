package com.csx.webserver;

import java.lang.reflect.InvocationTargetException;

/**
 * 反射测试
 * 01.获取Class对象
 * 
 * 
 * @author Cuisx
 *
 */
public class ReflectTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		//01.获取Class对象
		Iphone iph = new Iphone();
		Class cla = iph.getClass();
		
		Class cla2 = Class.forName("com.csx.webserver.Iphone");
		
		//02创建对象
		Iphone iph1 = (Iphone) cla.newInstance();
		System.out.println("iph1 = " + iph1);
		
		Iphone iph2 = (Iphone) cla2.getConstructor().newInstance();
		System.out.println("iph2 = " + iph2);
		System.out.println("cla2.getName() = " + cla2.getName());
		
	}

}

class Iphone{
	public Iphone() {
		
	}
}