package br.unicamp.ic.crawler.domain.core;

import br.unicamp.ic.crawler.domain.bugzilla.BZHistoryParserInHtml;
import br.unicamp.ic.crawler.domain.bugzilla.BZReportParserInXml;
import br.unicamp.ic.crawler.domain.bugzilla.BZReportCrawlerInXml;
import br.unicamp.ic.crawler.domain.jira.JiraReportCrawlerInXml;
import br.unicamp.ic.crawler.domain.jira.JiraHistoryParserInHtml;
import br.unicamp.ic.crawler.domain.jira.JiraReportParserInXml;
import br.unicamp.ic.crawler.domain.meta.Project;
import br.unicamp.ic.crawler.persistence.ReportRepositoryFromFile;
import br.unicamp.ic.crawler.persistence.ReportRepository;

public class CrawlerFactory {

	public static final String BTS_BUGZILLA = "bugzilla";
	public static final String BTS_JIRA = "jira";

	public static ReportCrawler getInstance(Project project) {
		ReportCrawler crawler = null;
		//
		if (project.getBts().equals(BTS_BUGZILLA)) {
			ReportPasser issueParser = new BZReportParserInXml();
			HistoryParser historyParser = new BZHistoryParserInHtml();
			ReportRepository repository = new ReportRepositoryFromFile(project, issueParser, historyParser);
			crawler = new BZReportCrawlerInXml(project, repository);
		} else if (project.getBts().equals(BTS_JIRA)) {
			ReportPasser issueParser = new JiraReportParserInXml();
			HistoryParser historyParser = new JiraHistoryParserInHtml();
			ReportRepository repository = new ReportRepositoryFromFile(project, issueParser, historyParser);
			crawler = new BZReportCrawlerInXml(project, repository);
			crawler = new JiraReportCrawlerInXml(project, repository);
		}
		return crawler;
	}

}
