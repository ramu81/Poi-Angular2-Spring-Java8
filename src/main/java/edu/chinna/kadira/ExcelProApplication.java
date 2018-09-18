package edu.chinna.kadira;

import static edu.chinna.kadira.constants.FwConstants.SAMPLE_XLSX_FILE_PATH;
import static edu.chinna.kadira.process.FWLogProcess.process;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import edu.chinna.kadira.service.FwLogService;

@SpringBootApplication
@EnableCaching
/**
 * 
 * @author rakondapalli
 *
 */
public class ExcelProApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelProApplication.class, args);
	}
	@Bean
	ApplicationRunner run(FwLogService pFwLogService) {
		process(pFwLogService);
		ExcelUtils.writeToExcelSheet(SAMPLE_XLSX_FILE_PATH, "Updated Sheet", pFwLogService.getFwLogs());
		return null;
	}
}
