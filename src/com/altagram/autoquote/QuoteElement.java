package com.altagram.autoquote;

import java.io.Serializable;

public class QuoteElement implements Serializable {
	
	private String languagePair = null;
	private String totalUnitPrice = null;
	private String unitPrice = null;
	
	//public QuoteElement (String sourceLanguage, String targetLanguage, String totalPrice, String unitPrice) {
	public QuoteElement () {}
	
	public String getLanguagePair() {
		return languagePair;
	}
	
	public String getTotalUnitPrice() {
		return totalUnitPrice;
	}
	
	public String getUnitPrice() {
		return unitPrice;
	}
	
	public void setLanguagePair(String languagePair) {
		this.languagePair = languagePair;
	}
	
	public void setTotalUnitPrice(String totalPrice) {
		this.totalUnitPrice = totalPrice;
	}
	
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
}
