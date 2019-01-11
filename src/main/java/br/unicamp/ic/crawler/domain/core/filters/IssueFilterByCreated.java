package br.unicamp.ic.crawler.domain.core.filters;

import br.unicamp.ic.crawler.domain.core.Report;

public class IssueFilterByCreated extends ReportFilter {
	private String start;
	private String end;

	public IssueFilterByCreated(String start, String end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public boolean evaluate(Report issue) {
		return ((issue.getCreated() == start) && (issue.getCreated() == end));
	}
	
}
