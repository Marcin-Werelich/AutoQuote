package com.altagram.autoquote;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

public class AutoQuote extends HttpServlet {

	public AutoQuote() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		HashMap<String, Double> priceList = AutoQuoteUtils
				.getPriceList(getServletContext().getResourceAsStream(
						Constants.PRICE_LIST_PATH));

		TreeMap<String, String> sourceLangList = AutoQuoteUtils
				.getFullLanguageNamesList(getServletContext()
						.getResourceAsStream(Constants.SOURCE_LANG_LIST_PATH));

		TreeMap<String, String> targetLangList = AutoQuoteUtils
				.getFullLanguageNamesList(getServletContext()
						.getResourceAsStream(Constants.TARGET_LANG_LIST_PATH));
		
		
		AdminDataBean adminData = AdminAutoQuoteUtils.setupAdminDataBean(
				sourceLangList, targetLangList, priceList);
		
		
		request.setAttribute("adminData", adminData);

		request.getRequestDispatcher(Constants.WELCOME_PAGE_URL).forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int wordCount = 0;
		double totalPrice = 0;
		double tempPrice;
		double tempQuote;

		String sourceLanguage = "";
		String sourceLanguageFull = "";
		String targetLanguageFull = "";
		String tempPriceFormatted = "";
		String tempQuoteFormatted = "";
		String tempFileName = "";

		HashMap<String, Double> priceList;
		TreeMap<String, String> fullLanguageNamesList;

		List<String> targetLanguages = new ArrayList<String>();
		List<String> fileNamesList = new ArrayList<String>();
		List<QuoteElementBean> quoteElementList = new ArrayList<QuoteElementBean>();
		List<FileItem> fileUploadList = new ArrayList<FileItem>();

		DecimalFormat priceFormat = AutoQuoteUtils
				.getPirceFormat(Constants.PRICE_DECIMAL_FORMAT, Constants.PRICE_CURRENCY_CODE,
						RoundingMode.HALF_UP);

		// Process data from HTTP request

		try {

			List<FileItem> multiPartParsed = AutoQuoteUtils
					.parseMultipart(request);

			if (multiPartParsed == null) {
				throw new Exception(Constants.NO_FILE_ERROR_MESSAGE);
			}

			if (multiPartParsed != null && multiPartParsed.size() > 0) {
				for (FileItem item : multiPartParsed) {
					if (!item.isFormField()) {
						tempFileName = new File(item.getName()).getName();
						fileUploadList.add(item);
						fileNamesList.add(tempFileName);
					}
				}

				for (FileItem item : multiPartParsed) {
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

			request.getRequestDispatcher(Constants.ERROR_PAGE_URL).forward(
					AutoQuoteUtils.setupErrorRequest(request, ex), response);
			return;
		}

		// Process input file, get word count

		try {
			for (FileItem fileUpload : fileUploadList) {
				wordCount += AutoQuoteUtils.getWordCountFromInputFile(
						fileUpload, sourceLanguage);
			}

		}

		catch (Exception ex) {
			request.getRequestDispatcher(Constants.ERROR_PAGE_URL).forward(
					AutoQuoteUtils.setupErrorRequest(request, ex), response);
			return;
		}
		try {
			priceList = AutoQuoteUtils.getPriceList(getServletContext()
					.getResourceAsStream(Constants.PRICE_LIST_PATH));
			
			fullLanguageNamesList = AutoQuoteUtils
					.getFullLanguageNamesList(getServletContext()
							.getResourceAsStream(Constants.TARGET_LANG_LIST_PATH));
			
			sourceLanguageFull = AutoQuoteUtils.getFullLanguageName(
					sourceLanguage, fullLanguageNamesList);
		} catch (Exception ex) {
			request.getRequestDispatcher(Constants.ERROR_PAGE_URL).forward(
					AutoQuoteUtils.setupErrorRequest(request, ex), response);
			return;
		}
		// TODO total price
		for (String targetLanguage : targetLanguages) {

			tempPrice = AutoQuoteUtils.getPrice(sourceLanguage, targetLanguage,
					priceList);
			tempQuote = AutoQuoteUtils.calculateQuote(wordCount, tempPrice);
			tempQuoteFormatted = priceFormat.format(tempQuote);
			tempPriceFormatted = priceFormat.format(tempPrice);

			targetLanguageFull = AutoQuoteUtils.getFullLanguageName(
					targetLanguage, fullLanguageNamesList);

			totalPrice += tempQuote;

			quoteElementList.add(AutoQuoteUtils.setupQuoteItem(
					new QuoteElementBean(), sourceLanguageFull, targetLanguageFull,
					tempQuoteFormatted, tempPriceFormatted));
		}

		// Forward to jsp response

		request.getRequestDispatcher(Constants.QUOTE_RESPONSE_PAGE_URL).forward(
				AutoQuoteUtils.setupOkResponseRequest(request,
						Integer.toString(wordCount),
						priceFormat.format(totalPrice),
						fileNamesList.toString(), quoteElementList), response);

	}
}
