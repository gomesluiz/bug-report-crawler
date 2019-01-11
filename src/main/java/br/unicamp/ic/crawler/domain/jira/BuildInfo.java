package br.unicamp.ic.crawler.domain.jira;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * The <code>BuildInfo</code> class represents the build information. 
 * This class is instantiated by Leitor.carrega method.
 * 
 * @author Luiz Alberto
 * @version %I, %G
 * @since 1.0
 */
@XStreamAlias("build-info")
final class BuildInfo {
	private String version;

	@XStreamAlias("build-number")
	private String buildNumber;

	@XStreamAlias("build-date")
	private String buidDate;

	@Override
	public String toString() {
		return "BuildInfo [version=" + version + ", buildNumber=" + buildNumber + ", buidDate=" + buidDate + "]";
	}

}
