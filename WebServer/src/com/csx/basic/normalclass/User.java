package com.csx.basic.normalclass;

public class User {

	private static String name;
	private static String gender;
	private static int age;
	private static String maritalStatus;
	private static String address;

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		User.name = name;
	}

	public static String getGender() {
		return gender;
	}

	public static void setGender(String gender) {
		User.gender = gender;
	}

	public static int getAge() {
		return age;
	}

	public static void setAge(int age) {
		User.age = age;
	}

	public static String getMaritalStatus() {
		return maritalStatus;
	}

	public static void setMaritalStatus(String maritalStatus) {
		User.maritalStatus = maritalStatus;
	}

	public static String getAddress() {
		return address;
	}

	public static void setAddress(String address) {
		User.address = address;
	}

	@Override
	public String toString() {
		return this.name + ", " + this.gender + ", " + this.age + ", " + this.maritalStatus + ", " + this.address
				+ ". ";
	}

}
