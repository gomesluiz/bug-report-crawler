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
		project = new Project("eclipse", "https://bugs.eclipse.org/bugs/show_bug.cgi?ctype=xml&id=%d",
				"/home/luiz/Workspace/issue-crawler/data/eclipse/xml/", "xml",
				"https://bugs.eclipse.org/bugs/show_activity.cgi?id=%d",
				"/home/luiz/Workspace/issue-crawler/data/eclipse/xml/", "html", "ECLIPSE-%d", 10000, 15000,
				CrawlerFactory.BTS_BUGZILLA);

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
		assertEquals(5, reports.size());
	}

	@Test
	public final void searchReportsWithFilterStatusResolved() {

		List<Report> reports = crawler.search(new IssueFilterByStatus("Resolved"));
		assertEquals(3, reports.size());
	}

	@Test
	public final void searchReportsWithFilterStatusUnconfirmed() {

		List<Report> reports = crawler.search(new IssueFilterByStatus("Unconfirmed"));
		assertEquals(0, reports.size());
	}

	@Test
	public final void searchReportsWithFilterStatusClosed() {

		List<Report> reports = crawler.search(new IssueFilterByStatus("Closed"));
		assertEquals(2, reports.size());
	}

	@Test
	public final void searchReportsWithFilterStatusResolvedOrClosed() {

		List<Report> reports = crawler.search(new IssueFilterByStatus("Resolved", "Closed"));
		assertEquals(5, reports.size());
	}

	@Test
	public final void searchReportsWithFilterResolutionFixed() {

		List<Report> reports = crawler.search(new IssueFilterByResolution("Fixed"));
		assertEquals(4, reports.size());
	}

	@Test
	public final void searchReportsWithFilterResolutionWontfix() {

		List<Report> reports = crawler.search(new IssueFilterByResolution("Wontfix"));
		assertEquals(0, reports.size());
	}

}
