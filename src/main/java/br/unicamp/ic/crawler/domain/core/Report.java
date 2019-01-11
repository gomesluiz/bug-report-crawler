package br.unicamp.ic.crawler.domain.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The <code>Issue</code> represents an maintenance software issue.
 * 
 * @author Luiz Alberto
 * @version 1.0
 * @since 2016-05-24
 * 
 */
public class Report {
	static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");

	private IssueEntry entry;

	public Report(IssueEntry entry) {
		this.entry = entry;
		new ArrayList<IssueComment>();

	}

	/**
	 * Returns issue key.
	 * 
	 * @return Issue key.
	 */
	public String getKey() {
		return entry.getKey();
	}

	/**
	 * Returns creation date issue.
	 * 
	 * @return the creation date
	 */
	public String getCreated() {
		return entry.getCreated();
	}

	/**
	 * Returns the summary text.
	 * 
	 * @return the summary text
	 */
	public String getSummary() {
		return entry.getSummary();
	}

	/**
	 * Returns issue description.
	 * 
	 * @return description.
	 */
	public String getDescription() {
		return entry.getDescription();
	}

	/**
	 * Returns issue priority.
	 * 
	 * @return priority
	 */
	public String getSeverity() {
		return entry.getSeverity();
	}

	/**
	 * Returns resolve date issue.
	 * 
	 * @return the resolve date.
	 */
	public String getResolved() {
		return entry.getResolved();
	}

	/**
	 * Returns resolution action.
	 * 
	 * @return the resolution action.
	 */
	public String getResolution() {
		return entry.getResolution().toLowerCase();
	}

	/**
	 * Return issue status.
	 * 
	 * @return the issue status.
	 */
	public String getStatus() {
		return entry.getStatus().toLowerCase();
	}

	/**
	 * Returns issue type.
	 * 
	 * @return issue type.
	 */
	public String getType() {
		return entry.getType();
	}

	/**
	 * Returns update date issue.
	 * 
	 * @return the update date.
	 */
	public String getUpdated() {
		return entry.getUpdated();
	}

	/**
	 * Gets a collection of issues comments.
	 * 
	 * @return collection of comments.
	 */
	public List<IssueComment> getComments() {
		return this.entry.getComments();
	}

	/**
	 * Gets the issue type code.
	 * 
	 * @return type code
	 */
	public String getTypeCode() {
		return entry.convertTypeToCode();
	}

	/**
	 * Gets the issue severity code.
	 * 
	 * @return severity code
	 */
	public String getSeverityCode() {
		return entry.convertSeverityToCode();
	}

	/**
	 * Gets days to resolve a issue.
	 * 
	 * @return days to resolve
	 */
	public int getDaysToResolve() {

		return entry.getDaysToResolve();
	}

	/**
	 * Gets the issue resolution code
	 * 
	 * @return resolution code
	 */
	public String getResolutionCode() {
		return entry.convertResolutionToCode();
	}

	/**
	 * Gets the issue status code
	 * 
	 * @return the status code
	 */
	public String getStatusCode() {
		return entry.converteStatusToCode();
	}

	/**
	 * Gets the comment quantity.
	 * 
	 * @return the quantity of comments
	 */
	public int getQuantityOfComments() {
		List<IssueComment> comments = this.getComments();
		return comments.size();
	}

	/**
	 * Gets reporter of issue report
	 * 
	 * @return the assignee name
	 */
	public String getReporter() {
		return entry.getReporter();
	}
	
	/**
	 * Gets assignee of issue report
	 * 
	 * @return the assignee name
	 */
	public String getAssignee() {
		return entry.getAssignee();
	}

	/**
	 * Gets votes gives to report.
	 * 
	 * @return the votes gives to report
	 */
	public String getVotes() {
		return entry.getVotes();
	}

	
	public List<IssueActivityEntry> getActivities() {
		List<IssueActivityEntry> result = entry.getActivities();

		if (result == null)
			return new ArrayList<IssueActivityEntry>();

		return entry.getActivities();
	}

	public String toString() {
		return "Issue [key=" + getKey() + ", title=" + getSummary() + "]";
	}

	public String getComponent() {
		return entry.getComponent();
	}

	public String getProduct() {
		return entry.getProduct();
	}

}
