package br.unicamp.ic.crawler.domain.core;

public interface IssueActivityEntry {

	String getWho();

	String getWhen();

	String getWhat();

	String getRemoved();

	String getAdded();

}