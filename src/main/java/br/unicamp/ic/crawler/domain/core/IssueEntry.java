package br.unicamp.ic.crawler.domain.core;

import java.util.List;

public interface IssueEntry {

	final String ISSUE_ENTRY_NA = "NA";
	final String ISSUE_STATUS_RESOLVED = "resolved";

	String getAssignee();
	List<IssueComment> getComments();
	String getCreated();
	String getKey();
	int getKeySequential();
	String getDescription();
	String getSeverity();
	String convertSeverityToCode();
	String getResolution();
	String convertResolutionToCode();
	String getResolved();
	String getStatus();
	String converteStatusToCode();
	String getSummary();
	String getType();
	String convertTypeToCode();
	String getUpdated();
	String getVotes();
	String getReporter();
	String getProduct();
	String getComponent();
	

	int getDaysToResolve();

	void registerActivity(IssueActivityEntry activity);
	
	List<IssueActivityEntry> getActivities();
}