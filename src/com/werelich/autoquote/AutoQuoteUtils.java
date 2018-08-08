package com.werelich.autoquote;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.werelich.autoquotebeans.QuoteElementBean;
import com.werelich.wordcounter.CountWordWeb;

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
