package br.unicamp.ic.crawler.domain.core;

import java.util.List;

public interface HistoryParser {
	public List<IssueActivityEntry> parse(String contents);
}
