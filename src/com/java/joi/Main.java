package com.java.joi;

//でばっぐとかにつかうよう
public class Main{
	public static void main(String[] args){
		GeneralIO g = new GeneralIO();
		g.interactive();
		g.getInputdata().forEach(a -> {a.forEach(s -> System.out.print(s));System.out.println();});
		g.setOutputdata(g.getInputdata());
		g.output();
	}
}