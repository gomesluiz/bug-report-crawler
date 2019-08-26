package br.unicamp.ic.crawler.ui.console;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.thoughtworks.xstream.XStream;

import br.unicamp.ic.crawler.domain.core.CrawlerFactory;
import br.unicamp.ic.crawler.domain.core.ReportCrawler;
import br.unicamp.ic.crawler.domain.meta.Project;

/**
 * Application main class.
 *
 * @author Luiz Alberto
 * @since 2016-01-02
 * 
 */
public class BugReportDownloader {

	public static void main(final String[] args) throws FileNotFoundException {

		FileReader fileReader = new FileReader("repositories.xml");
		XStream xstream = new XStream();
		xstream.allowTypesByWildcard(new String[] { "br.unicamp.ic.**" });

		xstream.alias("projects", List.class);
		xstream.alias("project", Project.class);
		xstream.autodetectAnnotations(true);
		@SuppressWarnings("unchecked")
		List<Project> projects = (List<Project>) xstream.fromXML(fileReader);

		Logger logger = LogManager.getLogger(BugReportCrawler.class);
		PropertyConfigurator.configure("log4j.properties");

		logger.trace("Start downloading !");
		for (Project project : projects) {
			if (project.isEnable()) {
				Thread th = new Thread(new Runnable() {

					@Override
					public void run() {
						ReportCrawler crawler = CrawlerFactory.getInstance(project);
						crawler.getAll(25);
					}
				});
				th.start();
			}
		}
		logger.trace("Finish downloading !");
	}
}
