package br.unicamp.ic.crawler.domain.bugzilla;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.unicamp.ic.crawler.domain.core.IssueActivityEntry;
import br.unicamp.ic.crawler.domain.core.IssueComment;
import br.unicamp.ic.crawler.domain.core.IssueEntry;

@XStreamAlias("bugzilla")
public class BZIssueEntry implements IssueEntry {

	private Bug bug;
	private List<IssueActivityEntry> history;

	@Override
	public String getDescription() {
		String description = ISSUE_ENTRY_NA;
		List<BZIssueComment> comments = bug.getComments();
		
		if (comments.size() > 0) description = comments.get(0).getMessage();
		
		return description;
	}

	@Override
	public String getKey() {
		StringBuilder key = new StringBuilder(bug.getProduct());
		key.append("-");
		key.append(bug.getBugId());
		return key.toString();
	}

	@Override
	public int getKeySequential() {
		if (bug.getBugId().equals(""))
			return -1;

		return Integer.parseInt(bug.getBugId());
	}

	@Override
	public String getType() {
		return bug.getCfBugType();
	}

	@Override
	public String getCreated() {
		String result = bug.getCreationTs();
		if (result != null) {
			result = result.substring(0, 10);
		}
		return result;
	}

	@Override
	public String getUpdated() {
		String result = bug.getDeltaTs();
		if (result != null) {
			result = result.substring(0, 10);
		}
		return result;
	}

	@Override
	public String getResolved() {
		String result = this.getUpdated();
	  
		if (history != null) {
			for (IssueActivityEntry activity : history) {
				if (activity.getAdded().equals(ISSUE_STATUS_RESOLVED)) {
					result = activity.getWhen().substring(0, 10);
				}
			}
		}
		return result != null? result:"0000-00-00";
	}

	@Override
	public String getSeverity() {
		return bug.getBugSeverity().toLowerCase();
	}

	@Override
	public String getResolution() {
		return bug.getResolution();
	}

	@Override
	public String getStatus() {
		return bug.getBugStatus();
	}

	@Override
	public List<IssueComment> getComments() {
		return new ArrayList<IssueComment>(bug.getComments());
	}

	@Override
	public String getAssignee() {
		return bug.getAssigneeTo();
	}

	@Override
	public String getSummary() {
		return bug.getShortDesc();
	}

	@Override
	public String getVotes() {
		return bug.getVotes();
	}

	@Override
	public int getDaysToResolve() {

		if  (this.getResolved().equals("0000-00-00")) return -1;
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		LocalDate startDate = LocalDate.parse(this.getCreated(), formatter);
		LocalDate endDate = LocalDate.parse(this.getResolved(), formatter);
		Days days = Days.daysBetween(startDate, endDate);
		
		return days.getDays();
	}

	@Override
	public String convertSeverityToCode() {
		String result;
		String value = this.getSeverity();

		switch (value) {
		case "blocker":
			result = "6";
			break;
		case "critical":
			result = "5";
			break;
		case "major":
			result = "4";
			break;
		case "normal":
			result = "3";
		case "minor":
			result = "2";
			break;
		case "trivial":
			result = "1";
			break;
		case "enhancement":
			result = "7";
			break;
		default:
			result = "0";
		}
		return result;
	}

	@Override
	public String convertResolutionToCode() {
		String result;
		String value = bug.getResolution().toLowerCase();

		switch (value) {
		case "fixed":
			result = "1";
			break;
		case "invalid":
			result = "2";
			break;
		case "wontfix":
			result = "3";
			break;
		case "duplicate":
			result = "4";
			break;
		case "worksforme ":
			result = "5";
			break;
		default:
			result = "0";
			break;
		}
		return result;
	}

	@Override
	public String convertTypeToCode() {
		String result;
		String value = bug.getResolution().toLowerCase();

		switch (value) {
		case "defect":
			result = "1";
			break;
		default:
			result = "0";
			break;
		}
		return result;

	}

	@Override
	public String converteStatusToCode() {
		String result;
		String value = bug.getBugStatus().toLowerCase();

		switch (value) {
		case "unconfirmed":
			result = "1";
			break;
		case "confirmed":
			result = "2";
			break;
		case "in_progress":
			result = "3";
			break;
		case "resolved":
			result = "4";
			break;
		case "verified":
			result = "5";
			break;
		case "closed":
			result = "6";
			break;
		default:
			result = "0";
			break;
		}
		return result;

	}

	@Override
	public void registerActivity(IssueActivityEntry activity) {
		if (history == null) {
			history = new ArrayList<IssueActivityEntry>();
		}
		this.history.add(activity);
	}

	@Override
	public List<IssueActivityEntry> getActivities() {
		return history;
	}

	@Override
	public String getReporter() {
		return bug.getReporter();
	}

	@Override
	public String getProduct() {
		return bug.getProduct();
	}

	@Override
	public String getComponent() {
		return bug.getComponent();
	}

}
