package com.suji.mod;

import java.util.ArrayList;
import java.util.List;

public class QuotePagition {
	
	public static List<Quote> getRecords(int page) {
		int limit = 10;
		return get(limit, (page) * limit);
	}

	public static List<Quote> get(int limit, int offset) {

		List<Quote> quotes = new ArrayList<Quote>();
		
		for (int i = offset; i < offset + limit; i++) {
			Quote q = new Quote(i, i, "Quote "+i, "Author "+i);
			quotes.add(q);
		}
		
		return quotes;
	
	}

	public static int getPages(int rec, int lim) {
		int pages = rec / lim;
		if (rec % lim > 0) {
			pages++;
		}
		return pages;
	}
}
