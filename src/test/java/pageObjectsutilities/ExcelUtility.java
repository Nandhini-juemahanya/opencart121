package pageObjectsutilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtility {
	
	
		public static FileInputStream fi;
		public static FileOutputStream fo;
		public static XSSFWorkbook wb;
		public static XSSFSheet ws;
		public static XSSFRow row;
		public static XSSFCell cell;
		public static CellStyle style;
		String path;
		
		public ExcelUtility(String path)
		{
			this.path=path;
		}
		
		public  int getRowcount(String sheetName) throws IOException
		{
			fi=new  FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(sheetName);
			int rowcount=ws.getLastRowNum();
			wb.close();
			fi.close();
			return rowcount;
		}
		
		public static int getCellcount(String sheetName,int rownum) throws IOException
		{
			fi=new  FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(sheetName);
			row=ws.getRow(rownum);
			int cellcount=row.getLastCellNum();
			wb.close();
			fi.close();
			return cellcount;
		}

		public static String getCellData(String sheetName,int rownum,int colnum) throws IOException
		{
			fi=new  FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(sheetName);
			row=ws.getRow(rownum);
			cell=row.getCell(colnum);
			
			DataFormatter formatter= new DataFormatter();
			String data;
			try
			{
				//data=cell.toString();
				
				
				data=formatter.formatCellValue(cell);//returns the formatted value of a cell as a String regardless of the cell type.
			}
			catch(Exception e)
			{
				data="";
				
			}
			wb.close();
			fi.close();
			return data;
			
			
			public static void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
			{
				File xlfile=new File(path);
				if(!xlfile.exists())   //if file notexists create new file
				{
				
				wb=new XSSFWorkbook(fi);
				fo=new FileOutputStream(path);
				 wb.write(fo);
				}
				
				fi=new  FileInputStream(path);
				wb=new XSSFWorkbook(fi);
				
				if(wb.getSheetIndex(sheetName)==-1)   //if sheet not exists then create new sheet
					wb.createSheet(sheetName);
				sheet=wb.getSheet(sheetName);
				
				if(ws.getRow(rownum)==null)   //if row not exists then create new row
				   ws.createRow(rownum);
				row=ws.getRow(rownum);
				
				cell=row.createCell(colnum);
				cell.setCellValue(data);
				fo=new FileOutputStream(path);
				wb.write(fo);
				wb.close();
				fi.close();
				fo.close();
			}

			public  void fillGreenColor(String sheetName,int rownum,int colnum) throws IOException
			{
				fi=new  FileInputStream(path);
				wb=new XSSFWorkbook(fi);
				ws=wb.getSheet(sheetName);
				
				row=ws.getRow(rownum);
				 cell=row.createCell(colnum);
				 
				style=wb.createCellStyle();
				
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				 
				 cell.setCellStyle(style);
				 
				 wb.write(fo);
				wb.close();
				fi.close();
				fo.close();
			}
				
			public static void fillRedColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
			{
				fi=new  FileInputStream(xlfile);
				wb=new XSSFWorkbook(fi);
				ws=wb.getSheet(xlsheet);
				row=ws.getRow(rownum);
				 cell=row.createCell(colnum);
				 
				style=wb.createCellStyle();
				
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				 
				 cell.setCellValue(data);
				 fo=new FileOutputStream(xlfile);
				 wb.write(fo);
				wb.close();
				fi.close();
				fo.close();
			}
				
				
				
				 
			
		}



}
