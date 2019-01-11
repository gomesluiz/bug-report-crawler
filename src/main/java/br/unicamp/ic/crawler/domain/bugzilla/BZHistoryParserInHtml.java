package br.unicamp.ic.crawler.domain.bugzilla;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.unicamp.ic.crawler.domain.core.HistoryParser;
import br.unicamp.ic.crawler.domain.core.IssueActivityEntry;

public class BZHistoryParserInHtml implements HistoryParser {

	@Override
	public List<IssueActivityEntry> parse(String contents) {
		List<IssueActivityEntry> activities = new ArrayList<IssueActivityEntry>();
		Document doc = Jsoup.parse(contents);

		Elements tables = doc.select("table:has(th:contains(Who))");
		if (tables == null)
			return activities;

		Element table = tables.select("table:has(th:contains(Who))").last();
		if (table == null)
			return activities;

		Elements rows = table.select("tr");
		if (rows == null)
			return activities;

		String who = "", when = "", what = "", removed = "", added = "";
		for (int j = 1; j < rows.size(); j++) {
			int columns = rows.get(j).select("td").size();
			int shift = columns - 3;
			if (columns == 5) {
				who = rows.get(j).select("td").get(0).text();
				when = rows.get(j).select("td").get(1).text();
			}
			what = rows.get(j).select("td").get(shift).text();
			removed = rows.get(j).select("td").get(shift + 1).text();
			added = rows.get(j).select("td").get(shift + 2).text();
			BZIssueActivityEntry activity = new BZIssueActivityEntry(who, when, what, removed, added);
			activities.add(activity);
		}
		return activities;
	}

}
