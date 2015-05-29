package com.altagram.autoquote;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import net.sf.okapi.common.LocaleId;
import net.sf.okapi.common.filters.IFilter;
import net.sf.okapi.common.Event;
import net.sf.okapi.common.EventType;
import net.sf.okapi.common.resource.RawDocument;
import net.sf.okapi.filters.html.HtmlFilter;
import net.sf.okapi.filters.idml.IDMLFilter;
import net.sf.okapi.filters.openoffice.OpenOfficeFilter;
import net.sf.okapi.filters.openxml.OpenXMLFilter;
import net.sf.okapi.filters.plaintext.PlainTextFilter;
import net.sf.okapi.filters.tmx.TmxFilter;
import net.sf.okapi.filters.xml.XMLFilter;


public class CountWordWeb {

	private int wordCount = 0;
	private IFilter documentFilter = null;
	private String status;
	private final String STATUS_OK = "OK";
	
	
	
	public CountWordWeb (FileItem inputFile, String sourceLocale) throws IOException {
		
		wordCount = 0;
		LocaleId sourceLocaleId = new LocaleId(sourceLocale);
		
		checkFilter(inputFile);
		
		if (documentFilter == null) {
			wordCount = 0;
			throw new IOException(inputFile.getName() + " - file type not supported.");
		}
		//TODO add multifile
		else {
			status = STATUS_OK;
			documentFilter.open(new RawDocument(inputFile.getInputStream(), "UTF-8", sourceLocaleId));
			while (documentFilter.hasNext() ) {
				Event event = documentFilter.next();
				if (event.getEventType() == EventType.TEXT_UNIT ) {
				
					wordCount += (int) WordCounter.count(event.getTextUnit().getSource().getFirstContent().toString(), sourceLocaleId);
					
				
				}
			}
		}
		

	}
	
	public int getWordCount() {
		return wordCount;
	}
	
	public String getStatus() {
		return status;
	}
	

	
	
private void checkFilter (FileItem sourceFile) {
		
		String fileName = sourceFile.getName();
		//MS Office
		if (fileName.endsWith(".docx") || fileName.endsWith(".xlsx") || 
				fileName.endsWith(".pptx") || fileName.endsWith(".docm") || fileName.endsWith(".dotm"))
			documentFilter = new OpenXMLFilter();
		//OpenOffice
		else if (fileName.endsWith(".odt") || fileName.endsWith(".ott") || fileName.endsWith(".odp") ||
				fileName.endsWith(".sxw") || fileName.endsWith(".stw") || fileName.endsWith(".otg") ||
				fileName.endsWith(".ods") || fileName.endsWith(".ots") || fileName.endsWith(".sxc") || 
				fileName.endsWith(".sxd") || fileName.endsWith(".std"))
			documentFilter = new OpenOfficeFilter();	
		
		//HTML \ HTM
		else if (fileName.endsWith(".html") || fileName.endsWith(".htm"))
			documentFilter = new HtmlFilter();
	
		//XML filter
		else if (fileName.endsWith(".xml"))
			documentFilter = new XMLFilter();
		
		//IDML
		else if (fileName.endsWith(".idml"))
			documentFilter = new IDMLFilter();
		
		//plain text
		else if (fileName.endsWith(".txt"))
			documentFilter = new PlainTextFilter();
		
		//TODO: PO filter 
//		else if (fileName.endsWith(".po"))
//			documentFilter = new POFilter();
		
		//TODO TMX filter
//		else if (fileName.endsWith(".tmx"))
//			documentFilter = new TmxFilter();
		
		//TODO:xliff filter
//		else if (fileName.endsWith(".xlf")|| fileName.endsWith(".sdlxliff") || fileName.endsWith(".mqxliff") || fileName.endsWith(".xliff"))
//			documentFilter = new XLIFFFilter();
		
		
		//TODO: other filters
		
		else documentFilter = null;
	}
	
	public static void main (String[] args) throws IOException {
	
//		HashMap<String, Double> testMap = AutoQuoteUtils.getPriceList();
//		for(Map.Entry<String, Double> entry: testMap.entrySet()) {
//			System.out.println(entry.toString());
//		}
	}
	
}
	

