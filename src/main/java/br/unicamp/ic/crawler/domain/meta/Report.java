package br.unicamp.ic.crawler.domain.meta;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("report")
public class Report {
  private String url;
  private String format;
  private int first;
  private int last;
  private String path;
  private String mask;

  private final History history;

  public Report(String url, String format, int first, int last, String path
      , String mask, History history) {
    super();
    this.url = url;
    this.format = format;
    this.first = first;
    this.last = last;
    this.path = path;
    this.mask = mask;
    this.history = history;
  }

  public String getUrl() {
    return this.url;
  }

  public String getPath() {
    return this.path;
  }

  public String getFormat() {
    return this.format;
  }

  public String getMask() {
    return this.mask;
  }

  public int getFirst() {
    return this.first;
  }
  
  public int getLast() {
    return this.last;
  }
  
  public String getHistoryUrl() {
    return this.history.getUrl();
  }

  public String getHistoryPath() {
    return this.history.getPath();
  }

  public String getHistoryFormat() {
    return this.history.getFormat();
  }

  public String getHistoryMask() {
    return this.history.getMask();
  }
  
  
}
