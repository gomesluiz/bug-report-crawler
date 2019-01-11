package br.unicamp.ic.crawler.domain.core;

public class LoggerObserver extends CrawlerObserver {

	private Subject subject;
	public LoggerObserver(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}
	@Override
	public void update() {
		System.out.println(subject.getMessage());
	}

}
