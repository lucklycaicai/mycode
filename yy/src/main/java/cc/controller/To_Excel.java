package cc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class To_Excel {

	public static void main(String[] args) throws Exception {
		
		
		Workbook  wkb = new XSSFWorkbook();
	 Sheet sheet=wkb.createSheet("test");  
	String path = "d:\\work\\2month.txt";
	BufferedReader br=new BufferedReader(new  InputStreamReader(new FileInputStream(new File(path)),"GBK"));
	String line=null;
	int count=0;
	Row row0=sheet.createRow(count++);  
//	Cell cell01=row0.createCell(0);  
//	Cell cell02=row0.createCell(1);  
//	Cell cell03=row0.createCell(2);  
//	Cell cell04=row0.createCell(3);  
//	Cell cell05=row0.createCell(4);  
//	Cell cell06=row0.createCell(5);  
//	Cell cell07=row0.createCell(6);  
//	Cell cell08=row0.createCell(7);  
//	Cell cell09=row0.createCell(8);  
//	Cell cell10=row0.createCell(9);  
//	Cell cell11=row0.createCell(10);  
	while((line=br.readLine())!=null){
		if(line.length()==0){
			continue;
		}
		Row row=sheet.createRow(count++);  
		Cell cell=row.createCell(2);  
		Cell cell2=row.createCell(1);  
		Cell cell3=row.createCell(3);  
		cell.setCellValue(line+" "+br.readLine());  
		cell2.setCellValue("SQL"+(count-1));  
		cell3.setCellValue("恢复数据");  
	}
	     FileOutputStream fos=new FileOutputStream("d:\\work\\11.xlsx");
	    wkb.write(fos);  
	    
	    fos.close();  
	}

}
