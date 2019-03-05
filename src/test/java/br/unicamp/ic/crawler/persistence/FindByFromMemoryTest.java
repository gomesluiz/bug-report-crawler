/**
 * 
 */
package br.unicamp.ic.crawler.persistence;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import br.unicamp.ic.crawler.domain.core.Report;

/**
 * @author luiz
 *
 */
public class FindByFromMemoryTest {

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

	@Ignore
	public final void findByABugReportResolvedIn155Days() {
		ReportRepository repository = new ReportRepositoryFromMemory();
		Report issue = repository.findBy("CORE_GRAVEYARD-13271");

		assertEquals("1999-09-07", issue.getCreated());
		assertEquals("2000-02-09", issue.getResolved());
		assertEquals("normal", issue.getSeverity());
		assertEquals("5", issue.getSeverityCode());

		assertEquals(155, issue.getDaysToResolve());
	}

	@Ignore
	public final void findByABugReportResolvedInZeroDays() {
		ReportRepository repository = new ReportRepositoryFromMemory();
		Report issue = repository.findBy("JDT-14582");

		assertEquals("2002-04-25", issue.getCreated());
		assertEquals("2002-04-25", issue.getResolved());
		assertEquals("major", issue.getSeverity());
		assertEquals("3", issue.getSeverityCode());

		assertEquals(0, issue.getDaysToResolve());
	}
	@Ignore
	public final void findByABugReportResolvedInNoDays() {
		ReportRepository repository = new ReportRepositoryFromMemory();
		Report issue = repository.findBy("WINE-9981");

		assertEquals("2007-10-10", issue.getCreated());
		assertEquals("2007-11-26", issue.getResolved());
		assertEquals("normal", issue.getSeverity());
		assertEquals("5", issue.getSeverityCode());

		assertEquals(47, issue.getDaysToResolve());
	}


}
