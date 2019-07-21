package com.automationpractice.core.pages;



public class StringCount {



	public static void main(String args[]) {
		String value = "1.431,38 €";
		String[] aka = value.split("€");
		System.out.println(aka[0]);
		String  mek2= aka[0].replace(".","");
		System.out.println(mek2);
		String mek = mek2.replace(",",".");
		System.out.println(mek);
		

	}
}
