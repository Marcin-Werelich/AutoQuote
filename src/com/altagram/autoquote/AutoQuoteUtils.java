package com.altagram.autoquote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import javax.annotation.Resources;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.altagram.autoquotebeans.QuoteElementBean;
import com.altagram.wordcounter.CountWordWeb;

public class AutoQuoteUtils {

	public static List<FileItem> parseMultipart(HttpServletRequest request)
			throws Exception {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			return items;
		} else
			return null;

	}

	public static DecimalFormat getPirceFormat(String pattern,
			String currencyCode, RoundingMode roundingMode) {
		DecimalFormat priceFormat = new DecimalFormat(pattern);
		priceFormat.setCurrency(Currency.getInstance(currencyCode));
		// priceFormat.setRoundingMode(roundingMode);
		return priceFormat;
	}

	public static int getWordCountFromInputFile(FileItem inputFile,
			String sourceLanguage) throws IOException {
		CountWordWeb CWW = new CountWordWeb(inputFile, sourceLanguage);
		return CWW.getWordCount();

	}

	public static HashMap<String, Double> getPriceList(InputStream input)
			throws IOException {

		List<String> lineList = new ArrayList<String>();
		HashMap<String, Double> priceList = new HashMap<String, Double>();
		String[] lineSplit;

		Scanner inputReader = new Scanner(new InputStreamReader(
				input));

		while (inputReader.hasNextLine()) {
			lineList.add(inputReader.nextLine());
		}

		for (String line : lineList) {
			if (line != null && !line.matches("")) {
				lineSplit = line.split("\t");
				priceList.put(lineSplit[0] + ">" + lineSplit[1],
						Double.parseDouble(lineSplit[2]));
			}
		}
		inputReader.close();
		return priceList;
	}

	public static double getPrice(String sourceLanguage, String targetLanguage,
			HashMap<String, Double> priceList) {
		String[] entrySplit;
		for (Entry<String, Double> entry : priceList.entrySet()) {
			entrySplit = entry.getKey().toString().split(">");
			if (entrySplit[0].matches(sourceLanguage)
					&& entrySplit[1].matches(targetLanguage))
				return (double) entry.getValue();
		}
		return 0;
	}

	public static TreeMap<String, String> getFullLanguageNamesList(
			InputStream input) throws IOException {
		List<String> lineList = new ArrayList<String>();
		TreeMap<String, String> langList = new TreeMap<String, String>();
		String[] lineSplit;
		Scanner inputReader = new Scanner(new InputStreamReader(
				input));

		while (inputReader.hasNextLine()) {
			lineList.add(inputReader.nextLine());
		}

		for (String line : lineList) {
			if (line != null) {
				lineSplit = line.split("\t");
				langList.put(lineSplit[1], lineSplit[0]);
			}
		}	
		inputReader.close();
		return langList;
	}

	public static String getFullLanguageName(String languageShortName,
			TreeMap<String, String> langList) {

		for (Entry<String, String> entry : langList.entrySet()) {
			if (entry.getValue().matches(languageShortName))
				return entry.getKey();
		}
		return null;
	}

	public static double calculateQuote(double wordCount, double price) {

		return wordCount * price;
	}

	public static QuoteElementBean setupQuoteItem(QuoteElementBean item,
			String sourceLanguage, String targetLanguage,
			String totalUnitPrice, String unitPrice) {

		String tempLanguagePair = sourceLanguage + " > " + targetLanguage;

		item.setLanguagePair(tempLanguagePair);
		item.setTotalUnitPrice(totalUnitPrice);
		item.setUnitPrice(unitPrice);

		return item;
	}

	public static HttpServletRequest setupErrorRequest(
			HttpServletRequest request, Exception exception) {

		StringWriter errorWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(errorWriter));

		request.setAttribute("stackTrace", errorWriter.toString());
		request.setAttribute("errorMessage", exception.getMessage());

		return request;
	}

	public static HttpServletRequest setupOkResponseRequest(
			HttpServletRequest request, String wordCount, String totalPrice,
			String fileName, List<QuoteElementBean> quoteElementList)
			throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		request.setAttribute("wordCount", wordCount);
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("fileName", fileName);
		request.setAttribute("quoteElementList", quoteElementList);

		return request;
	}

}
