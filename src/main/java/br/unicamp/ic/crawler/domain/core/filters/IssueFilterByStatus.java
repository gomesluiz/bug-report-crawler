package br.unicamp.ic.crawler.domain.core.filters;

import br.unicamp.ic.crawler.domain.core.Report;

/**
 * TODO
 * @author luiz
 *
 */
public class IssueFilterByStatus extends ReportFilter {

	private String statusOne;
	private String statusTwo;

	/**
	 * TODO
	 * @param statusOne TODO
	 * @param statusTwo TODO
	 */
	public IssueFilterByStatus(String statusOne, String statusTwo) {
		this.statusOne 	= statusOne.toLowerCase();
		this.statusTwo	= statusTwo.toLowerCase();
	}

	public IssueFilterByStatus(String status) {
		statusOne = statusTwo = status.toLowerCase();
	}

	@Override
	public boolean evaluate(Report issue) {
		boolean result = issue.getStatus().equals(this.statusOne);
		result = result || issue.getStatus().equals(this.statusTwo);
		return result;
	}

}
