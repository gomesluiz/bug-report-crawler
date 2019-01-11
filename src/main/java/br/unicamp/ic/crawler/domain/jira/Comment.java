package br.unicamp.ic.crawler.domain.jira;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

import br.unicamp.ic.crawler.domain.core.IssueComment;

@XStreamAlias("comment")
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"message"})
public class Comment implements IssueComment{
	
	@XStreamAsAttribute 
	private int id;
	
	@XStreamAsAttribute 
	private String author;
	
	@XStreamAsAttribute 
	private String created;
	
	@XStreamOmitField
	private String message;
	
	public int getId() {
		return id;
	}
	public String getAuthor() {
		return author;
	}
	public String getCreated() {
		return created;
	}
	public String getMessage() {
		return message;
	}
}
