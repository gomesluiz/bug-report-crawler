package br.unicamp.ic.crawler.domain.bugzilla;

import br.unicamp.ic.crawler.domain.core.IssueActivityEntry;

public class BZIssueActivityEntry implements IssueActivityEntry {
	private final String who;
	private final String when;
	private final String what;
	private final String removed;
	private final String added;

	public BZIssueActivityEntry(String who, String when, String what, String removed, String added) {
		this.who = who.toLowerCase();
		this.when = when.substring(0,10);
		this.what = what.toLowerCase();
		this.removed = removed.toLowerCase();
		this.added = added.toLowerCase();
	}

	@Override
	public String getWho() {
		return who;
	}

	@Override
	public String getWhen() {
		return when;
	}

	@Override
	public String getWhat() {
		return what;
	}

	@Override
	public String getRemoved() {
		return removed;
	}

	@Override
	public String getAdded() {
		return added;
	}

}
