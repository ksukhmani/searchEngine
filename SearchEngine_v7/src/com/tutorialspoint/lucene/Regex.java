package com.tutorialspoint.lucene;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static String[] main (String args,String DS) throws Exception
	{
		BufferedReader reader = null;
		String Results[]= new String[10],IndexDir="";
	
		int ph_c=0,i=0;
		Pattern r = Pattern.compile(args);
		Matcher findphone;
		if (DS.equalsIgnoreCase("CNN"))
		{
			IndexDir = "D:\\Adv Comp Assignments\\Data Files\\JSOUPFiles_CNN";
		}
		if (DS.equalsIgnoreCase("BBC"))
		{
			IndexDir = "D:\\Adv Comp Assignments\\Data Files\\JSOUPFiles_BBC";
		}
		if (DS.equalsIgnoreCase("WindsorStar"))
		{
			IndexDir = "D:\\Adv Comp Assignments\\Data Files\\JSOUPFiles";
		}
		
		final File folder = new File(IndexDir);
		
		
		for (final File fileEntry : folder.listFiles()) 
		{
			 if (fileEntry.isDirectory()== false ) 
			 {	
				 reader = new BufferedReader(new FileReader(fileEntry.getAbsoluteFile()));
				 String currentline = reader.readLine();
					while (currentline!= null)
					{
						findphone = r.matcher(currentline);
						//findemail = e.matcher(currentline);
						//finddollar = d.matcher(currentline);
						if (findphone.find())
						{
							//System.out.println("Found Phone# : " + findphone.group(0) + " in file " + fileEntry.getName());
							if (i<10) 
							{
								//int space = findphone.group(0);
								Results[i++]= "Found : "+ findphone.group(0) + " in file " + fileEntry.getName();
							}
							ph_c++;
						}
						
						currentline= reader.readLine();
					}
			 }
		}
		
		reader.close();
		
		System.out.println("Total Regex Matches# = " + ph_c);
		
		return Results;
	}

}
