package br.unicamp.ic.crawler.domain.bugzilla;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.thoughtworks.xstream.XStream;

import br.unicamp.ic.crawler.domain.core.ReportPasser;

/**
 * The <code>FormatConverterFromXml</code> class implements XML reading files.
 * 
 * @author Luiz Alberto
 * @version %I%, %G%
 * @since 1.0
 */
public class BZReportParserInXml implements ReportPasser {

	private XStream stream;

	public BZReportParserInXml() {
		stream = new XStream();
		stream.autodetectAnnotations(true);
		stream.ignoreUnknownElements();
	}

	@Override
	public Object parse(String contents) {
		InputStream xml = new ByteArrayInputStream(contents.getBytes());
		stream.processAnnotations(BZIssueEntry.class);
		return stream.fromXML(xml);
	}

}
