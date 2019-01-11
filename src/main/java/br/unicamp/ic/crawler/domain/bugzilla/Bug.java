package br.unicamp.ic.crawler.domain.bugzilla;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * The <code>Bug</code> class represents a bug in Bugzilla context.
 * 
 * @author Luiz Alberto
 * @version %I, %G
 * @since 1.0
 */
@XStreamAlias("bug")
public class Bug {

	@XStreamAlias("bug_id")
	private String bugId;

	@XStreamAlias("creation_ts")
	private String creationTs;

	@XStreamAlias("short_desc")
	private String shortDesc;

	@XStreamAlias("delta_ts")
	private String deltaTs;

	@XStreamAlias("reporter_accessible")
	private String reporterAccessible;

	@XStreamAlias("cclist_accessible")
	private String cclistAccessible;

	@XStreamAlias("classification_id")
	private String classificationId;

	@XStreamAlias("classification")
	private String classification;

	@XStreamAlias("product")
	private String product;

	@XStreamAlias("component")
	private String component;

	@XStreamAlias("version")
	private String version;

	@XStreamAlias("rep_plataform")
	private String repPlataform;

	@XStreamAlias("op_sys")
	private String opSys;

	@XStreamAlias("bug_status")
	private String bugStatus;

	@XStreamAlias("resolution")
	private String resolution;

	@XStreamAlias("bug_file_loc")
	private String bugFileLoc;

	@XStreamAlias("status_whiteboard")
	private String statusWhiteboard;

	@XStreamAlias("keywords")
	private String keywords;

	@XStreamAlias("priority")
	private String priority;

	@XStreamAlias("bug_severity")
	private String bugSeverity;

	@XStreamAlias("target_milestone")
	private String targetMilestone;

	@XStreamAlias("everconfirmed")
	private String everConfirmed;

	@XStreamAlias("reporter")
	private String reporter;

	@XStreamAlias("assigned_to")
	private String assignedTo;

	@XStreamImplicit
	private List<String> cc = new ArrayList<String>();

	@XStreamAlias("cf_bug_type")
	private String cfBugType;

	@XStreamAlias("cf_lastconfirmedver")
	private String cfLastConfirmedVer;

	@XStreamAlias("cf_fix_difficulty")
	private String cfFixDifficulty;

	@XStreamAlias("votes")
	private String votes;

	@XStreamAlias("comment_sort_order")
	private String commentSortOrder;

	@XStreamImplicit(itemFieldName="long_desc")
	private List<BZIssueComment> descriptions = new ArrayList<BZIssueComment>();

	public String getBugId() {
		return returnEmptyIfNull(bugId);
	}

	public String getCfBugType() {
		return returnEmptyIfNull(cfBugType);
	}

	public String getCreationTs() {
		return returnEmptyIfNull(creationTs);
	}

	public String getDeltaTs() {
		return returnEmptyIfNull(deltaTs);
	}

	public String getBugSeverity() {
		return returnEmptyIfNull(bugSeverity);
	}

	public String getResolution() {
		return returnEmptyIfNull(resolution);
	}

	public String getBugStatus() {
		return returnEmptyIfNull(bugStatus);
	}

	public String getVotes() {
		return returnEmptyIfNull(votes);
	}

	public String getAssigneeTo() {
		return returnEmptyIfNull(assignedTo);
	}

	public String getReporter() {
		return returnEmptyIfNull(reporter);
	}
	
	public List<BZIssueComment> getComments() {
		return descriptions;
	}

	public String getShortDesc() {
		return returnEmptyIfNull(shortDesc);
	}

	private String returnEmptyIfNull(String s) {
		return s == null ? "" : s;
	}

	public String getProduct() {
		String result = returnEmptyIfNull(this.product);

		result = result.toUpperCase();
		result = result.replace(" ", "_");

		return result;
	}
	public String getComponent() {
		return this.component;
	}

}
