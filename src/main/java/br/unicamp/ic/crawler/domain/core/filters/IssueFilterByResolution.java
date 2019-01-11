package br.unicamp.ic.crawler.domain.core.filters;

import br.unicamp.ic.crawler.domain.core.Report;

public class IssueFilterByResolution extends ReportFilter {

	private String resolution;

	public IssueFilterByResolution(String resolution) {
		this.resolution = resolution.toLowerCase();
	}

	@Override
	protected boolean evaluate(Report issue) {
		return issue.getResolution().equals(resolution);
	}

}
