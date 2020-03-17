package com.csx.test;

import com.csx.basic.exception.MyException;

public class TestError {

	public static void main(String[] args) throws Exception {
		
		int arr[] = new int[] {1,2,3};
		
		
		for(int i : arr) {
			System.out.println("i: "+i);
		}
		
	}

}
