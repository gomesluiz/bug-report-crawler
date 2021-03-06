package br.unicamp.ic.crawler.domain.bugzilla;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.crawler.domain.core.ReportCrawler;
import br.unicamp.ic.crawler.domain.core.Report;
import br.unicamp.ic.crawler.domain.core.filters.ReportFilter;
import br.unicamp.ic.crawler.domain.meta.Project;
import br.unicamp.ic.crawler.persistence.ReportRepository;
import br.unicamp.ic.crawler.persistence.URLResource;

/**
 * Extract information Bugzilla Tracking Systems.
 * 
 * @author Luiz Alberto
 * @version 1.0
 * 
 */
public class BZReportCrawlerInXml extends ReportCrawler {

	/**
	 * Constructs a IssueJiraExtraxtor instance.
	 * 
	 * @param project 
	 *        An instance of Project class.
	 * @param repository
	 * 		  An instance of Repository class.
	 */
	public BZReportCrawlerInXml(Project project, ReportRepository repository) {
		super();
		this.project = project;
		this.reports = new ArrayList<Report>();
		this.repository = repository;
	}

	@Override
	public String downloadFrom(String url) {
		String contents = null;
		try {
			URLResource urlResource = new URLResource(url);
			contents = urlResource.asString();

			String buffer = contents.toLowerCase();
			if (buffer.contains("<bug error=\"" + "invalidbugid" + "\"" + ">")
					|| buffer.contains("<bug error=\"" + "notfound" + "\"" + ">"))
				contents = null;
		} catch (Exception e) {
			subject.setMessage(e.getMessage());
		}

		return contents;
	}

	@Override
	public String formatRemoteReportUrl(int key) {
		return String.format(project.getReportUrl(), key);
	}

	@Override
	public String formatRemoteReportHistoryUrl(int key) {
		return String.format(project.getReportHistoryUrl(), key);
	}

	@Override
	public void search(ReportFilter filter) {
		filters.add(filter);
		return;
	}

}
