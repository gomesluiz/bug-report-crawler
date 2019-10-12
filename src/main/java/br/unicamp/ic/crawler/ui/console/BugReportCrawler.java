package br.unicamp.ic.crawler.ui.console;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.thoughtworks.xstream.XStream;

import br.unicamp.ic.crawler.domain.core.CrawlerFactory;
import br.unicamp.ic.crawler.domain.core.ReportCrawler;
import br.unicamp.ic.crawler.domain.core.filters.IssueFilterByResolution;
import br.unicamp.ic.crawler.domain.core.filters.IssueFilterByStatus;
import br.unicamp.ic.crawler.domain.core.filters.IssueFilterOutBySeverity;
import br.unicamp.ic.crawler.domain.meta.Project;
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
public class BugReportCrawler {

  public static void main(final String[] args) throws FileNotFoundException {

    FileReader fileReader = new FileReader("repositories.xml");
    XStream xstream = new XStream();
    xstream.allowTypesByWildcard(new String[] {
        "br.unicamp.ic.**"
    });
    
    xstream.alias("projects", List.class);
    xstream.alias("project", Project.class);
    xstream.autodetectAnnotations(true);
    @SuppressWarnings("unchecked")
    List<Project> projects = (List<Project>) xstream.fromXML(fileReader);
    
    Logger logger         = LogManager.getLogger(BugReportCrawler.class);
    PropertyConfigurator.configure("log4j.properties");
    
    CSVOutputFormatter formatter = new CSVRawIssueFormatter();
    String current = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    for (Project project : projects) {
      logger.trace(project.getName());
      if (project.isEnable()) {
        String fileName = String.format("%s_%s", current, project.getName());
        IssueFileWriter output = new CSVIssueFileWriter(fileName, formatter);
        ReportCrawler crawler = CrawlerFactory.getInstance(project);
        logger.trace("Start " + project.getName() + " !");
        crawler.search(new IssueFilterByStatus("Resolved", "Closed"));
        crawler.search(new IssueFilterByResolution("Fixed"));
        crawler.search(new IssueFilterOutBySeverity("Enhancement"));
        crawler.load(10000);
        crawler.export(output);
        logger.trace("Finish " + project.getName() + " !");
      }
    }
  }
}
