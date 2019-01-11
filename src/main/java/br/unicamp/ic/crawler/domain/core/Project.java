package br.unicamp.ic.crawler.domain.core;

/**
 * 
 * @author luiz
 * 
 * TODO rename this class to Project
 * TODO create issue and history attributes
 * TODO create an interval class
 *
 */
public class Project {
	private String name;
	private String bts;
	
	// 	
	private String remoteIssueUrl;
	
	private String localIssuePath;
	private String issueFileFormat;
	
	//
	private String remoteIssueHistoryUrl;
	
	private String localIssueHistoryPath;
	private String issueHistoryFileFormat;
	
	//
	private String nameMask;
	
	//
	private int firstIssue;
	private int lastIssue;
	
	/**
	 * 
	 * @param name
	 * @param remoteIssueUrl
	 * @param localIssuePath
	 * @param issueFileFormat
	 * @param remoteIssueHistoryUrl
	 * @param localIssueHistoryPath
	 * @param issueHistoryFileFormat
	 * @param localNameMask
	 * @param firstIssue
	 * @param lastIssue
	 * @param bts TODO
	 */
	public Project(String name, String remoteIssueUrl, String localIssuePath, String issueFileFormat,
			String remoteIssueHistoryUrl, String localIssueHistoryPath, String issueHistoryFileFormat,
			String localNameMask, int firstIssue, int lastIssue, String bts) {
		super();
		this.name = name;
		this.remoteIssueUrl = remoteIssueUrl;
		this.localIssuePath = localIssuePath;
		this.issueFileFormat = issueFileFormat;
		this.remoteIssueHistoryUrl = remoteIssueHistoryUrl;
		this.localIssueHistoryPath = localIssueHistoryPath;
		this.issueHistoryFileFormat = issueHistoryFileFormat;
		this.nameMask = localNameMask;
		this.firstIssue = firstIssue;
		this.lastIssue = lastIssue;
		this.bts = bts;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the remoteIssueUrl
	 */
	public String getRemoteIssueUrl() {
		return remoteIssueUrl;
	}

	/**
	 * @return the localIssuePath
	 */
	public String getLocalReportFolder() {
		return localIssuePath;
	}

	/**
	 * @return the issueFileFormat
	 */
	public String getReportFileExtension() {
		return issueFileFormat;
	}

	/**
	 * @return the remoteIssueHistoryUrl
	 */
	public String getRemoteIssueHistoryUrl() {
		return remoteIssueHistoryUrl;
	}

	/**
	 * @return the localIssueHistoryPath
	 */
	public String getLocalIssueHistoryPath() {
		return localIssueHistoryPath;
	}

	/**
	 * @return the issueHistoryFileFormat
	 */
	public String getIssueHistoryFileFormat() {
		return issueHistoryFileFormat;
	}

	/**
	 * @return the localNameMask
	 */
	public String getNameMask() {
		return nameMask;
	}

	/**
	 * @return the firstIssue
	 */
	public int getFirstReportNumber() {
		return firstIssue;
	}

	/**
	 * @return the lastIssue
	 */
	public int getLastReportNumber() {
		return lastIssue;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String formatRemoteIssueUrl(int key) {
		return String.format(remoteIssueUrl, key);
	}

	/**
	 * 
	 * @param key
	 * @return the local file name formatted.
	 */
	public String formatLocalIssueFileName(int key) {
		StringBuilder fileName = new StringBuilder(localIssuePath);
		fileName.append(nameMask);
		fileName.append(".");
		fileName.append(issueFileFormat);
		return String.format(fileName.toString(), key);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String formatRemoteIssueHistoryUrl(int key) {
		return String.format(remoteIssueHistoryUrl, key);
	}

	/**
	 * 
	 * @param key
	 * @return the local file name formatted.
	 */
	public String formatLocalIssueHistoryFileName(int key) {
		StringBuilder fileName = new StringBuilder(localIssueHistoryPath);
		fileName.append(nameMask);
		fileName.append(".");
		fileName.append(issueHistoryFileFormat);
		return String.format(fileName.toString(), key);
	}
	@Override
	public String toString() {
		return super.toString();
	}

	public String getBts() {
		// TODO Auto-generated method stub
		return this.bts;
	}

	public String getNameWithKey(int key) {
		// TODO Auto-generated method stub
		return String.format(nameMask, key);
	}

	

}
