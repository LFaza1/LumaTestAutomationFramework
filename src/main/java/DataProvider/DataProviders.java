package DataProvider;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import Utilities.ExcelDataHandler;

public class DataProviders {
    public static ExcelDataHandler excelReader;

    @DataProvider(name = "addToCartWithNavigation")
    public static Object[][] getAddToCartWithNavigationData() throws IOException {
        excelReader = new ExcelDataHandler();
        excelReader.setWorkSheetName("AddToCartWithNavigation");
        Object[][] data = new Object[excelReader.getRowCount() - 1][1];
        for (int i = 1; i < excelReader.getRowCount(); i++) {
            data[i - 1][0] = excelReader.readExcelData(i);
        }
        excelReader.closeWorkbook();
        return data;
    }

    @DataProvider(name = "product")
    public static Object[][] getProductData() throws IOException {
        excelReader = new ExcelDataHandler();
        excelReader.setWorkSheetName("SingleProduct");
        Object[][] data = new Object[excelReader.getRowCount() - 1][1];
        for (int i = 1; i < excelReader.getRowCount(); i++) {
            data[i - 1][0] = excelReader.readExcelData(i);
        }
        excelReader.closeWorkbook();
        return data;
    }

    @DataProvider(name = "shippingAddress")
    public static Object[][] getShippingAddressData() throws IOException {
        excelReader = new ExcelDataHandler();
        excelReader.setWorkSheetName("SingleShippingAddress");
        Object[][] data = new Object[excelReader.getRowCount() - 1][1];
        for (int i = 1; i < excelReader.getRowCount(); i++) {
            data[i - 1][0] = excelReader.readExcelData(i);
        }
        excelReader.closeWorkbook();
        return data;
    }

    @DataProvider(name = "signUpDataWithEmailUpdate")
    public static Object[][] getSignUpData() throws IOException {
        excelReader = new ExcelDataHandler();
        excelReader.setWorkSheetName("SignUp");
        Object[][] data = new Object[excelReader.getRowCount() - 1][1];
        for (int i = 1; i < excelReader.getRowCount(); i++) {
            data[i - 1][0] = excelReader.readExcelData(i);
        }
        // get the email of the first row
        Hashtable<String, String> rowData = (Hashtable) data[0][0];
        String email = rowData.get("Email");
        // update the email of the first row
        updateEmail(1, email);
        excelReader.closeWorkbook();
        return data;
    }

    private static void updateEmail(int row, String email) throws IOException {
        // assumes that the email is in the 0-based column index 2
        excelReader.updateCellData(row, 2, incrementEmail(email));
    }

    private static String incrementEmail(String email) {
        String[] parts = email.split("@");
        String name = parts[0];
        String domain = parts[1];
        String[] nameParts = name.split("(?<=\\D)(?=\\d)");
        String baseName = nameParts[0];
        int number = Integer.parseInt(nameParts[1]);
        number++;
        return baseName + number + "@" + domain;
    }
}
