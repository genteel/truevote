package co.afrolabs.truevote.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import co.afrolabs.truevote.constant.StateEnum;
import co.afrolabs.truevote.dao.ConstituencyDAO;
import co.afrolabs.truevote.dto.Constituency;

public class ConstituencyExcelReadUtil {

	private static Cell cell;
	private static Map<String, List<Constituency>> SenDistricts = new HashMap<String, List<Constituency>>();
	private static Map<String, Object> rowData;
	private static String stateName = "";

	public static Map<String, List<Constituency>> readSheet(byte[] bytes)
			throws Exception {
		List<Constituency> zones = null;
		HSSFWorkbook workbook;
		int rowCount = 0;
		int columnCount = 0;
		try (InputStream file = new ByteArrayInputStream(bytes)) {
			workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(1);
			Iterator<Row> rowIterator = sheet.iterator();
			System.out.println("sheets: " + sheet.getFirstRowNum() + " : "
					+ sheet.getLastRowNum());

			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				rowCount++;
				// For each row, iterate through each columns
				rowData = new HashMap<>();
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					columnCount++;
					System.out.println("point " + rowCount + " : "
							+ columnCount + " --- " + getValue().toString());
					if (getStates().contains(getValue().toString())) {
						rowCount = 1;
						stateName = getValue().toString();
						zones = new ArrayList<Constituency>();
						SenDistricts.put(stateName, zones);
						System.out.println(">>>>>>>>>>>>>>>>>>>>>1 : "
								+ SenDistricts.get(stateName));

					} else {
						if (rowCount >= 3 && columnCount == 2) {
							SenDistricts.get(stateName).add(
									new Constituency(getValue().toString()));
							System.out.println("Constituency Name : "
									+ getValue());
						}
					}
				}

				columnCount = 0;
				System.out.println("rowData: " + zones);
			}
			rowCount = 0;

			System.out.println("excelData: " + SenDistricts);
		}
		// FileOutputStream out =
		// new FileOutputStream(new File("C:\\ResultSheetUpload.xls"));
		// workbook.write(out);
		// out.close();
		return SenDistricts;
	}

	private static Object getValue() {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue().trim();
		case Cell.CELL_TYPE_BLANK:
			return "    ";
		default:
			return new Object();
		}
	}

	public static void main(String[] args) {
		try {
			File file = new File(
					"C:\\Java projects\\truevote\\Name-of-Senatorial-DistrictsFederal-and-State-Const.xls");
			byte[] byteArray = new byte[(int) file.length()];
			byteArray = FileUtils.readFileToByteArray(file);
			ConstituencyExcelReadUtil.readSheet(byteArray);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static List<String> getStates() {
		List<String> states = new ArrayList<String>();
		for (StateEnum s : StateEnum.values()) {
			states.add(s.toString().toUpperCase().replaceAll("_", " "));
		}

		return states;
	}
}
