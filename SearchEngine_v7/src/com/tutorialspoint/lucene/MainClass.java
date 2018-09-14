package com.tutorialspoint.lucene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.misc.HighFreqTerms;
import org.apache.lucene.misc.TermStats;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
public class MainClass 
{
	static String IndexDir = "";
	static String MappingFile = "";
	static String dataDir = "D:\\Adv Comp Assignments\\Data Files\\JSOUPFiles";
	final static File folder = new File("D:\\Adv Comp Assignments\\Data Files\\MY TEXT");
	//static Indexer indexer;
	static TopDocs results;
	Searcher searcher;
	static TST<String> tst = new TST<String>();
	static IndexReader reader;
	IndexSearcher indexsearcher;

	public String[] main(String args,String DS,String SearchType) throws Exception 
	{
		
		String str_args = args ;//"Tim Hortons";//args[0];
		//String IndexDir = "";
		String Results[]= {};
		//createIndex();
		System.out.println("args = " + args +"DS = " + DS +"SearchType = " + SearchType  );
		if (DS.equalsIgnoreCase("CNN"))
		{
			IndexDir = "D:\\Adv Comp Assignments\\Data Files\\DataIndex_CNN";
			MappingFile = LuceneConstants.Mapping_CNN;
		}
		if (DS.equalsIgnoreCase("BBC"))
		{
			IndexDir = "D:\\Adv Comp Assignments\\Data Files\\DataIndex_BBC";
			MappingFile = LuceneConstants.Mapping_BBC;
		}
		if (DS.equalsIgnoreCase("WindsorStar"))//WindsorStar
		{	
			System.out.println("DS = " + DS );
			IndexDir = LuceneConstants.indexDir_WindsorStar;
			MappingFile = LuceneConstants.Mapping_WindsorStar;
		}
		reader = DirectoryReader.open(FSDirectory.open(Paths.get(IndexDir)));
		
		if (SearchType.equalsIgnoreCase("Pattern Search"))
		{	
			indexsearcher = new IndexSearcher(reader);
			 
			//System.out.println("MappingFile = " + MappingFile);
			createTST(MappingFile);
			System.out.println("args = " + args);
			searcher = new Searcher(IndexDir);
			long startTime = System.currentTimeMillis();
			
			results = searcher.search(args); //get hit count
			long endTime = System.currentTimeMillis();
			ScoreDoc[] hits = results.scoreDocs;
			//System.out.println("Total hits = "+ hits.length);
			
			Results= new String[hits.length];int i =0;
			for (ScoreDoc s : hits)
		    {
		    	Document doc = indexsearcher.doc(s.doc);
		        String filename = doc.get("path");
		        //System.out.println("path  =" + filename);
		        Path path = FileSystems.getDefault().getPath(filename);
		        filename = path.getFileName().toString();
		        //System.out.println("filename =" + filename);
		        
		        String temp = filename.substring(0, filename.lastIndexOf('.'));
				//System.out.println(tst.get(temp));
		    	//System.out.println("doc="+filename+" score="+s.score);
		    	//<td><a href=<%=s%>> <%=s%> </a></td>
		    	String HREF = "<a href ="+tst.get(temp)+">"+tst.get(temp)+"</a>";
		    	Results[i++]= new String (HREF);
		    	//System.out.println("   HREF: " + HREF);
		    }
		}
		if (SearchType.equalsIgnoreCase("RegEx"))
		{
			Results= Regex.main(args,DS);
		}
		if (SearchType.equalsIgnoreCase("Top N"))
		{	
			//reader = DirectoryReader.open(FSDirectory.open(Paths.get(IndexDir)));
			//searcher = new IndexSearcher(reader);
			Results= new String[Integer.parseInt(args)];int i =0;
			
			HighFreqTerms.DocFreqComparator x = new HighFreqTerms.DocFreqComparator();
			TermStats[] commonTerms = HighFreqTerms.getHighFreqTerms(reader,Integer.parseInt(args), "contents",x);
			
			for (TermStats commonTerm : commonTerms) {
			    //System.out.println(commonTerm.termtext.utf8ToString() + " freq = " + commonTerm.totalTermFreq); //Or whatever you need to do with it
				Results[i++] =commonTerm.termtext.utf8ToString() +" Freq - " +  commonTerm.totalTermFreq;
			}
		}	
		reader.close();
		return Results;
	}
	//NOT USED 
	private void search(String searchQuery) throws IOException, org.apache.lucene.queryparser.classic.ParseException {
		searcher = new Searcher(IndexDir);
		long startTime = System.currentTimeMillis();
		TopDocs results = searcher.search(searchQuery);
		long endTime = System.currentTimeMillis();
		ScoreDoc[] hits = results.scoreDocs;
	    
		System.out.println(hits.length +
				" documents found. Time :" + (endTime - startTime));
		for (ScoreDoc s : hits)
	    {
	    	Document doc = searcher.searcher.doc(s.doc);
	        String path = doc.get("path");
	    	System.out.println("doc="+path+" score="+s.score);
	    	//System.out.println("   Title: " + doc.get("title"));
	    }
		//searcher.close();
	}

	public static void createTST(String MappingFile) throws Exception
	{
		//MappingFile = "D:\\Adv Comp Assignments\\Data Files\\Mapping URL_FileName.txt";
		BufferedReader reader = null; 
		reader = new BufferedReader(new FileReader(MappingFile));
		String currentLine = reader.readLine();
		while (currentLine != null) 
		{
			String[] split = currentLine.split("\\s");
			if (split.length== 2)
			{
				//System.out.println("split[0] ="+split[0]);System.out.println("split[1] ="+split[1]);
				tst.put(split[0],split[1]);
			}
			currentLine = reader.readLine();
		}
		reader.close();
	}
}
