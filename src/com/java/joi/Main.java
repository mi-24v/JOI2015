package com.java.joi;

import java.util.ArrayList;

public class Main{
	public static void main(String[] args){
		GeneralIO g = new GeneralIO();
		g.interactive();
		ArrayList<ArrayList<String>> rawdata = g.getInputdata();
		rawdata.forEach(a -> {a.forEach(s -> System.out.print(s));System.out.println();});

	}
}