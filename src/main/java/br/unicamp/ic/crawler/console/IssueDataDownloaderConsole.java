package br.unicamp.ic.crawler.console;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

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
public class IssueDataDownloaderConsole {

  public static void main(final String[] args) throws FileNotFoundException {

    FileReader fileReader = new FileReader("repositories.xml");
    XStream xstream = new XStream();
    xstream.alias("projects", List.class);
    xstream.alias("project", Project.class);
    xstream.autodetectAnnotations(true);
    @SuppressWarnings("unchecked")
    List<Project> projects = (List<Project>) xstream.fromXML(fileReader);

    for (Project project : projects) {
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
}
