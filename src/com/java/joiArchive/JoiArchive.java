package com.java.joiArchive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import com.java.joi.GeneralIO;

public class JoiArchive {

	//「和と差」のもんだい(練習ページとい1)
	@SuppressWarnings("serial")
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

	//「水道料金」2014年とい1
	@SuppressWarnings("serial")
	public void WaterRate(){
		GeneralIO g = new GeneralIO();
		g.interactive();
		ArrayList<ArrayList<String>> rawdata = g.getInputdata();
		rawdata.forEach(a -> {a.forEach(s -> System.out.print(s));System.out.println();});
		final int XperYen = Integer.parseInt(rawdata.get(0).get(0));
		final int YconstYen = Integer.parseInt(rawdata.get(1).get(0));
		final int YmaxWater = Integer.parseInt(rawdata.get(2).get(0));
		final int YperYen = Integer.parseInt(rawdata.get(3).get(0));
		final int TotalWater = Integer.parseInt(rawdata.get(4).get(0));
		final int ans;
		if(TotalWater > YmaxWater){
			if(YperYen > XperYen && XperYen < YconstYen){
				ans = XperYen * TotalWater;
			}else{
				ans = YconstYen + YperYen * (TotalWater - YmaxWater);
			}
		}else{
			if(XperYen > YconstYen){
				ans = YconstYen;
			}else if(XperYen > YperYen){
				ans = YconstYen + YperYen * (TotalWater - YmaxWater);
			}else{
				ans = YconstYen + XperYen * (TotalWater - YmaxWater);
			}
		}
		g.setOutputdata(new ArrayList<ArrayList<String>>(){{
			add(new ArrayList<String>(){{add(Integer.toString(ans));}});
		}});
		g.output();
	}

	//「クリスマスパーティー」2014とい2
	@SuppressWarnings("serial")
	public void ChristmasParty(){
		GeneralIO g = new GeneralIO();
		g.interactive();
		ArrayList<ArrayList<String>> rawdata = g.getInputdata();
		rawdata.forEach(a -> {a.forEach(s -> System.out.print(s));System.out.println();});
		final int friends = Integer.parseInt(rawdata.get(0).get(0));
		final int games = Integer.parseInt(rawdata.get(1).get(0));
		final ArrayList<Integer> targets = rawdata.get(2).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toCollection(ArrayList<Integer>::new));
		ArrayList<ArrayList<Integer>> gamedata = new ArrayList<>();
		ArrayList<Integer> result = new ArrayList<>(friends);
		for(int i = 0; i<friends; i++){result.add(0);}
		for (int i = 3; i < games+3; i++) {
			gamedata.add(rawdata.get(i).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toCollection(ArrayList<Integer>::new)));
		}
		System.out.println("targets:"+targets);
		for(int i = 0; i < games; i++){
			final int target = targets.get(i);
			int miss = 0;
			ArrayList<Integer> data = gamedata.get(i);
			System.out.println("data:"+data.toString()+" target:"+target);
			for (int j = 0; j < data.size(); j++) {
				if(data.get(j) == target){
					System.out.println("match:"+result.get(j));
					result.set(j, result.get(j) + 1);
					System.out.println("->"+result.get(j));
				}else{
					miss++;
				}
			}
			result.set(target-1, result.get(target-1) + miss);
		}
		ArrayList<ArrayList<String>> ans = new ArrayList<>();
		result.forEach(r -> ans.add(new ArrayList<String>(){{add(Integer.toString(r));}}));
		g.setOutputdata(ans);
		g.output();
	}

	//2015よせん1もんめ
	@SuppressWarnings("serial")
	public void SelectSubjects(){
		GeneralIO g = new GeneralIO();
		g.interactive();
		ArrayList<ArrayList<String>> rawdata = g.getInputdata();
		rawdata.forEach(a -> {a.forEach(s -> System.out.print(s));System.out.println();});
		ArrayList<Integer> science = new ArrayList<Integer>(){{
			add(Integer.parseInt(rawdata.get(0).get(0)));
			add(Integer.parseInt(rawdata.get(1).get(0)));
			add(Integer.parseInt(rawdata.get(2).get(0)));
			add(Integer.parseInt(rawdata.get(3).get(0)));
		}};
		ArrayList<Integer> social = new ArrayList<Integer>(){{
			add(Integer.parseInt(rawdata.get(4).get(0)));
			add(Integer.parseInt(rawdata.get(5).get(0)));
		}};
		Collections.sort(science);
		Collections.sort(social);
		int sciencesum=0,socialsum=0;
		for(int i = science.size()-1;i>0;i--){
			sciencesum += science.get(i);
		}
		for(int i = social.size()-1;i>0;i--){
			socialsum += social.get(i);
		}
		final String ansstr = Integer.toString(sciencesum+socialsum);
		ArrayList<ArrayList<String>> ans = new ArrayList<>();
		ans.add(new ArrayList<String>(){{add(ansstr);}});
		g.setOutputdata(ans);
		g.output();
	}

	//2015よせん2もんめ
	@SuppressWarnings("serial")
	public void SwappingBips(){
		GeneralIO g = new GeneralIO();
		g.interactive();
		ArrayList<ArrayList<String>> rawdata = g.getInputdata();
		rawdata.forEach(a -> {a.forEach(s -> System.out.print(s));System.out.println();});
		final int students = Integer.parseInt(rawdata.get(0).get(0));
		final int totalbaton = Integer.parseInt(rawdata.get(0).get(1));
		ArrayList<Integer> zekkens = new ArrayList<>();
		for(int i = 1; i <= students; i++){
			zekkens.add(Integer.parseInt(rawdata.get(i).get(0)));
		}
		for(int i=1;i<=totalbaton;i++){
			for(int j=0;j<zekkens.size();j++){
				if(j < zekkens.size()-1){
					int amari1 = i % zekkens.get(j),
						amari2 = i % zekkens.get(j+1);
					if(amari1 > amari2){
						int tmp = zekkens.get(j);
						zekkens.set(j, zekkens.get(j+1));
						zekkens.set(j+1, tmp);
					}
				}
			}
		}
		ArrayList<ArrayList<String>> ans = new ArrayList<>();
		for(int i=0;i<zekkens.size();i++){
			final int zekken = zekkens.get(i);
			ans.add(new ArrayList<String>(){{add(Integer.toString(zekken));}});
		}
		g.setOutputdata(ans);
		g.output();
	}

}
