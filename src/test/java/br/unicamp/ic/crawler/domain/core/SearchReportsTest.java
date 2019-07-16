package br.unicamp.ic.crawler.domain.core;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unicamp.ic.crawler.domain.bugzilla.BZReportCrawlerInXml;
import br.unicamp.ic.crawler.domain.core.filters.IssueFilterByResolution;
import br.unicamp.ic.crawler.domain.core.filters.IssueFilterByStatus;
import br.unicamp.ic.crawler.domain.core.filters.IssueNoFilter;
import br.unicamp.ic.crawler.domain.meta.Project;
import br.unicamp.ic.crawler.persistence.ReportRepository;
import br.unicamp.ic.crawler.persistence.ReportRepositoryFromMemory;


public class SearchReportsTest {

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

	@Test
	public final void searchReportsWithNoFilter() {

		List<Report> reports = crawler.search(new IssueNoFilter());
		assertEquals(12, reports.size());
	}

	@Test
	public final void searchReportsWithFilterStatusResolved() {

		List<Report> reports = crawler.search(new IssueFilterByStatus("Resolved"));
		assertEquals(6, reports.size());
	}

	@Test
	public final void searchReportsWithFilterStatusUnconfirmed() {

		List<Report> reports = crawler.search(new IssueFilterByStatus("Unconfirmed"));
		assertEquals(0, reports.size());
	}

	@Test
	public final void searchReportsWithFilterStatusClosed() {

		List<Report> reports = crawler.search(new IssueFilterByStatus("Closed"));
		assertEquals(6, reports.size());
	}

	@Test
	public final void searchReportsWithFilterStatusResolvedOrClosed() {

		List<Report> reports = crawler.search(new IssueFilterByStatus("Resolved", "Closed"));
		assertEquals(12, reports.size());
	}

	@Test
	public final void searchReportsWithFilterResolutionFixed() {

		List<Report> reports = crawler.search(new IssueFilterByResolution("Fixed"));
		assertEquals(10, reports.size());
	}

	@Test
	public final void searchReportsWithFilterResolutionWontfix() {

		List<Report> reports = crawler.search(new IssueFilterByResolution("Wontfix"));
		assertEquals(0, reports.size());
	}

}
