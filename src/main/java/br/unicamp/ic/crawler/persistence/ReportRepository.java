package br.unicamp.ic.crawler.persistence;

import java.util.List;

import br.unicamp.ic.crawler.domain.core.Report;
import br.unicamp.ic.crawler.domain.core.filters.ReportFilter;

public interface ReportRepository {

	
	void add(String target, String contents);
	int count();
	List<Report> findAll(int max, List<ReportFilter> filters);
	List<Report> findAll(String from, List<ReportFilter> filters);
	Report findBy(String key);
	
}
