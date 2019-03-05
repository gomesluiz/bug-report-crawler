package br.unicamp.ic.crawler.domain.core;

import java.io.File;
import java.util.List;
import java.util.Random;

import br.unicamp.ic.crawler.domain.core.filters.ReportFilter;
import br.unicamp.ic.crawler.domain.meta.Project;
import br.unicamp.ic.crawler.persistence.IssueFileWriter;
import br.unicamp.ic.crawler.persistence.ReportRepository;

/**
 * The <code>ReportCrawler</code> abstract class offers template methods to down-
 * loads one or several issues from Bug Tracking System specified by an URL and
 * stores them into disk.
 * 
 * This software is licensed with an Apache 2 license, see
 * http://www.apache.org/licenses/LICENSE-2.0 for details.
 * 
 * @author Luiz Alberto (gomes.luiz@gmail.com)
 * 
 */
public abstract class ReportCrawler {

	protected Project project;
	protected List<Report> reports;
	protected ReportRepository repository;
	protected Subject subject;

	public abstract String downloadFrom(String url);

	public abstract String formatRemoteReportUrl(int key);

	public abstract String formatRemoteReportHistoryUrl(int key);

	public abstract List<Report> search(ReportFilter filter);

	/**
	 * Class constructor.
	 */
	public ReportCrawler() {
		subject = new Subject();
		new LoggerObserver(subject);
	}

	/**
	 * Downloads all bug reports starting on first until last bug report defined in
	 * a <code>Project<code> object.
	 */
	public final void getAll() {
		try {
			File folder = new File(project.getReportPath());
			int min = project.getFirstReportNumber();
			int max = project.getLastReportNumber();

			if (!folder.exists()) {
				folder.mkdirs();
			}

			for (int key = min; key <= max; key++) {
				getOne(key);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Downloads all bug reports starting on first until last bug report defined in
	 * a <code>Project<code> object.
	 * 
	 * @param percentage
	 *            percentage of bug reports to be downloaded.
	 */
	public final void getAll(double percentage) {
		try {
			subject.setMessage("Start " + project.getName() + " !");

			File folder = new File(project.getReportPath());
			int min = project.getFirstReportNumber();
			int max = project.getLastReportNumber();

			if (!folder.exists()) {
				folder.mkdirs();
			}

			Random generator = new Random();
			int start = repository.count() + 1;
			int end = (int) ((max - min) * (percentage / 100));
			for (int i = start; i <= end; i++) {
				int key = generator.nextInt((max - min) + 1) + min;
				subject.setMessage("[" + project.getName() + "]" + " Start at : " + start + " End at: " + end
						+ " Current : " + i + " Key :" + key);
				getOne(key);
			}
			subject.setMessage("Finish " + project.getName() + " !");
		} catch (Exception e) {
		
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Downloads one report whose id is defined by key.
	 * 
	 * @param key
	 *            report identified.
	 */
	protected final void getOne(int key) {

		try {
			String reportUrl = this.formatRemoteReportUrl(key);

			File localIssueFile = new File(project.formatLocalIssueFileName(key));
			File localHistoryFile = new File(project.formatLocalIssueHistoryFileName(key));

			if (localIssueFile.exists() && localHistoryFile.exists())
				return;

			String reportContent = downloadFrom(reportUrl);
			if (reportContent == null)
				return;

			String reportHistoryUrl = this.formatRemoteReportHistoryUrl(key);

			String reportHistoryContent = downloadFrom(reportHistoryUrl);
			if (reportHistoryContent == null)
				return;

			repository.add(project.formatLocalIssueFileName(key), reportContent);
			repository.add(project.formatLocalIssueHistoryFileName(key), reportHistoryContent);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());

		}
	}

	/**
	 * 
	 */
	public final void load() {
		reports = repository.findAll();
	}

	/**
	 * 
	 * @param out
	 */
	public void export(IssueFileWriter out) {
		out.write(this.project, this.reports);
	}

}