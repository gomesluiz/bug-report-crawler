package br.unicamp.ic.crawler.persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import br.unicamp.ic.crawler.domain.core.IssueActivityEntry;
import br.unicamp.ic.crawler.domain.core.Project;
import br.unicamp.ic.crawler.domain.core.Report;

/**
 * The <code>CSVIssueFileWriter</code> class implements a file writer which uses
 * csv format to store a List of <code>IssueNode</code> instances in a specific
 * format.
 * 
 * <PRE>
 * CSVIssueFileWriter writer = new CSVFileWritter("file.csv", formatter);
 * writer.write(issues);
 * </PRE>
 * 
 * @author Luiz Alberto
 * @version 1.0
 * @see FileWriter
 *
 */
public class CSVIssueFileWriter implements IssueFileWriter {

	private String prefix;
	private CSVOutputFormatter issueformatter;

	/**
	 * CSVIssueFileWriter constructor
	 * 
	 * @param filePrefix
	 *            file
	 * @param issueFormatter
	 */
	public CSVIssueFileWriter(String filePrefix, CSVOutputFormatter issueFormatter) {
		this.prefix = filePrefix;
		this.issueformatter = issueFormatter;
	}

	/**
	 * Writes issues into csv file.
	 * 
	 * @param issues
	 *            to write.
	 */
	public void write(Project project, final List<Report> issues) {
		FileWriter writer1 = null, writer2 = null;
		CSVPrinter printer1 = null, printer2 = null;
		CSVFormat format = CSVFormat.EXCEL;
		try {
			String path = project.getLocalReportFolder();
			path = path.replace("xml", "csv");
			File folder = new File(path);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			String reportPath = path + this.prefix + "_bug_report_data.csv";
			String historyPath = path + this.prefix + "_bug_report_history_data.csv";

			writer1 = new FileWriter(reportPath);
			writer2 = new FileWriter(historyPath);

			printer1 = new CSVPrinter(writer1, format);
			printer2 = new CSVPrinter(writer2, format);

			printer1.printRecord(issueformatter.getHeaders(CSVOutputFormatter.ISSUE_HEADER_TYPE));
			printer2.printRecord(issueformatter.getHeaders(CSVOutputFormatter.HISTORY_HEADER_TYPE));

			for (Report issue : issues) {
				printer1.printRecord(issueformatter.format(issue));
				for (IssueActivityEntry activity : issue.getActivities()) {
					if (activity.getWhat().toLowerCase().contains("severity")) {
						printer2.printRecord(issueformatter.format(issue.getKey(), activity));
					}
				}
			}

		} catch (IOException e) {
			throw new RuntimeException("Erro: " + e.getMessage());
		} finally {
			try {
				if (writer1 != null) {
					writer1.flush();
					writer1.close();
				}
				if (writer2 != null) {
					writer2.flush();
					writer2.close();
				}
				if (printer1 != null) {
					printer1.close();
				}
				if (printer2 != null) {
					printer2.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("Erro: " + e.getMessage());
			}
		}

	}

}
