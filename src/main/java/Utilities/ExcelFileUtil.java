package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelFileUtil {
Workbook wb;
public ExcelFileUtil(String excelpath) throws Throwable{
	FileInputStream fi=new FileInputStream(excelpath);
	wb=WorkbookFactory.create(fi);
}
public int rowCount(String sheeetname){
	return wb.getSheet(sheeetname).getLastRowNum();
}
public int CellCount(String sheetname){
	return wb.getSheet(sheetname).getRow(0).getLastCellNum();
}
public String getCellData(String sheetname,int row,int column){
	String data="";
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC){
	int Celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
	data=String.valueOf(Celldata);
	}else{
		data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
public void setCellData(String sheetname,int row,int column,String Status,String writeexcel) throws Throwable{

	Sheet ws=wb.getSheet(sheetname);
	Row rownum=ws.getRow(row);
	Cell cell=rownum.createCell(column);
	cell.setCellValue(Status);
	if(Status.equalsIgnoreCase("pass")){
		CellStyle Style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		//font.setBoldwegiht(Font.BOLDWEIGHT_BOLD);
		Style.setFont(font);
		rownum.getCell(column).setCellStyle(Style);
		
	}else if(Status.equalsIgnoreCase("fail")){
		CellStyle Style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		//font.setBoldwegiht(Font.BOLDWEIGHT_BOLD);
		Style.setFont(font);
		rownum.getCell(column).setCellStyle(Style);
	}else if(Status.equalsIgnoreCase("blocked")){
		CellStyle Style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		//font.setBoldwegiht(Font.BOLDWEIGHT_BOLD);
		Style.setFont(font);
		rownum.getCell(column).setCellStyle(Style);
	}
	FileOutputStream fo=new FileOutputStream(writeexcel);
	wb.write(fo);
	
}
}
