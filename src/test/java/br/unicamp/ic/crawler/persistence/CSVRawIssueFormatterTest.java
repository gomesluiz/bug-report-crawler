package br.unicamp.ic.crawler.persistence;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unicamp.ic.crawler.domain.bugzilla.BZReportCrawlerInXml;
import br.unicamp.ic.crawler.domain.bugzilla.BZReportParserInXml;
import br.unicamp.ic.crawler.domain.core.IssueEntry;
import br.unicamp.ic.crawler.domain.core.Report;
import br.unicamp.ic.crawler.domain.core.ReportCrawler;
import br.unicamp.ic.crawler.domain.core.ReportPasser;
import br.unicamp.ic.crawler.domain.core.filters.IssueNoFilter;
import br.unicamp.ic.crawler.domain.meta.Project;

public class CSVRawIssueFormatterTest {

	private static Project project;
	private static ReportRepository repository;
	private static ReportCrawler crawler;
 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		project = new Project("eclipse", 
				"bugzilla", 
				"/Users/luiz/Workspace/doctorate/long-lived-bug-prediction/notebooks/datasets/",
				"https://bugs.eclipse.org/bugs/show_bug.cgi?ctype=xml&amp;id=%d", 
				"/Users/luiz/Workspace/doctorate/data/eclipse/xml//Users/luiz/Workspace/doctorate/data/eclipse/xml/",
				"xml", 
				"https://bugs.eclipse.org/bugs/show_activity.cgi?id=%d", 
				"/Users/luiz/Workspace/doctorate/data/eclipse/xml/", 
				"html",
				"ECLIPSE-%d", 
				1, 
				530583);
 
		repository = new ReportRepositoryFromMemory();
		crawler = new BZReportCrawlerInXml(project, repository);

	}
 
	@Before
	public void setUp() throws Exception {
		crawler.load();
	}


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<Report> reports = crawler.search(new IssueNoFilter());
		CSVOutputFormatter formatter = new CSVRawIssueFormatter();
		System.out.println(formatter.format(reports.get(5)));
	}

}
