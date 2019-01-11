package br.unicamp.ic.crawler.domain.jira;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.unicamp.ic.crawler.domain.core.IssueComment;
import br.unicamp.ic.crawler.domain.core.IssueEntry;
import br.unicamp.ic.crawler.domain.core.IssueActivityEntry;

/**
 * The <code>BZIssueEntry</code> class maps an issue entry. This class is
 * instantiated by Leitor.load method.
 * 
 * @see Channel
 * @see br.unicamp.ic.issueminer.haoop.infrastructure.LeitorXML#load()
 * 
 * @author Luiz Alberto
 * @version %I, %G
 * @since 1.0
 */
@XStreamAlias("rss")
public class JIRAIssueEntry implements IssueEntry {

	private Channel channel;

	@Override
	public String getDescription() {
		return channel.getItemDescription();
	}

	@Override
	public String getKey() {
		return channel.getItemKey();
	}

	@Override
	public int getKeySequential() {
		String key = channel.getItemKey();
		int pos = key.indexOf("-");
		if (pos == -1) {
			return -1;
		}
		return Integer.parseInt(key.substring(pos + 1));
	}

	@Override
	public String getType() {
		return channel.getType();
	}

	@Override
	public String getCreated() {
		return channel.getCreated();
	}

	@Override
	public String getUpdated() {
		return channel.getUpdated();
	}

	@Override
	public String getResolved() {
		return channel.getResolved();
	}

	@Override
	public String getSeverity() {
		String value = channel.getPriority();
		return value == null ? null : channel.getPriority().toLowerCase();
	}

	@Override
	public String getResolution() {
		return channel.getResolution();
	}

	@Override
	public String getStatus() {
		return channel.getStatus();
	}

	@Override
	public List<IssueComment> getComments() {
		List<IssueComment> comments = new ArrayList<IssueComment>();
		for (Comment c : channel.getComments()) {
			comments.add(c);
		}
		return comments;
	}

	@Override
	public String toString() {
		return channel.getItemKey();
	}

	@Override
	public String getAssignee() {
		return channel.getAssignee();
	}

	@Override
	public String getSummary() {
		return channel.getTitle();
	}

	@Override
	public String getVotes() {
		return channel.getVotes();
	}

	@Override
	public int getDaysToResolve() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("EEE, d MMM yyyy HH:mm:ss Z");
		DateTime startDate = formatter.parseDateTime(getCreated());
		DateTime endDate = formatter.parseDateTime(getResolved());
		Days days = Days.daysBetween(startDate.toLocalDate(), endDate.toLocalDate());
		return days.getDays();
	}

	@Override
	public String convertSeverityToCode() {
		String result = "0";

		if (this.getSeverity() != null) {
			String value = this.getSeverity();

			switch (value) {
			case "blocker":
				result = "5";
				break;
			case "critical":
				result = "4";
				break;
			case "major":
				result = "3";
				break;
			case "minor":
				result = "2";
				break;
			case "trivial":
				result = "1";
				break;
			}
		}
		return result;
	}

	@Override
	public String convertResolutionToCode() {
		String result = "0";
		String value = this.getResolution().toLowerCase();

		switch (value) {
		case "fixed":
			result = "1";
			break;
		case "duplicate":
			result = "2";
			break;
		case "unresolved":
			result = "3";
			break;
		case "won't fix":
			result = "4";
			break;
		case "cannot reproduce":
			result = "5";
			break;
		case "not a problem":
			result = "6";
			break;
		case "resolved":
			result = "7";
			break;
		case "implemented":
			result = "8";
			break;
		case "invalid":
			result = "9";
			break;
		case "later":
			result = "10";
			break;
		case "incomplete":
			result = "11";
			break;
		case "done":
			result = "12";
			break;
		}
		return result;
	}

	@Override
	public String convertTypeToCode() {
		String result = "0";
		String value = this.getType().toLowerCase();

		if (value.equals("improvement")) {
			result = "1";
		} else if (value.equals("bug")) {
			result = "2";
		} else if (value.equals("wish")) {
			result = "3";
		} else if (value.equals("test")) {
			result = "4";
		} else if (value.equals("new feature")) {
			result = "5";
		} else if (value.equals("sub-task")) {
			result = "6";
		} else if (value.equals("task")) {
			result = "7";
		} else if (value.equals("brainstorming")) {
			result = "8";
		} else if (value.equals("question")) {
			result = "9";
		} else if (value.equals("story")) {
			result = "10";
		} else if (value.equals("documentation")) {
			result = "11";
		} else if (value.equals("dependency upgrade")) {
			result = "12";
		} else if (value.equals("epic")) {
			result = "13";
		} else if (value.equals("umbrella")) {
			result = "14";
		} else if (value.equals("technical task")) {
			result = "15";
		} else if (value.equals("request")) {
			result = "16";
		}

		return result;

	}

	@Override
	public String converteStatusToCode() {
		String result = "0";
		String value = this.getStatus().toLowerCase();

		switch (value) {
		case "closed":
			result = "1";
			break;
		case "resolved":
			result = "2";
			break;
		case "reopened":
			result = "3";
			break;
		case "fixed":
			result = "4";
			break;
		}
		return result;
	}

	@Override
	public void registerActivity(IssueActivityEntry activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IssueActivityEntry> getActivities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReporter() {
		// TODO Auto-generated method stub
		return channel.getReporter();
	}

	@Override
	public String getProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getComponent() {
		// TODO Auto-generated method stub
		return null;
	}

}
