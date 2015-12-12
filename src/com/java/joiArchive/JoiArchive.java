package com.java.joiArchive;

import java.util.ArrayList;

import com.java.joi.GeneralIO;

public class JoiArchive {

	//「和と差」のもんだい(練習ページとい1)
	public void AdditionSubtraction(){
		GeneralIO g = new GeneralIO();
		g.interactive();
		g.getInputdata().forEach(a -> {a.forEach(s -> System.out.print(s));System.out.println();});
		ArrayList<ArrayList<String>> rawdata = g.getInputdata();
		ArrayList<ArrayList<String>>ans = new ArrayList<ArrayList<String>>(){{
			add(new ArrayList<String>(){{
				add(Integer.toString(Integer.parseInt(rawdata.get(0).get(0))+Integer.parseInt(rawdata.get(0).get(1))));
				}});
			add(new ArrayList<String>(){{
				add(Integer.toString(Integer.parseInt(rawdata.get(0).get(0))-Integer.parseInt(rawdata.get(0).get(1))));
			}});
		}};
		g.setOutputdata(ans);
		g.output();
	}

}
