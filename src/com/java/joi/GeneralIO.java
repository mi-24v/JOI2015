package com.java.joi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GeneralIO {
	public static final String ERR_INVALIDNAME = "invalid name,please retry";
	public static final Charset FILEFORMAT = StandardCharsets.UTF_8;
	public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

	private BufferedReader stdin;
	private ArrayList<ArrayList<String>> inputfiledata,outputfiledata;

	public GeneralIO(){
		stdin = new BufferedReader(new InputStreamReader(System.in));
		inputfiledata = new ArrayList<>();
	}

	public void interactive(){
		String query = null;
		System.out.println("What's the path?");
		try{
			query = stdin.readLine();
			if(query.contains("http")){
				URL url = new URL(query);
				download(url);
			}else{
				perseFile(query);
			}
		}catch(IOException e){
			e.printStackTrace();
			System.err.println("input error");
			System.exit(1);
		}
	}

	private void download(URL url){
		try{
			HttpURLConnection con = (HttpURLConnection)url.openConnection();

			//設定
			//対話形式の無効化
			con.setAllowUserInteraction(false);
			//リダイレクトには従う
			con.setInstanceFollowRedirects(true);
			//GETメソッドを使用
			con.setRequestMethod("GET");
			//キャッシュを使用しない
			con.setUseCaches(false);

			//接続
			con.connect();
			//接続の確認
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
				//データ取得 -> File出力
				BufferedReader in =
						new BufferedReader(new InputStreamReader
								(con.getInputStream()));
				File outfile = new File("input_"+LocalDateTime.now().format(TIME_FORMAT)+".txt");
				if(!outfile.exists()){outfile.createNewFile();}
				BufferedWriter out = new BufferedWriter(
						new FileWriter(outfile));
				in.lines().forEach(line -> {
					this.inputfiledata.add(new ArrayList<String>(Arrays.asList(line.split("\\s"))));
					try {
						out.write(line);
						out.newLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				out.flush();
				in.close();
				out.close();
			}else{//エラー
				System.err.println("Error " + con.getResponseCode());
				//さいしょにもどる
				this.interactive();
				return;
			}
		}catch(MalformedURLException e){
			e.printStackTrace();
			System.err.println("invalid URL,please retry");
			this.interactive();
		}catch(ProtocolException e){
			e.printStackTrace();
			System.err.println("invalid protocol,please retry");
			this.interactive();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private void perseFile(String filename){
		File tgtfile = new File(filename);
		if(!tgtfile.exists()){
			System.err.println(ERR_INVALIDNAME);
			interactive();
			return;
		}else{
			try {
				ArrayList<String> raw = (ArrayList<String>)Files.readAllLines(tgtfile.toPath(), FILEFORMAT);
				inputfiledata = raw.stream().map(line -> new ArrayList<String>(Arrays.asList(line.split("\\s")))).collect(Collectors.toCollection(ArrayList::new));
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("read error, please retry");
				interactive();
				return;
			}
		}
	}

	public void output(){
		File outfile = new File("output_"+LocalDateTime.now().format(TIME_FORMAT)+".txt");
		try{outfile.createNewFile();}catch(IOException e){e.printStackTrace();}
		try(BufferedWriter outer = new BufferedWriter(new FileWriter(outfile))){
			for(ArrayList<String> array : this.outputfiledata){
				for(String s : array){
					outer.write(s);
					if(array.indexOf(s) == array.size()-1){
						outer.newLine();
					}else{
						outer.write(" ");
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public ArrayList<ArrayList<String>> getInputdata(){
		return this.inputfiledata;
	}

	public void setOutputdata(ArrayList<ArrayList<String>> data){
		this.outputfiledata = data;
	}

}
