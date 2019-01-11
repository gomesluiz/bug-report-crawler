package br.unicamp.ic.crawler.domain.core.filters;

import java.util.ArrayList;
import java.util.List;

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
	public List<Report> filter(List<Report> issues) {
		List<Report> result = new ArrayList<Report>();
		for(Report issue: issues) {
			if (evaluate(issue) == true) {
				result.add(issue);
			}
		}
		return result;
	}
}
