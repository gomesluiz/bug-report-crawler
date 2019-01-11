package br.unicamp.ic.crawler.console;

import java.util.Arrays;
import java.util.List;

import br.unicamp.ic.crawler.domain.core.CrawlerFactory;
import br.unicamp.ic.crawler.domain.core.Project;
import br.unicamp.ic.crawler.domain.core.ReportCrawler;

/**
 * Application main class.
 *
 * @author Luiz Alberto
 * @since 2016-01-02
 * https://hibernate.atlassian.net/si/jira.issueviews:issue-xml/HHH-12279/HHH-12279.xml
 */
public class IssueDataDownloaderConsole {

	public static void main(final String[] args) {

		List<Project> projects = Arrays.asList(
				new Project("eclipse", "https://bugs.eclipse.org/bugs/show_bug.cgi?ctype=xml&id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/eclipse/xml/", "xml",
						"https://bugs.eclipse.org/bugs/show_activity.cgi?id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/eclipse/xml/", "html", "ECLIPSE-%d", 1, 530583,
						CrawlerFactory.BTS_BUGZILLA),
				new Project("winehq", "https://bugs.winehq.org/show_bug.cgi?ctype=xml&id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/winehq/xml/", "xml",
						"https://bugs.winehq.org/show_activity.cgi?id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/winehq/xml/", "html", "WINEHQ-%d", 1, 45000,
						CrawlerFactory.BTS_BUGZILLA),
				new Project("freedesktop", "https://bugs.freedesktop.org/show_bug.cgi?ctype=xml&id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/freedesktop/xml/", "xml",
						"https://bugs.freedesktop.org/show_activity.cgi?id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/freedesktop/xml/", "html", "FREEDESKTOP-%d", 1, 104780,
						CrawlerFactory.BTS_BUGZILLA),
				new Project("netbeans", "https://netbeans.org/bugzilla/show_bug.cgi?ctype=xml&id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/netbeans/xml/", "xml",
						"https://netbeans.org/bugzilla/show_activity.cgi?id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/netbeans/xml/", "html", "NETBEANS-%d", 1, 300000,
						CrawlerFactory.BTS_BUGZILLA),
				new Project("gnome", "https://bugzilla.gnome.org/show_bug.cgi?ctype=xml&id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/gnome/xml/", "xml",
						"https://bugzilla.gnome.org/show_activity.cgi?id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/gnome/xml/", "html", "GNOME-%d", 1, 793083,
						CrawlerFactory.BTS_BUGZILLA),
				new Project("gcc", "https://gcc.gnu.org/bugzilla/show_bug.cgi?ctype=xml&id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/gcc/xml/", "xml",
						"https://gcc.gnu.org/bugzilla/show_activity.cgi?id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/gcc/xml/", "html", "GCC-%d", 1, 84157,
						CrawlerFactory.BTS_BUGZILLA),
				new Project("ooo", "https://bz.apache.org/ooo/show_bug.cgi?ctype=xml&id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/ooo/xml/", "xml",
						"https://bz.apache.org/ooo/show_activity.cgi?id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/ooo/xml/", "html", "OOO-%d", 1, 62067,
						CrawlerFactory.BTS_BUGZILLA),
				new Project("mozilla", "https://bugzilla.mozilla.org/show_bug.cgi?ctype=xml&id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/mozilla/xml/", "xml",
						"https://bugzilla.mozilla.org/show_activity.cgi?id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/mozilla/xml/", "html", "MOZILLA-%d", 1, 1000000,
						CrawlerFactory.BTS_BUGZILLA),
				new Project("kernel", "https://bugzilla.kernel.org/show_bug.cgi?ctype=xml&id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/kernel/xml/", "xml",
						"https://bugzilla.kernel.org/show_activity.cgi?id=%d",
						"/Users/luiz/Workspace/issue-crawler/data/kernel/xml/", "html", "KERNEL-%d", 1, 198745,
						CrawlerFactory.BTS_BUGZILLA),
				new Project("hhh", "https://hibernate.atlassian.net/si/jira.issueviews:issue-xml/%s/%s.xml",
						"/Users/luiz/Workspace/issue-crawler/data/hibernate/xml/", "xml",
						"https://hibernate.atlassian.net/browse/%s?page=com.atlassian.jira.plugin.system.issuetabpanels:changehistory-tabpanel", 
						"/Users/luiz/Workspace/issue-crawler/data/hibernate/xml/", "html", "HHH-%d", 1, 12129,
						CrawlerFactory.BTS_JIRA)
				);

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
