package edu.chinna.kadira.process;

import static edu.chinna.kadira.constants.FwConstants.AUDIT_LOG;
import static edu.chinna.kadira.constants.FwConstants.FORMATTER;
import static edu.chinna.kadira.constants.FwConstants.SAMPLE_XLSX_FILE_PATH;
import static java.time.LocalDateTime.parse;
import static java.time.temporal.ChronoUnit.MILLIS;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted;
import static org.apache.poi.ss.usermodel.WorkbookFactory.create;
import static org.springframework.beans.BeanUtils.copyProperties;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import edu.chinna.kadira.dto.FwLogDto;
import edu.chinna.kadira.service.FwLogService;
import edu.chinna.kadira.vo.FwLogVo;

public class FWLogProcess {

	private static List<FwLogVo> mockDataList = new ArrayList<>();

	private static FwLogService fwLogService;

	public static void process(final FwLogService pFwLogService) {
		try {
			fwLogService = pFwLogService;
			FileInputStream fsIP = new FileInputStream(new File(SAMPLE_XLSX_FILE_PATH));
			Workbook workbook = create(fsIP);
			getList(workbook);
			getMap(mockDataList).entrySet().forEach(FWLogProcess::persist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Map<String, List<FwLogVo>> getMap(List<FwLogVo> mockDataList) {
		return mockDataList.stream().sorted(comparing(FwLogVo::getMsgId).thenComparing(FwLogVo::getTranTimestamp))
				.collect(groupingBy(FwLogVo::getMsgId));

	}

	private static void getList(Workbook workbook) {
		workbook.forEach(sheet -> {
			if (sheet.getSheetName().equalsIgnoreCase(AUDIT_LOG))
				sheet.forEach(FWLogProcess::readARow);
		});
	}

	private static void persist(final Entry<String, List<FwLogVo>> pEntry) {

		if (pEntry.getValue().size() == 2) {
			LocalDateTime startDate = null, endDate = null;
			FwLogDto lFwLog = new FwLogDto();
			for (FwLogVo fwLog : pEntry.getValue()) {
				if (startDate == null) {
					startDate = fwLog.getTranTimestamp();
					copyProperties(fwLog, lFwLog);
					lFwLog.setReq(fwLog.getModuleName());
				} else {
					endDate = fwLog.getTranTimestamp();
					lFwLog.setRes(fwLog.getModuleName());
				}
			}

			lFwLog.setDuration(startDate.until(endDate, MILLIS));

			if (lFwLog.getDuration() != 0) {
				fwLogService.saveFwLog(lFwLog);
			}
		}

	}

	/**
	 * 
	 * @param pRow
	 */
	private static void readARow(final Row pRow) {
		FwLogVo lLog = new FwLogVo();
		pRow.forEach(cell -> {
			switch (cell.getColumnIndex()) {
			case 0:
				lLog.setTranTimestamp(parse(printCellValue(cell), FORMATTER));
				break;
			case 1:
				lLog.setMsgId(printCellValue(cell));
				break;
			case 2:
				lLog.setModuleName(printCellValue(cell));
				break;
			case 3:
				lLog.setSourceName(printCellValue(cell));
				break;
			case 4:
				lLog.setUserId(printCellValue(cell));
				break;
			default:
				break;
			}
		});
		mockDataList.add(lLog);
	}

	private static String printCellValue(Cell cell) {
		String val = "";
		switch (cell.getCellTypeEnum()) {
		case BOOLEAN:
			System.out.print(cell.getBooleanCellValue());
			break;
		case STRING:
			val = cell.getRichStringCellValue().getString();
			break;
		case NUMERIC:
			if (isCellDateFormatted(cell)) {
				System.out.print(cell.getDateCellValue());
			} else {
				System.out.print(cell.getNumericCellValue());
			}
			break;
		case FORMULA:
			System.out.print(cell.getCellFormula());
			break;
		case BLANK:
			break;
		default:
		}
		return val;
	}
}
