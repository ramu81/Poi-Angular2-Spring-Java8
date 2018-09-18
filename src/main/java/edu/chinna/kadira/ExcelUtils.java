package edu.chinna.kadira;

import static java.util.Objects.nonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static <T> void writeToExcelSheet(final String fileName, final String sheetName, final List<T> data) {
		File file = null;
		OutputStream fos = null;
		XSSFWorkbook workbook = null;
		try {
			file = new File(fileName);
			Sheet sheet = null;
			if (file.exists()) {
				workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
			} else {
				workbook = new XSSFWorkbook();
			}
			if (nonNull(workbook.getSheet(sheetName))) {
				workbook.removeSheetAt(workbook.getSheetIndex(sheetName));
			}
			sheet = workbook.createSheet(sheetName);
			List<String> fieldNames = getFieldNamesForClass(data.get(0).getClass());
			int rowCount = 0;
			int columnCount = 0;
			Row row = sheet.createRow(rowCount++);
			for (String fieldName : fieldNames) {
				Cell cell = row.createCell(columnCount++);
				cell.setCellValue(fieldName);
			}
			Class<? extends Object> classz = data.get(0).getClass();
			for (T t : data) {
				row = sheet.createRow(rowCount++);
				columnCount = 0;
				for (String fieldName : fieldNames) {
					Cell cell = row.createCell(columnCount);
					Method method = null;
					try {
						method = classz.getMethod("get" + capitalize(fieldName));
					} catch (NoSuchMethodException nme) {
						method = classz.getMethod("get" + fieldName);
					}
					Object value = method.invoke(t, (Object[]) null);
					if (nonNull(value)) {
						if (value instanceof String) {
							cell.setCellValue((String) value);
						} else if (value instanceof Long) {
							cell.setCellValue((Long) value);
						} else if (value instanceof Integer) {
							cell.setCellValue((Integer) value);
						} else if (value instanceof Double) {
							cell.setCellValue((Double) value);
						}
					}
					columnCount++;
				}
			}
			fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (nonNull(fos)) {
					fos.close();
				}
			} catch (IOException e) {
			}
			try {
				if (nonNull(workbook)) {
					workbook.close();
				}
			} catch (IOException e) {
			}
		}
	}

	// retrieve field names from a POJO class
	private static List<String> getFieldNamesForClass(Class<?> clazz) throws Exception {
		return Stream.of(clazz.getDeclaredFields()).parallel().map(e -> e.getName()).collect(Collectors.toList());
	}

	// capitalize the first letter of the field name for retrieving value of the
	// field later
	private static String capitalize(String s) {
		return s.isEmpty() ? s : s.substring(0, 1).toUpperCase() + s.substring(1);
	}

}