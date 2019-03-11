package br.unicamp.ic.crawler.domain.meta;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author Luiz Alberto
 * 
 *         TODO create an interval class
 *
 */
@XStreamAlias("project")
public class Project {
  private String name;
  private String bts;
  private String output;
  private boolean enable;

  private Report report;

  /**
   * 
   * @param name
   * @param bts                    
   * @param output TODO
   * @param remoteIssueUrl
   * @param localIssuePath
   * @param issueFileFormat
   * @param remoteIssueHistoryUrl
   * @param localIssueHistoryPath
   * @param issueHistoryFileFormat
   * @param localNameMask
   * @param firstIssue
   * @param lastIssue
   */
  public Project(String name, String bts, String output
      , String remoteIssueUrl, String localIssuePath, String issueFileFormat
      , String remoteIssueHistoryUrl, String localIssueHistoryPath
      , String issueHistoryFileFormat, String localNameMask
      , int firstIssue, int lastIssue) {
    this.name = name;
    this.bts = bts;
    this.output = output;
    this.enable = true;
    History history = new History(remoteIssueHistoryUrl, issueHistoryFileFormat
        , localIssueHistoryPath, localNameMask);
    this.report = new Report(remoteIssueUrl, issueFileFormat, firstIssue
        , lastIssue, localIssuePath, localNameMask,
        history);
  }

  /**
   * @return the project name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * 
   * @return the output folder 
   */
  public String getOutput() {
	  return this.output;
  }
  
  /**
   * @return The report URL.
   */
  public String getReportUrl() {
    return report.getUrl();
  }

  /**
   * @return The report local path.
   */
  public String getReportPath() {
    return report.getPath();
  }

  /**
   * @return The report format.
   */
  public String getReportFormat() {
    return report.getFormat();
  }

  /**
   * @return The report history URL.
   */
  public String getReportHistoryUrl() {
    return report.getHistoryUrl();
  }

  /**
   * @return the localIssueHistoryPath
   */
  public String getReportHistoryPath() {
    return report.getHistoryPath();
  }

  /**
   * @return the issueHistoryFileFormat
   */
  public String getHistoryFormat() {
    return report.getHistoryFormat();
  }

  /**
   * @return the localNameMask
   */
  public String getReportMask() {
    return report.getMask();
  }

  /**
   * @return the firstIssue
   */
  public int getFirstReportNumber() {
    return report.getFirst();
  }

  /**
   * @return the lastIssue
   */
  public int getLastReportNumber() {
    return report.getLast();
  }

  /**
   * 
   * @param key
   * @return
   */
  public String formatRemoteIssueUrl(int key) {
    return String.format(getReportUrl(), key);
  }

  /**
   * 
   * @param key
   * @return the local file name formatted.
   */
  public String formatLocalIssueFileName(int key) {
    StringBuilder fileName = new StringBuilder(this.getReportPath());
    fileName.append(getReportMask());
    fileName.append(".");
    fileName.append(getReportFormat());
    return String.format(fileName.toString(), key);
  }

  /**
   * 
   * @param key
   * @return
   */
  public String formatRemoteIssueHistoryUrl(int key) {
    return String.format(getReportHistoryUrl(), key);
  }

  /**
   * 
   * @param key
   * @return the local file name formatted.
   */
  public String formatLocalIssueHistoryFileName(int key) {
    StringBuilder fileName = new StringBuilder(getReportHistoryPath());
    fileName.append(getHistoryMask());
    fileName.append(".");
    fileName.append(getHistoryFormat());
    return String.format(fileName.toString(), key);
  }

  private String getHistoryMask() {
    return report.getHistoryMask();
  }

  public String getBts() {
    return this.bts;
  }

  public String getNameWithKey(int key) {
    return String.format(getReportMask(), key);
  }

  public boolean isEnable() {
    return this.enable;
  }
  
  @Override
  public String toString() {
    return(String.format("name: %s, mask: %s", this.name, this.getReportMask()));
  }

}
