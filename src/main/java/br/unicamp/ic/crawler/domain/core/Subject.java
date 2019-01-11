package br.unicamp.ic.crawler.domain.core;

import java.util.ArrayList;
import java.util.List;

public class Subject {

	private List<CrawlerObserver> observers = new ArrayList<CrawlerObserver>();
	private String message;
	
	public void add(CrawlerObserver o) {
		observers.add(o);
	}

	public String getMessage() {
		return this.message;
	}
	
	public 	void setMessage(String message) {
		this.message = message;
		execute();
	}

	private void execute() {
		for (CrawlerObserver observer: observers) {
			observer.update();
		}
		
	}
	
}
