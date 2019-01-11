package br.unicamp.ic.crawler.domain.core;

public abstract class CrawlerObserver {
	protected ReportCrawler subject;
	public abstract void update();
}
