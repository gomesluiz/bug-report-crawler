package br.unicamp.ic.crawler.domain.jira;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import br.unicamp.ic.crawler.domain.core.Report;
import br.unicamp.ic.crawler.domain.core.ReportCrawler;
import br.unicamp.ic.crawler.domain.core.filters.ReportFilter;
import br.unicamp.ic.crawler.domain.meta.Project;
import br.unicamp.ic.crawler.persistence.ReportRepository;

/**
 * Extract information from Jira issues.
 * 
 * @author Luiz Alberto
 * @version 1.0
 * 
 */
public class JiraReportCrawlerInXml extends ReportCrawler {

  /**
   * Constructs a IssueJiraExtraxtor instance.
   * 
   * @param dataset   TODO
   * @param dataset   TODO
   * @param converter
   */
  public JiraReportCrawlerInXml(Project dataset, ReportRepository repository) {
    this.project = dataset;
    this.repository = repository;
  }

  @Override
  public String formatRemoteReportUrl(int key) {
    String name = project.getNameWithKey(key).toUpperCase();
    return String.format(project.getReportUrl(), name, name);
  }

  @Override
  public String formatRemoteReportHistoryUrl(int key) {
    String name = project.getNameWithKey(key).toUpperCase();
    return String.format(project.getReportHistoryUrl(), name);
  }

  @Override
  public void search(ReportFilter filter) {
    }

  @Override
  public String downloadFrom(String url) {
    String contents = null;
    try {
      Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
      contents = doc.html();
    } catch (Exception e) {
      subject.setMessage(e.getMessage());
    }
    return contents;
  }

}
