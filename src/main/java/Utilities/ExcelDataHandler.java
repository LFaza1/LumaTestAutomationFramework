package Utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Constants.ConfigConstants;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Utility class to handle Excel file operations like reading and writing data
 */
public class ExcelDataHandler {
    XSSFWorkbook workbook;
    String workSheetName;
    FileInputStream fis;

    /**
     * Constructor - initializes workbook from the configured Excel file path
     */
    public ExcelDataHandler() throws IOException {
        fis = new FileInputStream(ConfigConstants.EXCELWORKBOOKPATH);
        workbook = new XSSFWorkbook(fis);
    }

    /**
     * Sets the worksheet name to be processed
     * 
     * @param workSheetName Name of the worksheet
     */
    public void setWorkSheetName(String workSheetName) {
        this.workSheetName = workSheetName;
    }

    /**
     * Gets the total number of rows in the current worksheet
     * 
     * @return Number of rows that contain data
     */
    public int getRowCount() throws IOException {
        XSSFSheet sheet = workbook.getSheet(workSheetName);
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     * Reads data from a specific row in the Excel sheet
     * 
     * @param rowNumber Row number(0-indexed) to read data from
     * @return Hashtable containing column headers as keys and cell values as values
     */
    public Hashtable<String, String> readExcelData(int rowNumber) throws IOException {
        XSSFSheet sheet = workbook.getSheet(workSheetName);
        Hashtable<String, String> data = new Hashtable<String, String>();
        XSSFRow titleRow = sheet.getRow(0); // First row contains column headers
        XSSFRow row = sheet.getRow(rowNumber);
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String cellValue = "";
            // Handle different cell types appropriately
            switch (cell.getCellType()) {
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    cellValue = String.valueOf((int) cell.getNumericCellValue());
                    break;
                case FORMULA:
                    cellValue = cell.getCellFormula();
                    break;
                default:
                    break;
            }
            data.put(titleRow.getCell(cell.getColumnIndex()).getStringCellValue(), cellValue);
        }
        return data;
    }

    /**
     * Updates a specific cell in the Excel sheet with new value
     * 
     * @param rowNumber    Row number of the cell to update
     * @param columnNumber Column number of the cell to update
     * @param newValue     New value to be set in the cell
     */
    public void updateCellData(int rowNumber, int columnNumber, String newValue) throws IOException {
        XSSFSheet sheet = workbook.getSheet(workSheetName);
        XSSFRow row = sheet.getRow(rowNumber);
        XSSFCell cell = row.getCell(columnNumber);
        cell.setCellValue(newValue);
        // Save changes to the Excel file
        FileOutputStream fos = new FileOutputStream(ConfigConstants.EXCELWORKBOOKPATH);
        workbook.write(fos);
        fos.close();
    }

    /**
     * Closes the workbook and input stream to release resources
     */
    public void closeWorkbook() throws IOException {
        workbook.close();
        fis.close();
    }
}