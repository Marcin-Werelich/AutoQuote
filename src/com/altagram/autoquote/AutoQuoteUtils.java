package com.altagram.autoquote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resources;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AutoQuoteUtils {

	
	public static List<FileItem> parseMultipart(HttpServletRequest request, String uploadPath) throws Exception {
		  
	  boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	  
	  	if (isMultipart) {
			File returnFile;
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			return items;
	  	}
	  	else return null;
  
	}
	
//	public static String getSourceLanguage (HttpServletRequest request) {
//		
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		try {
//			List<FileItem> items = upload.parseRequest(request);
//			
//		} catch (FileUploadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		  
//		  
//	}
	
	
	public static double calculateQuote(double wordCount, double price) {
		
		return wordCount * price;
	}
	
	
	
	public static int getWordCount(File inputFile, String sourceLanguage) throws IOException {
		
		CountWordWeb countWords = new CountWordWeb(inputFile, sourceLanguage);
		return countWords.getWordCount();
				
	}
	

	public static double getPrice(String sourceLanguage, String targetLanguage, HashMap<String, Double> priceList) {
		String[] entrySplit;
		for(Entry<String, Double> entry:priceList.entrySet()) {
			entrySplit = entry.getKey().toString().split(">");
			if(entrySplit[0].matches(sourceLanguage) && entrySplit[1].matches(targetLanguage)) 
				return (double) entry.getValue();
		}
		return 0;
	}
	
	
	public static HashMap<String, Double> getPriceList(String inputPath) throws IOException {
		
		HashMap<String, Double> priceList = new HashMap<String, Double>();		
		String[] lineSplit;
		List<String> lineList = Files.readAllLines(Paths.get(inputPath), Charset.defaultCharset());
		
		for (String line:lineList) {			
			lineSplit = line.split("\t");
			priceList.put(lineSplit[0] + ">" + lineSplit[1], Double.parseDouble(lineSplit[2]));
		}
		
		return priceList;
	}
	
	public static QuoteElement setupQuoteItem (QuoteElement item, String sourceLanguage, String targetLanguage, 
			String totalPrice, String unitPrice) {
		
		String tempLanguagePair = sourceLanguage + " > " + targetLanguage;
    	
		item.setLanguagePair(tempLanguagePair);
    	item.setTotalPrice(totalPrice);
    	item.setUnitPrice(unitPrice);
		
    	return item;
	}
	
	//TODO: unzipping
//	public static List<FileItem> unzipToList (FileItem inputFile) {
//		
//	}
	
	
}
