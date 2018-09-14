package com.tutorialspoint.lucene;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class JsoupExtract {

	public static final String OutputFilePath = "D:\\Adv Comp Assignments\\Data Files\\LinksList_BBC.txt";
	public static final String OutputFilePath_Mapping_WS = "D:\\Adv Comp Assignments\\Data Files\\Mapping URL_FileName_BBC.txt";

	static TST<String> tst = new TST<String>();

	// pass url as input; print all hrefs available in tht page and more to a file 
	public static void getFileList(String url) throws IOException
	{

		double st_time = System.currentTimeMillis();
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("a[href]");
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(OutputFilePath));
		int count = links.size();
		for (Element link : links)
		{
			writer.write(link.attr("abs:href"));//System.out.println( link.attr("abs:href"));
			writer.newLine();
		}
		System.out.println("Done writing links to file : " + OutputFilePath + " \nCount is = "+count);
		writer.close();
		System.out.println("Total time for file list extract = " + ( System.currentTimeMillis()-st_time )+"ms" );

	}

	public static void getHTMLFiles_mapping(String FilePath) throws IOException
	{
		BufferedReader reader = null;
		double st_time = System.currentTimeMillis();
		reader = new BufferedReader(new FileReader(FilePath));
		String CurrentLine = reader.readLine();
		while(CurrentLine!=null )
		{
			try {
				String tempFileName = CurrentLine.replaceAll("\\W", "");
				Map_URL_File(CurrentLine,tempFileName);
			}
			catch(Exception e)
			{
				;
			}

			CurrentLine = reader.readLine();
		}
		reader.close();
		System.out.println("Done reading list and creating mapping file ");
		System.out.println("Total time for creating a maping file = " + ( System.currentTimeMillis()-st_time )+"ms" );
	}
	public static void Map_URL_File(String url, String FileName) throws Exception
	{
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(OutputFilePath_Mapping_WS,true));
		writer.newLine();
		writer.write(FileName +"	"+ url );
		writer.close();
	}
	public static void createTST(String filepath) throws Exception
	{
		BufferedReader reader = null; 
		double st_time = System.currentTimeMillis();

		reader = new BufferedReader(new FileReader(filepath));

		String currentLine = reader.readLine();
		while (currentLine != null) 
		{
			String[] split = currentLine.split("\\s");
			if (split.length== 2)
			{
				tst.put(split[0],split[1]);
			}
			currentLine = reader.readLine();
		}
		reader.close();

		System.out.println("Total time for creating tst = " + ( System.currentTimeMillis()-st_time )+"ms" );

	}
	public static void downloadHTMLFiles(String Mappingfilepath,String dataDir) throws Exception
	{
		BufferedWriter writer = null;
		createTST(Mappingfilepath);
		double st_time = System.currentTimeMillis();
		System.out.println("Size of TST = " + tst.size());
		
		for(String str : tst.keys())
		{
			try {
				//System.out.println("TST Key = " + str  + " TST VALUE = " + tst.get(str));
				Document doc = Jsoup.connect(tst.get(str)).get();
				Document document = Jsoup.parse(doc.html());
				String text = new HtmlToPlainText().getPlainText(document);

				writer = new BufferedWriter(new FileWriter(dataDir +str+".txt"));
				writer.write(text); 
				writer.close();
			}
			catch(Exception e)
			{
				System.out.println("caught Err =  " + e.getMessage());
			}
		}
		System.out.println("Done downloading html files ");
		System.out.println("Total time for downloading = " + ( System.currentTimeMillis()-st_time )+"ms" );
	}
	public static void main(String[] args) throws Exception {
		double st_time_whole = System.currentTimeMillis();
		String url = "http://www.bbc.com/news";
		//String url = "http://windsorstar.com/";
		//Step 1 : Get all URL's  present in home page and write it to a file 
		//getFileList(url);
		//Step 2 : Create a mapping of URL and file name 
		getHTMLFiles_mapping(OutputFilePath); //uses Map_URL_File to write 
		//Step 3 : Create a file and write the html text to it recursively for all files i.e download all files 
		downloadHTMLFiles(OutputFilePath_Mapping_WS,LuceneConstants.dataDir_BBC);
		System.out.println("Total time for whole extract = " + ( System.currentTimeMillis()-st_time_whole)+"ms" );
	}

}
