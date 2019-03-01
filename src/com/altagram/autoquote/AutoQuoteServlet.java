package com.altagram.autoquote;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.altagram.admin_autoquote.AdminUtils;
import com.altagram.autoquotebeans.AdminDataBean;
import com.altagram.autoquotebeans.QuoteElementBean;

public class AutoQuoteServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	public AutoQuoteServlet() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		DataManagement priceListManagement = new DataManagement(getServletContext().getResourceAsStream(
				Constants.PRICE_LIST_PATH), Constants.DATAMANAGEMENT_PRICELIST);

		DataManagement sourceLangListManagement = new DataManagement(getServletContext()
				.getResourceAsStream(Constants.SOURCE_LANG_LIST_PATH), Constants.DATAMANAGEMENT_LANGS);
		

		DataManagement targetLangListManagement = new DataManagement(getServletContext()
				.getResourceAsStream(Constants.TARGET_LANG_LIST_PATH), Constants.DATAMANAGEMENT_LANGS);
		
		
		AdminDataBean adminData = AdminUtils.setupAdminDataBean(
				sourceLangListManagement.getFullLanguageNamesList(), targetLangListManagement.getFullLanguageNamesList(), priceListManagement.getPriceList());
		
		
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
		DataManagement priceListManagement = new DataManagement(getServletContext().getResourceAsStream(
				Constants.PRICE_LIST_PATH), Constants.DATAMANAGEMENT_PRICELIST);
		
		DataManagement sourceLangListManagement = new DataManagement(
				getServletContext().getResourceAsStream(Constants.SOURCE_LANG_LIST_PATH),
				Constants.DATAMANAGEMENT_LANGS);
		
		DataManagement targetLangListManagement = new DataManagement(getServletContext()
				.getResourceAsStream(Constants.TARGET_LANG_LIST_PATH), Constants.DATAMANAGEMENT_LANGS);
		

		List<String> targetLanguages = new ArrayList<String>();
		List<String> fileNamesList = new ArrayList<String>();
		List<QuoteElementBean> quoteElementList = new ArrayList<QuoteElementBean>();
		List<FileItem> fileUploadList = new ArrayList<FileItem>();

		DecimalFormat priceFormat = AutoQuoteUtils
				.getPirceFormat(Constants.PRICE_DECIMAL_FORMAT, Constants.PRICE_CURRENCY_CODE,
						RoundingMode.HALF_UP);

		// Process file data from HTTP request

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
						
			sourceLanguageFull = sourceLangListManagement.getFullLanguageName(
					sourceLanguage);
		} catch (Exception ex) {
			request.getRequestDispatcher(Constants.ERROR_PAGE_URL).forward(
					AutoQuoteUtils.setupErrorRequest(request, ex), response);
			return;
		}
		// TODO total price
		for (String targetLanguage : targetLanguages) {

			tempPrice = priceListManagement.getPrice(sourceLanguage, targetLanguage);
			tempQuote = AutoQuoteUtils.calculateQuote(wordCount, tempPrice);
			tempQuoteFormatted = priceFormat.format(tempQuote);
			tempPriceFormatted = priceFormat.format(tempPrice);

			targetLanguageFull = targetLangListManagement.getFullLanguageName(
					targetLanguage);

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
