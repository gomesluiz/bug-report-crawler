package br.unicamp.ic.crawler.domain.core;

/**
 * 
 * @author 	Luiz Alberto
 * @version %I%, %G%
 *
 */
public interface ReportPasser {
	Object parse(String contents);
}