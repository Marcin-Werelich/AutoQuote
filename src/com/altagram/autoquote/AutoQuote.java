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

	private final String PRICE_LIST_PATH = "/Resources/files/pricelist.txt";
	private final String LANG_LIST_PATH = "/Resources/files/languageList.txt";
	private final String WELCOME_PAGE_URL = "/Resources/html/Quote.html";
	private final String RESPONSE_PAGE_URL = "/Resources/html/QuoteResponse.jsp";
	private final String ERROR_PAGE_URL = "/Resources/html/ErrorResponse.jsp";
	private final String PRICE_DECIMAL_FORMAT = "0.00#";
	private final String PRICE_CURRENCY_CODE = "EUR";
	private final String NO_FILE_ERROR_MESSAGE = "There was an error with the file upload, please try again.";

	public AutoQuote() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(WELCOME_PAGE_URL).forward(request,
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
		HashMap<String, String> fullLanguageNamesList;

		List<String> targetLanguages = new ArrayList<String>();
		List<String> fileNamesList = new ArrayList<String>();
		List<QuoteElementBean> quoteElementList = new ArrayList<QuoteElementBean>();
		List<FileItem> fileUploadList = new ArrayList<FileItem>();

		DecimalFormat priceFormat = AutoQuoteUtils
				.getPirceFormat(PRICE_DECIMAL_FORMAT, PRICE_CURRENCY_CODE,
						RoundingMode.HALF_UP);

		// Process data from HTTP request

		try {

			List<FileItem> multiPartParsed = AutoQuoteUtils
					.parseMultipart(request);

			if (multiPartParsed == null) {
				throw new Exception(NO_FILE_ERROR_MESSAGE);
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

			request.getRequestDispatcher(ERROR_PAGE_URL).forward(
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
			request.getRequestDispatcher(ERROR_PAGE_URL).forward(
					AutoQuoteUtils.setupErrorRequest(request, ex), response);
			return;
		}
		try {
			priceList = AutoQuoteUtils.getPriceList(getServletContext()
					.getResourceAsStream(PRICE_LIST_PATH));
			fullLanguageNamesList = AutoQuoteUtils
					.getFullLanguageNamesList(getServletContext()
							.getResourceAsStream(LANG_LIST_PATH));
			sourceLanguageFull = AutoQuoteUtils.getFullLanguageName(
					sourceLanguage, fullLanguageNamesList);
		} catch (Exception ex) {
			request.getRequestDispatcher(ERROR_PAGE_URL).forward(
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

		request.getRequestDispatcher(RESPONSE_PAGE_URL).forward(
				AutoQuoteUtils.setupOkResponseRequest(request,
						Integer.toString(wordCount),
						priceFormat.format(totalPrice),
						fileNamesList.toString(), quoteElementList), response);

	}
}
