package br.unicamp.ic.crawler.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.crawler.domain.core.HistoryParser;
import br.unicamp.ic.crawler.domain.core.IssueActivityEntry;
import br.unicamp.ic.crawler.domain.core.IssueEntry;
import br.unicamp.ic.crawler.domain.core.ReportPasser;
import br.unicamp.ic.crawler.domain.core.LoggerObserver;
import br.unicamp.ic.crawler.domain.core.Report;
import br.unicamp.ic.crawler.domain.core.Subject;
import br.unicamp.ic.crawler.domain.core.filters.ReportFilter;
import br.unicamp.ic.crawler.domain.meta.Project;

public class ReportRepositoryFromFile implements ReportRepository {

	private Project project;
	private ReportPasser reportParser;
	private HistoryParser historyParser;
	private Subject subject;

	public ReportRepositoryFromFile(Project project, ReportPasser issueParser, HistoryParser historyParser) {
		this.project = project;
		this.reportParser = issueParser;
		this.historyParser = historyParser;
		this.subject = new Subject();
		new LoggerObserver(this.subject);
	}

	@Override
	public List<Report> findAll(int max, List<ReportFilter> filters) {
		List<Report> reports = new ArrayList<Report>();
		File[] files = getReportFiles();
		IssueEntry entry;
		for (File reportFile : files) {
			try{
				entry = convertFrom(reportFile);
			} catch(RuntimeException e){
				subject.setMessage(reportFile.getName()+ " Converting Error.");
				continue;
			}
			Boolean filtered = true;
			Report report    = new Report(entry);
			
			for (ReportFilter filter:filters) 
				filtered = filtered && filter.filter(report);
			
			
			if (filtered) {
				subject.setMessage(reportFile.getName()+ " Included!");
				reports.add(new Report(entry));
				if (reports.size() >= max) break;
			} else {
				// clean up unnecessary files.
				String key   = report.getKey();
				int position = key.lastIndexOf('-'); 
				int number   = Integer.parseInt(key.substring(position+1));
				File historyFile = new File(project.formatLocalIssueHistoryFileName(number));
				
				if(historyFile.delete())
					subject.setMessage(historyFile.getName()+ " Removed ! ");
				
				if (reportFile.delete())
					subject.setMessage(reportFile.getName()+ " Removed ! ");
			}
		}
		return reports;
	}

	@Override
	public Report findBy(String key) {
		Report report = null;
		File[] files = getReportFiles();
		if (files == null)
			return null;
		for (File file : files) {
			if (file.getName().equals(key)) {
				IssueEntry entry = convertFrom(file);
				report = new Report(entry);
			}

		}
		return report;

	}

	/**
	 * 
	 * @param target
	 * @param contents
	 */
	public void add(String target, String contents) {
		try {

			FileWriter out = new FileWriter(target);
			BufferedWriter writer = new BufferedWriter(out);
			writer.write(contents);
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private IssueEntry convertFrom(File file) {
		FileResource fileResource = new FileResource(file);
		String contents = fileResource.asString();
		IssueEntry entry = (IssueEntry) reportParser.parse(contents);
		List<IssueActivityEntry> activities = extractHistory(entry.getKeySequential());
		for (IssueActivityEntry activity : activities) {
			entry.registerActivity(activity);
		}
		return entry;
	}

	private List<IssueActivityEntry> extractHistory(int key) {
		List<IssueActivityEntry> activities = new ArrayList<IssueActivityEntry>();
		if (key == -1)
			return activities;

		try {
			File file = new File(project.formatLocalIssueHistoryFileName(key));
			FileResource fileResource = new FileResource(file);
			String contents = fileResource.asString();

			activities = historyParser.parse(contents);
		} catch (Exception e) {
			System.err.println("FileResource: cannot access :" 
				+ project.formatLocalIssueHistoryFileName(key));
		}
		return activities;
	}

	private File[] getReportFiles() {
		File folder = new File(project.getReportPath());
		File[] files = new File[] {};
		if (folder.exists()) {
			files = folder.listFiles((dir, name) -> name.endsWith(project.getReportFormat()));
		}
		return files;
	}

	@Override
	public int count() {
		File files[] = getReportFiles();
		return files.length;
	}

}
