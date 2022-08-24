package utilities;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelDataDriven {

	@Test
	public List<String> handlingExcelTestData(String sheetName, String TestCaseName) throws Exception {

		List<String> storeTestData = new ArrayList<String>();

		String cellname = "Test Cases";
		FileInputStream fis = new FileInputStream("C:\\Users\\sabareesan.j\\Desktop\\TestData - Sample.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheetNos = workbook.getNumberOfSheets();

		for (int i = 0; i < sheetNos; i++) {

			String name = workbook.getSheetName(i);
			if (name.equalsIgnoreCase(sheetName)) {

				XSSFSheet sheet = workbook.getSheetAt(i);

				Iterator<Row> row = sheet.iterator();
				Row firstrow = row.next();

				Iterator<Cell> cell = firstrow.cellIterator();
				int counter = 0;
				int column = 0;
				while (cell.hasNext()) {
					Cell FirstCell = cell.next();

					if (FirstCell.getStringCellValue().equalsIgnoreCase(cellname)) {
						// System.out.println("Test Data");
						column = counter;
					}

					counter++;
				}

				System.out.println(column);

				while (row.hasNext()) {
					Row selectRow = row.next();

					if (selectRow.getCell(column).getStringCellValue().equalsIgnoreCase(TestCaseName)) {
						System.out.println("Reached the required Column");

						Iterator<Cell> cellCheck = selectRow.cellIterator();
						while (cellCheck.hasNext()) {
							Cell cellValues = cellCheck.next();
							// String fetchValues = cellValues.getStringCellValue();
							// System.out.println(fetchValues);

							if (cellValues.getCellType() == CellType.STRING) {
								storeTestData.add(cellValues.getStringCellValue());
							} else if ((cellValues.getCellType() == CellType.NUMERIC)) {
								String convertedValue = NumberToTextConverter.toText(cellValues.getNumericCellValue());
								storeTestData.add(convertedValue);
							}
						}

					}
				}
			}

		}
		workbook.close();

		return storeTestData;
	}

	@Test
	public void methodUsingExcelUtilities() throws Exception {

		System.out.println(handlingExcelTestData("Sample", "Savings Account"));

	}
}
