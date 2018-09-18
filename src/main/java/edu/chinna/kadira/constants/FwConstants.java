package edu.chinna.kadira.constants;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.format.DateTimeFormatter;

public interface FwConstants {
	String INDEX = "index";
	String AUDIT_LOG = "AuditLogESB";
	String SAMPLE_XLSX_FILE_PATH = "./export.xlsx";
	DateTimeFormatter FORMATTER = ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
}
