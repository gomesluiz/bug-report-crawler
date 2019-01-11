package br.unicamp.ic.crawler.domain.bugzilla;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.unicamp.ic.crawler.domain.core.IssueComment;

@XStreamAlias("long_desc")
public class BZIssueComment implements IssueComment{
	
	@XStreamAlias("commentid") 
	private int commentid;
	
	@XStreamAlias("comment_count") 
	private int comment_count;
	
	@XStreamAlias("who") 
	private String who;
	
	@XStreamAlias("bug_when") 
	private String bug_when;
	
	@XStreamAlias("thetext")
	private String thetext;
	
	public int getId() {
		return commentid;
	}
	public String getAuthor() {
		return who;
	}
	public String getCreated() {
		return bug_when;
	}
	public String getMessage() {
		return thetext;
	}
}
