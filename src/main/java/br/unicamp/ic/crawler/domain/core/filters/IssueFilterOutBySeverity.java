package br.unicamp.ic.crawler.domain.core.filters;

import br.unicamp.ic.crawler.domain.core.Report;

public class IssueFilterOutBySeverity extends ReportFilter {

	private String severity;

	public IssueFilterOutBySeverity(String severity) {
		this.severity = severity;
	}

	@Override
	protected boolean evaluate(Report issue) {
		return !(issue.getSeverity().equalsIgnoreCase(severity));
	}

}
