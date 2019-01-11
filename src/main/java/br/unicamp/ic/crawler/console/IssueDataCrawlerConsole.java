package br.unicamp.ic.crawler.console;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.unicamp.ic.crawler.domain.core.CrawlerFactory;
import br.unicamp.ic.crawler.domain.core.ReportCrawler;
import br.unicamp.ic.crawler.domain.core.Project;
import br.unicamp.ic.crawler.domain.core.filters.IssueFilterByResolution;
import br.unicamp.ic.crawler.domain.core.filters.IssueFilterByStatus;
import br.unicamp.ic.crawler.domain.core.filters.IssueFilterOutBySeverity;
import br.unicamp.ic.crawler.persistence.CSVIssueFileWriter;
import br.unicamp.ic.crawler.persistence.CSVOutputFormatter;
import br.unicamp.ic.crawler.persistence.CSVRawIssueFormatter;
import br.unicamp.ic.crawler.persistence.IssueFileWriter;

/**
 * Application main class.
 *
 * @author Luiz Alberto
 * @since 2016-01-02
 */
public class IssueDataCrawlerConsole {

	public static void main(final String[] args) {

		List<Project> projects = Arrays
				.asList(new Project("eclipse", "https://bugs.eclipse.org/bugs/show_bug.cgi?ctype=xml&id=%d",
						"/Users/luiz/Workspace/doctorate/data/eclipse/xml/", "xml",
						"https://bugs.eclipse.org/bugs/show_activity.cgi?id=%d",
						"/Users/luiz/Workspace/doctorate/data/eclipse/xml/", "html", "ECLIPSE-%d", 1, 530583,
						CrawlerFactory.BTS_BUGZILLA));

		Logger logger = LogManager.getRootLogger();
		CSVOutputFormatter formatter = new CSVRawIssueFormatter();
		IssueFileWriter output = new CSVIssueFileWriter("r2", formatter);

		for (Project project : projects) {
			ReportCrawler crawler = CrawlerFactory.getInstance(project);
			logger.trace("Start " + project.getName() + " !");
			crawler.load();
			crawler.search(new IssueFilterByStatus("Resolved", "Closed"));
			crawler.search(new IssueFilterByResolution("Fixed"));
			crawler.search(new IssueFilterOutBySeverity("Enhancement"));
			crawler.export(output);
			logger.trace("Finish " + project.getName() + " !");
		}
	}
}
