/**
 * 
 */
package br.unicamp.ic.crawler.domain.bugzilla;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unicamp.ic.crawler.domain.core.IssueActivityEntry;
import br.unicamp.ic.crawler.domain.core.IssueEntry;
import br.unicamp.ic.crawler.domain.core.ReportPasser;
import br.unicamp.ic.crawler.persistence.ReportRepositoryFromMemory;

/**
 * @author luiz
 *
 */
public class ParseReportAndHistoryTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public final void parseAnIssue6FromBugzillaXmlValidFormat() {
		String xml = ReportRepositoryFromMemory.reports.get(6);	
		ReportPasser parser = new BZReportParserInXml();
		IssueEntry entry = (IssueEntry) parser.parse(xml);
		
		
		
		String html = ReportRepositoryFromMemory.histories.get(6);
		BZHistoryParserInHtml parserHistory = new BZHistoryParserInHtml();
		List<IssueActivityEntry> activities = parserHistory.parse(html);
		assertEquals(5, activities.size());
		
		IssueActivityEntry anActivity = activities.get(0);
		assertEquals("loewis", anActivity.getWho());
		assertEquals("2000-03-08", anActivity.getWhen());
		assertEquals("status", anActivity.getWhat());
		assertEquals("assigned", anActivity.getRemoved());
		assertEquals("assigned", anActivity.getAdded());
		entry.registerActivity(anActivity);
		
		anActivity = activities.get(1);
		assertEquals("jason", anActivity.getWho());
		assertEquals("2000-08-08", anActivity.getWhen());
		assertEquals("assignee", anActivity.getWhat());
		assertEquals("nobody", anActivity.getRemoved());
		assertEquals("jason", anActivity.getAdded());
		entry.registerActivity(anActivity);
		
		anActivity = activities.get(2);
		assertEquals("jason", anActivity.getWho());
		assertEquals("2000-08-08", anActivity.getWhen());
		assertEquals("status", anActivity.getWhat());
		assertEquals("assigned", anActivity.getRemoved());
		assertEquals("waiting", anActivity.getAdded());
		entry.registerActivity(anActivity);
		
		anActivity = activities.get(3);
		assertEquals("neil", anActivity.getWho());
		assertEquals("2000-11-25", anActivity.getWhen());
		assertEquals("status", anActivity.getWhat());
		assertEquals("waiting", anActivity.getRemoved());
		assertEquals("closed", anActivity.getAdded());
		entry.registerActivity(anActivity);
		
		anActivity = activities.get(4);
		assertEquals("pinskia", anActivity.getWho());
		assertEquals("2005-06-05", anActivity.getWhen());
		assertEquals("target milestone", anActivity.getWhat());
		assertEquals("---", anActivity.getRemoved());
		assertEquals("3.0.x", anActivity.getAdded());
		entry.registerActivity(anActivity);
		
		assertEquals("GCC-9", entry.getKey());
		assertEquals("2000-02-29", entry.getCreated());
		assertEquals("2019-01-18", entry.getUpdated());
		assertEquals("2000-11-25", entry.getResolved());
		assertEquals(270, entry.getDaysToResolve());
		assertEquals("", entry.getType());
		assertEquals("normal", entry.getSeverity());
		assertEquals("RESOLVED", entry.getStatus());
		assertEquals("martin", entry.getReporter());
		assertEquals("jason", entry.getAssignee());
		
		assertEquals("c++", entry.getComponent());
		assertEquals("GCC", entry.getProduct());
		
		assertEquals(10, entry.getComments().size());
		
	}

	@Test
	public final void parseAnIssue5FromBugzillaXmlValidFormat() {
		String xml = ReportRepositoryFromMemory.reports.get(5);	
		ReportPasser parser = new BZReportParserInXml();
		IssueEntry entry = (IssueEntry) parser.parse(xml);
		assertEquals("WINE-10518", entry.getKey());
		assertEquals("2007-11-20", entry.getCreated());
		assertEquals("2009-08-07", entry.getUpdated());
		assertEquals("2009-08-07", entry.getResolved());
		assertEquals(626, entry.getDaysToResolve());
		assertEquals("", entry.getType());
		assertEquals("normal", entry.getSeverity());
		assertEquals("CLOSED", entry.getStatus());
		assertEquals("epimetreus", entry.getReporter());
		assertEquals("wine-bugs", entry.getAssignee());
		
		assertEquals("directx-dsound", entry.getComponent());
		assertEquals("WINE", entry.getProduct());
		
		assertEquals(14, entry.getComments().size());
	}
	
	@Test
	public final void parseAnIssue4FromBugzillaXmlValidFormat() {
		String xml = ReportRepositoryFromMemory.reports.get(4);	
		ReportPasser parser = new BZReportParserInXml();
		IssueEntry entry = (IssueEntry) parser.parse(xml);
		assertEquals("JAVAEE-92086", entry.getKey());
		assertEquals("2007-01-09", entry.getCreated());
		assertEquals("2007-01-09", entry.getUpdated());
		assertEquals("2007-01-09", entry.getResolved());
		assertEquals(0, entry.getDaysToResolve());
		assertEquals("DEFECT", entry.getType());
		assertEquals("blocker", entry.getSeverity());
		assertEquals("RESOLVED", entry.getStatus());
		assertEquals("jhorvath", entry.getReporter());
		assertEquals("madamek", entry.getAssignee());
		
		assertEquals("EJB", entry.getComponent());
		assertEquals("JAVAEE", entry.getProduct());
		
		assertEquals(3, entry.getComments().size());
	}
	
	@Test
	public final void parseAnIssueHistoryFromBugzillaHtmlWith2Activities() {
		String html = ReportRepositoryFromMemory.histories.get(0);
		BZHistoryParserInHtml parser = new BZHistoryParserInHtml();
		List<IssueActivityEntry> activities = parser.parse(html);
		assertEquals(2, activities.size());
		
		IssueActivityEntry anActivity = activities.get(0);
		assertEquals("akiezun", anActivity.getWho());
		assertEquals("2002-04-25", anActivity.getWhen());
		assertEquals("status", anActivity.getWhat());
		assertEquals("new", anActivity.getRemoved());
		assertEquals("resolved", anActivity.getAdded());
		
		anActivity = activities.get(1);
		assertEquals("akiezun", anActivity.getWho());
		assertEquals("2002-04-25", anActivity.getWhen());
		assertEquals("resolution", anActivity.getWhat());
		assertEquals("---", anActivity.getRemoved());
		assertEquals("fixed", anActivity.getAdded());
		
	}

	@Test
	public final void parseAnIssueHistoryFromBugzillaHtmlWith5Activities() {
		String html = ReportRepositoryFromMemory.histories.get(4);
		BZHistoryParserInHtml parser = new BZHistoryParserInHtml();
		List<IssueActivityEntry> activities = parser.parse(html);
		
		assertEquals(5, activities.size());
		
		IssueActivityEntry anActivity = activities.get(0);
		assertEquals("jhorvath", anActivity.getWho());
		assertEquals("2007-01-09", anActivity.getWhen());
		assertEquals("keywords", anActivity.getWhat());
		assertEquals("", anActivity.getRemoved());
		assertEquals("regression", anActivity.getAdded());
		
		anActivity = activities.get(1);
		assertEquals("madamek", anActivity.getWho());
		assertEquals("2007-01-09", anActivity.getWhen());
		assertEquals("status", anActivity.getWhat());
		assertEquals("new", anActivity.getRemoved());
		assertEquals("resolved", anActivity.getAdded());
		
		anActivity = activities.get(2);
		assertEquals("madamek", anActivity.getWho());
		assertEquals("2007-01-09", anActivity.getWhen());
		assertEquals("resolution", anActivity.getWhat());
		assertEquals("---", anActivity.getRemoved());
		assertEquals("duplicate", anActivity.getAdded());
		
		anActivity = activities.get(3);
		assertEquals("quality", anActivity.getWho());
		assertEquals("2009-10-30", anActivity.getWhen());
		assertEquals("product", anActivity.getWhat());
		assertEquals("j2ee", anActivity.getRemoved());
		assertEquals("javaee", anActivity.getAdded());
		
		anActivity = activities.get(4);
		assertEquals("quality", anActivity.getWho());
		assertEquals("2009-10-30", anActivity.getWhen());
		assertEquals("component", anActivity.getWhat());
		assertEquals("ejb", anActivity.getRemoved());
		assertEquals("ejb", anActivity.getAdded());
	}

	@Test
	public final void parseAnIssueHistoryFromBugzillaHtmlWith7Activities() {
		String html = ReportRepositoryFromMemory.histories.get(3);
		BZHistoryParserInHtml parser = new BZHistoryParserInHtml();
		List<IssueActivityEntry> activities = parser.parse(html);
		
		assertEquals(7, activities.size());
		
		IssueActivityEntry anActivity = activities.get(0);
		assertEquals("susancragin", anActivity.getWho());
		assertEquals("2007-10-10", anActivity.getWhen());
		assertEquals("url", anActivity.getWhat());
		assertEquals("", anActivity.getRemoved());
		assertEquals("http://www.nuance.com", anActivity.getAdded());
		
		anActivity = activities.get(1);
		assertEquals("susancragin", anActivity.getWho());
		assertEquals("2007-10-11", anActivity.getWhen());
		assertEquals("cc", anActivity.getWhat());
		assertEquals("", anActivity.getRemoved());
		assertEquals("susancragin", anActivity.getAdded());
		
		anActivity = activities.get(2);
		assertEquals("susancragin", anActivity.getWho());
		assertEquals("2007-11-26", anActivity.getWhen());
		assertEquals("status", anActivity.getWhat());
		assertEquals("unconfirmed", anActivity.getRemoved());
		assertEquals("resolved", anActivity.getAdded());
		
		anActivity = activities.get(3);
		assertEquals("susancragin", anActivity.getWho());
		assertEquals("2007-11-26", anActivity.getWhen());
		assertEquals("resolution", anActivity.getWhat());
		assertEquals("---", anActivity.getRemoved());
		assertEquals("fixed", anActivity.getAdded());
		
		anActivity = activities.get(4);
		assertEquals("truiken", anActivity.getWho());
		assertEquals("2008-01-18", anActivity.getWhen());
		assertEquals("component", anActivity.getWhat());
		assertEquals("_obsolete_multimedia", anActivity.getRemoved());
		assertEquals("-unknown", anActivity.getAdded());
		
		anActivity = activities.get(5);
		assertEquals("dank", anActivity.getWho());
		assertEquals("2008-01-28", anActivity.getWhen());
		assertEquals("status", anActivity.getWhat());
		assertEquals("resolved", anActivity.getRemoved());
		assertEquals("closed", anActivity.getAdded());
		
		anActivity = activities.get(6);
		assertEquals("austinenglish", anActivity.getWho());
		assertEquals("2009-01-12", anActivity.getWhen());
		assertEquals("version", anActivity.getWhat());
		assertEquals("cvs/git", anActivity.getRemoved());
		assertEquals("unspecified", anActivity.getAdded());
	}
	
}
