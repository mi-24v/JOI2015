package com.java.joi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Main{
	private static ArrayList<Integer> Opecounts;
	private static ArrayList<ArrayList<String>> OldFlag;
	private static LinkedHashMap<ArrayList<Integer>,Integer> alexe = new LinkedHashMap<>();
	private static int Width,Height;
	public static void main(String[] args){
		GeneralIO g = new GeneralIO();
		g.interactive();
		ArrayList<ArrayList<String>> rawdata = g.getInputdata();
		rawdata.forEach(a -> {a.forEach(s -> System.out.print(s));System.out.println();});
		Width=Integer.parseInt(rawdata.get(0).get(1));
		Height =Integer.parseInt(rawdata.get(0).get(0));
		rawdata.remove(0);
		OldFlag = rawdata;
		Opecounts = new ArrayList<>();
		Opecounts.add(nurikae(1,1,Height-2,Integer.MAX_VALUE));
		Collections.sort(Opecounts);
		int minOpe = Opecounts.get(0);
		ArrayList<ArrayList<String>> ans = new ArrayList<>();
		ans.add(new ArrayList<String>(){{add(Integer.toString(minOpe));}});
		g.setOutputdata(ans);
		g.output();
	}

	private static int nurikae(int Wcount,int Bcount,int Rcount,int minope){
		System.out.println("minope:"+minope);
		ArrayList<Integer> exe = new ArrayList<Integer>(){{add(Wcount);add(Bcount);add(Rcount);}};
		System.out.println("args:"+exe.toString());
		if(Wcount + Bcount + Rcount > Height && Wcount ==Bcount && Bcount == Rcount){
			System.out.println("[returned]");
			return minope;
		}else if(Rcount == 0){
			nurikae(1,Height-2,1,minope);
		}else if(Bcount == 0){
			nurikae(Height-2,1,1,minope);
		}else if(Wcount == 0){
			nurikae(2,2,1,minope);
		}else if(Wcount + Bcount > Height -Rcount && Bcount == Wcount){
			nurikae(1,2,2,minope);
		}else if(Bcount + Rcount > Height -Wcount && Bcount == Rcount){
			nurikae(2,1,2,minope);
		}else if(Wcount + Rcount > Height -Bcount && Wcount == Rcount){
			nurikae(1,1,1,minope);
		}
		int ope = Integer.MAX_VALUE;
		if(alexe.containsKey(exe)){
			System.out.println("[same thing]");
			ope = Math.min(minope,alexe.get(exe));
		}else if(Wcount < Height && Bcount <Height && Rcount < Height){
			ope = 0;
			for(int i=0;i<Wcount;i++){
				ArrayList<String> line = OldFlag.get(i);
				ope += nurikae(line,"W");
			}
			for(int i=Wcount;i<Bcount+Wcount;i++){
				ArrayList<String> line = OldFlag.get(i);
				ope += nurikae(line,"B");
			}
			for(int i=Bcount+Wcount;i<Height;i++){
				ArrayList<String> line = OldFlag.get(i);
				ope += nurikae(line,"R");
			}
			System.out.println("ope:"+ope);
			alexe.put(exe, ope);
			ope = Math.min(ope, minope);
		}else{
			System.err.println("over!");
			if(Wcount >= Height && Bcount >= Height && Rcount >= Height){
				System.out.println("[returned]");
				return minope;
			}else if(Wcount >= Height){
				return nurikae(1,Bcount,Rcount,minope);
			}else if(Bcount >= Height){
				return nurikae(Wcount,1,Rcount,minope);
			}else if(Rcount >= Height){
				return nurikae(Wcount,Bcount,1,minope);
			}else{
				System.err.println("returned");
				return Math.min(minope, ope);
			}
		}
		if(Wcount == 1 && Bcount == 1&& Rcount ==1){
			return nurikae(Wcount+1,Bcount+1,Rcount+1,ope);
		}else if(Wcount == Bcount && Rcount == 1){
			return nurikae(Wcount+1,Bcount+1,Rcount,ope);
		}else if(Wcount == 1){
			return nurikae(Wcount,Bcount+1,Rcount-1,ope);
		}else if(Rcount == 1){
			return nurikae(Wcount+1,Bcount-1,Rcount,ope);
		}else if(Bcount == 1){
			return nurikae(Wcount-1,Bcount,Rcount+1,ope);
		}else if(Bcount == Rcount || Wcount == 1){
			return nurikae(Wcount,Bcount+1,Rcount+1,ope);
		}else if(Wcount == Rcount || Bcount == 1){
			return nurikae(Wcount+1,Bcount,Rcount+1,ope);
		}else{
			System.err.println("returned");
			return Math.min(minope, ope);
		}
	}

	private static int nurikae(ArrayList<String> line, String s){
		return (int) (Width - line.stream().filter(str -> str.equals(s)).collect(Collectors.toList()).size());
	}

}