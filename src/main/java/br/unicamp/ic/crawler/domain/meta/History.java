package br.unicamp.ic.crawler.domain.meta;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("history")
public class History {
  private String url;
  private String format;
  private String path;
  private String mask;

  public History(String url, String format, String path, String mask) {
    this.url = url;
    this.format = format;
    this.path = path;
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

}
