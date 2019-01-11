package br.unicamp.ic.crawler.domain.jira;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * The <code>Channel</code> class represents a channel that is composed by
 * <code>Item</code> instances.
 * 
 * @see Item
 * @see BuildInfo
 * 
 * @author Luiz Alberto
 * @version %I, %G
 * @since 1.0
 */
@XStreamAlias("channel")
final class Channel {
	private String title;
	private String link;
	private String description;
	private String language;

	@XStreamAlias("build-info")
	private BuildInfo buildInfo;

	private Item item;

	/**
	 * Returns the key of the <code>Item</code>.
	 * 
	 * @return the key
	 */
	public String getItemKey() {
		return item.getKey();
	}

	/**
	 * Returns the priority of the <code>Item</code>.
	 * 
	 * @return the key
	 */
	public String getPriority() {
		return item.getPriority();
	}

	/**
	 * Returns the description of the <code>Item</code>.
	 * 
	 * @return the description
	 */
	public String getItemDescription() {
		return item.getDescription();
	}

	/**
	 * Returns the type of the <code>Item</code>.
	 * 
	 * @return the type
	 */
	public String getType() {
		return item.getType();
	}

	/**
	 * Returns the creation date of the <code>Item</code>.
	 * 
	 * @return the creation date
	 */

	public String getCreated() {
		return item.getCreated();
	}

	/**
	 * Returns the updating date of the <code>Item</code>.
	 * 
	 * @return the updating date
	 */

	public String getUpdated() {
		return item.getUpdated();
	}

	/**
	 * Returns the status of the <code>Item</code>.
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return item.getStatus();
	}

	/**
	 * Returns the resolution of the <code>Item</code>.
	 * 
	 * @return the resolution status
	 */
	public String getResolution() {
		return item.getResolution();
	}

	/**
	 * Returns the creation date of the <code>Item</code>.
	 * 
	 * @return the resolution date
	 */

	public String getResolved() {
		return item.getResolved();
	}

	/**
	 * Returns comments about the <code>Item<code>
	 * 
	 * @return comments
	 */
	public List<Comment> getComments() {
		return item.getComments();
	}

	/**
	 * Returns who was assigned the <code>Item</code>.
	 * 
	 * @return assignee
	 */
	public String getAssignee() {
		return item.getAssignee();
	}

	/**
	 * Returns the title of <code>Item</code>.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return item.getTitle();
	}
	
	/**
	 * Returns quantity of votes for <code>Item</code>
	 * 
	 * @return votes
	 */
	public String getVotes() {
		return item.getVotes();
	}
	@Override
	public String toString() {
		return "Channel [title=" + title + ", link=" + link + ", description=" + description + ", language=" + language
				+ ", buildInfo=" + buildInfo + ", item=" + item + "]";
	}

	public String getReporter() {
		// TODO Auto-generated method stub
		return item.getReporter();
	}

}
