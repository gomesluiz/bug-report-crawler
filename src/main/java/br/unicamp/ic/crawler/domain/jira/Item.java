package br.unicamp.ic.crawler.domain.jira;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * The <code>Item</code> class represents a item and allows access to its
 * contents using gets methods. This class is instantiated by Leitor.carrega
 * method.
 * 
 * @see br.unicamp.ic.issueminer.haoop.infrastructure.LeitorXML#load()
 * 
 * @author Luiz Alberto
 * @version %I, %G
 * @since 1.0
 */
@XStreamAlias("item")
final class Item {

	private String title;
	private String link; 
	private String project; 
	private String description;
	private String environment; 
	private String key;
	private String summary; 
	private String type;
	private String priority;
	private String status;

	@XStreamAlias("statusCategory")
	private String category; //?

	private String resolution;
	private String assignee;
	private String reporter;

	private String labels; //?
	private String created;
	private String updated;
	private String resolved;

	@XStreamImplicit
	private List<String> version = new ArrayList<String>(); //?

	@XStreamImplicit
	private List<String> component = new ArrayList<String>();

	private String due; //?
	private String votes;
	private String watchers;

	private String attachments;
	private String subtasks;
	
	private List<Comment> comments = new ArrayList<Comment>();

	/**
	 * Returns the description of the <code>Item</code>.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the description of the <code>Item</code>.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Returns the priority of the <code>Item</code>.
	 * 
	 * @return the key
	 */
	public String getPriority() {
		return priority;
	}
	
	/**
	 * Returns the type of the <code>Item</code>.
	 * 
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Returns the creation date of the <code>Item</code>.
	 * 
	 * @return the creation date
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * Returns the update date of the <code>Item</code>.
	 * 
	 * @return the updating date
	 */
	public String getUpdated() {
		return updated;
	}

	/**
	 * Returns the status of the <code>Item</code>.
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Returns the resolution of the <code>Item</code>.
	 * 
	 * @return the resolution status
	 */
	public String getResolution() {
		return resolution;
	}
	
	/**
	 * Returns the resolution date of the <code>Item</code>.
	 * 
	 * @return the resolution update
	 */
	public String getResolved() {
		return resolved;
	}

	public List<Comment> getComments(){
		return comments;
	}
	
	public String getAssignee() {
		return assignee;
	}
	
	@Override
	public String toString() {
		return "Item [title=" + title + ", link=" + link + ", project=" + project + ", description=" + description
				+ ", environment=" + environment + ", key=" + key + ", summary=" + summary + ", type=" + type
				+ ", priority=" + priority + ", status=" + status + ", category=" + category + ", resolution="
				+ resolution + ", assignee=" + assignee + ", reporter=" + reporter + ", labels=" + labels + ", created="
				+ created + ", updated=" + updated + ", version=" + version + ", component=" + component + ", due="
				+ due + ", votes=" + votes + ", watchers=" + watchers + ", attachments=" + attachments + ", subtasks="
				+ subtasks + "]";
	}

	public String getTitle() {
		return summary;
	}

	public String getVotes() {
		return votes;
	}

	public String getReporter() {
		// TODO Auto-generated method stub
		return reporter;
	}

}
