package br.unicamp.ic.crawler.domain.core.filters;

import br.unicamp.ic.crawler.domain.core.Report;

/**
 * TODO
 * @author luiz
 *
 */
public abstract class ReportFilter {
	/**
	 * TODO
	 * @param issue
	 * @return
	 */
	protected abstract boolean evaluate(Report issue);
	
	/**
	 * TODO
	 * @param issues
	 * @return
	 */
	public Boolean filter(Report issue) {
		return evaluate(issue);
		
	}
}
