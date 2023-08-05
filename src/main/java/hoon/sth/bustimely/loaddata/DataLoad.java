package hoon.sth.bustimely.loaddata;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataLoad {

    public static String getStringCellValue(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        } else {
            // Handle other types or throw an exception
            return "";
        }
    }

    public static double getNumericCellValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            return Double.parseDouble(cell.getStringCellValue());
        } else {
            // Handle other types or throw an exception
            return 0.0;
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(new File("businfo.xlsx"));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();

        String url = "";
        String user = "";
        String password = "";

        Connection conn = DriverManager.getConnection(url, user, password);

        String insertQuery = "INSERT INTO BusStops (ROUTE_ID, ROUTE_NAME, ORDER_NUM, NODE_ID, " +
                "ARS_ID, STATION_NAME, X_COORD, Y_COORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(insertQuery);

        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            pstmt.setString(1, getStringCellValue(row.getCell(0)));
            pstmt.setString(2, getStringCellValue(row.getCell(1)));
            pstmt.setInt(3, (int) getNumericCellValue(row.getCell(2)));
            pstmt.setString(4, getStringCellValue(row.getCell(3)));
            pstmt.setString(5, getStringCellValue(row.getCell(4)));
            pstmt.setString(6, getStringCellValue(row.getCell(5)));
            pstmt.setDouble(7, getNumericCellValue(row.getCell(6)));
            pstmt.setDouble(8, getNumericCellValue(row.getCell(7)));
            pstmt.executeUpdate();
        }

        pstmt.close();
        conn.close();
        workbook.close();
        fis.close();
    }
}
