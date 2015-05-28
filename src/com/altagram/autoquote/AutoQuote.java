package com.altagram.autoquote;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

public class AutoQuote extends HttpServlet {

	private final String PRICE_LIST_PATH = "/Resources/files/pricelist.txt";
	private final String WELCOME_PAGE_URL = "/Resources/html/Quote.html";
	private final String RESPONSE_PAGE_URL = "/Resources/html/Response.jsp";
	
	
	public AutoQuote() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("Quote.html");
		request.getRequestDispatcher(WELCOME_PAGE_URL).forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int wordCount = 0;
		double totalPrice = 0;
		double tempPrice;
    	double tempQuote;
		
		
		String sourceLanguage = "";
		String targetLanguagesAll = "";	
		String tempPriceFormatted = "";
		String tempQuoteFormatted = "";
		final String UPLOAD_PATH = getServletContext().getRealPath("") + File.separator +  "uploads";
		
		List<String> uploadedFileNames = new ArrayList<String>();
		
		String htmlResponse;
				
		HashMap<String, Double> priceList;
		List<String> targetLanguages = new ArrayList<String>();
		List<QuoteElement> quoteElementList = new ArrayList<QuoteElement>();
		
		PrintWriter writer = response.getWriter();
		
		File fileUpload = null;
		
		DecimalFormat priceFormat = new DecimalFormat("#.##");
		priceFormat.setRoundingMode(RoundingMode.HALF_UP);
		
		//TODO Add error pages
		//Process data from HTTP request
		try {
			
			List<FileItem> multiPartParsed = AutoQuoteUtils.parseMultipart(request, UPLOAD_PATH);
			File uploadDir = new File(UPLOAD_PATH);
			
		    if (!uploadDir.exists()) {
		        uploadDir.mkdir();
		    }
		  
		    if (multiPartParsed != null && multiPartParsed.size() > 0) {
		    	for (FileItem item:multiPartParsed) {
		    		if (!item.isFormField()) {
		    			item.write(new File(UPLOAD_PATH + File.separator + new File(item.getName()).getName()));
		    			fileUpload = new File(UPLOAD_PATH + File.separator + new File(item.getName()).getName());
		    			
		    		}
		    	}
		    	
		    	for (FileItem item:multiPartParsed) {
		    		if (item.isFormField()) {
		    			if (item.getFieldName().equals("source"))
		    				sourceLanguage = item.getString();
		    			else if (item.getFieldName().equals("target"))
		    				targetLanguages.add(item.getString());
		    		}
		    	}
		    	
		    }
		}

		catch (Exception ex) {
			htmlResponse = "<html>";
	        htmlResponse += "Error: " + ex.getMessage() + "<br/><br/>";
	        htmlResponse += "</html>";
	        writer.println(htmlResponse);
	        writer.close();
		}

		//Process input file, get word count
		
		try {			
			CountWordWeb CWW = new CountWordWeb(fileUpload, sourceLanguage);
			wordCount = CWW.getWordCount();
		}
		
		catch (Exception ex) {
			htmlResponse = "<html>";
	        htmlResponse += "Error: " + ex.getMessage() + "<br/><br/>";
	        htmlResponse += "</html>";
	        writer.println(htmlResponse);
	        writer.close();
		}
		
	        priceList = AutoQuoteUtils.getPriceList(getServletContext().getRealPath(PRICE_LIST_PATH));
	        
	        for (String targetLanguage:targetLanguages) {
	        	
	        	tempPrice = AutoQuoteUtils.getPrice(sourceLanguage, targetLanguage, priceList);
	        	tempPriceFormatted = priceFormat.format(tempPrice);
	        	tempQuote = AutoQuoteUtils.calculateQuote(wordCount, tempPrice);
	        	tempQuoteFormatted = priceFormat.format(tempQuote);
	        	
	        	totalPrice += tempQuote;    	

	        	quoteElementList.add(AutoQuoteUtils.setupQuoteItem(new QuoteElement(), 
	        			sourceLanguage, targetLanguage, tempQuoteFormatted, tempPriceFormatted));
	        }
	        
	        // Forward to jsp response
	        //TODO: add if null check
	        
	        request.setCharacterEncoding("UTF-8");
	        request.setAttribute("wordCount", Integer.toString(wordCount));
	        request.setAttribute("fileName", fileUpload.getName());
	        request.setAttribute("quoteElementList", quoteElementList);
	        request.getRequestDispatcher(RESPONSE_PAGE_URL).forward(request, response);
		

		
        
//        htmlResponse += "<br/><b>Total:    " + priceFormat.format(totalPrice) + "<br/>";
//        htmlResponse += "Total + 5% PM fee:    " + priceFormat.format(totalPrice + (totalPrice * 0.05d)) + "</b><br/><br/>";
//        
//        htmlResponse += "</body></html>";
//        writer.println(htmlResponse);
//        writer.close();
				
	}
}

	
//	public static void main (String[] args) throws IOException {
//		
//	}
//		HashMap<String, Double> testPriceList = AutoQuoteUtils.getPriceListFromFile("C:\\Users\\MarcinWerelich\\Desktop\\TEST\\pricelist.txt");
//		File testFile = new File("C:\\Users\\MarcinWerelich\\Desktop\\TEST\\HW_Benchmark_Collectors_Edition_EN.docx");
//		CountWordWeb testCWW = new CountWordWeb(testFile, "en");
//		DecimalFormat testFormat = new DecimalFormat("#.##");
//		testFormat.setRoundingMode(RoundingMode.HALF_UP);
//		
//		String sourceLanguage = "en";
//		String formattedPrice;
//		String[] testTargetLanguageList = new String[] {"no", "sv", "fi"};
//		
//		int testWordCount = testCWW.getWordCount();
//		double testPrice;  
//		
//		System.out.println("Input document: " + testFile.getName());
//		System.out.println("Word count: " + testWordCount);
//		System.out.println();
//		System.out.println("Quote: ");
//		
//		for (String targetLang:testTargetLanguageList) {
//			testPrice = AutoQuoteUtils.getPrice(sourceLanguage, targetLang, testPriceList);
//			formattedPrice = testFormat.format(AutoQuoteUtils.calculateQuote(testWordCount, testPrice));
//			
//			System.out.println(sourceLanguage + ">" + targetLang + ": " + formattedPrice + " EUR" + " @ " + testPrice + " per word");
//			
//		}
//		
//	}

