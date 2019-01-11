package br.unicamp.ic.crawler.domain.core.filters;

import br.unicamp.ic.crawler.domain.core.Report;

public class IssueNoFilter extends ReportFilter {

	@Override
	protected boolean evaluate(Report issue) {
		return true;
	}

}
