package br.unicamp.ic.crawler.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.unicamp.ic.crawler.domain.core.IssueActivityEntry;
import br.unicamp.ic.crawler.domain.core.Report;

/**
 * 
 * The <code>CSVRawIssueFormatter</code> class implements issue raw format.
 * 
 * @author luiz
 *
 */
public class CSVRawIssueFormatter implements CSVOutputFormatter {
	@Override
	public Object[] getHeaders(int header) {
		ArrayList<String> headers;

		if (header == ISSUE_HEADER_TYPE) {
			headers = new ArrayList<String>(Arrays.asList(
					"bug_id"
					, "creation_date"
					, "component_name"
					, "product_name"
					, "short_description"
					, "long_description"
					, "assignee_name"
					, "reporter_name"
					, "resolution_category"
					, "resolution_code"
					, "status_category"
					, "status_code"
					, "update_date"
					, "quantity_of_votes"
					, "quantity_of_comments"
					, "resolution_date"
					, "days_to_resolve"
					, "severity_category"
					, "severity_code"
					));
		} else {
			headers = new ArrayList<String>(Arrays.asList("Key", "Who", "When", "What", "Removed", "Added"));
		}

		return headers.toArray();
	}

	/**
	 * 
	 */
	@Override
	public List<Object> format(Report report) {

		List<Object> record = new ArrayList<Object>();

		String resolved = report.getResolved();
		int daysToResolve = report.getDaysToResolve();
		
		String description = report.getDescription();
		String summary = report.getSummary();
		
		description = description.replaceAll("\\&.*?\\;", "")
				.replaceAll("<.*?>", "")
				.replaceAll("\"", "")
				.replaceAll(",", "");
		summary = summary.replaceAll("\\&.*?\\;", "")
				.replaceAll("<.*?>", "")
				.replaceAll("\"", "")
				.replaceAll(",", "");
		
		record.add(report.getKey());
		record.add(report.getCreated());
		record.add(report.getComponent());
		record.add(report.getProduct());
		record.add(summary);
		record.add(description);
		record.add(report.getAssignee());
		record.add(report.getReporter());		
		record.add(report.getResolution());
		record.add(report.getResolutionCode());
		record.add(report.getStatus());
		record.add(report.getStatusCode());
		record.add(report.getUpdated());
		record.add(report.getVotes());
		record.add(report.getQuantityOfComments());
		record.add(resolved);
		record.add(daysToResolve);
		record.add(report.getSeverity());
		record.add(report.getSeverityCode());

		
		
		return record;
	}

	@Override
	public Iterable<?> format(String key, IssueActivityEntry activity) {
		List<Object> record = new ArrayList<Object>();

		record.add(key);
		record.add(activity.getWho());
		record.add(activity.getWhen());
		record.add(activity.getWhat());
		record.add(activity.getRemoved());
		record.add(activity.getAdded());

		return record;

	}
}
