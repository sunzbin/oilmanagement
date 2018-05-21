package core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 从EXCEL导入到数据库
 * @version
 */
public class ObjectExcelRead {

	/**
	 * @param filepath //文件路径
	 * @param filename //文件名
	 * @param startrow //开始行号
	 * @param startcol //开始列号
	 * @param sheetnum //sheet
	 * @return list
	 */
	public static List<Map<String,String>> readExcel2003(String filepath, String filename, int startrow, int startcol, int sheetnum) {
		List<Map<String,String>> varList = new ArrayList<Map<String,String>>();
		try {
			File target = new File(filepath, filename);
			FileInputStream fi = new FileInputStream(target);
			HSSFWorkbook wb = new HSSFWorkbook(fi);
			HSSFSheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
			int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号
			for (int i = startrow; i < rowNum; i++) {					//行循环开始
				Map<String,String> varpd = new HashMap<String,String>();
				HSSFRow row = sheet.getRow(i); 							//行
				int cellNum = row.getLastCellNum(); 					//每行的最后一个单元格位置

				for (int j = startcol; j < cellNum; j++) {				//列循环开始
					
					HSSFCell cell = row.getCell(Short.parseShort(j + ""));
					String cellValue = null;
					if (null != cell) {
						switch (cell.getCellType()) { 					// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case 0:
							cellValue = String.valueOf((int) cell.getNumericCellValue());
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = cell.getNumericCellValue() + "";
							// cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue = "";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						}
					} else {
						cellValue = "";
					}
					varpd.put("var"+j, cellValue.length()==1?"0"+cellValue:cellValue);
				}
				varList.add(varpd);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return varList;
	}
	
	/**  
     * 判断是否为老版本的excel  
     * @Title: isExcel2003  
     * @param filePath   *.xls  
     * @return  
     * boolean  
     * @author wangqinghua   
     * @date 2017-3-21 上午11:05:19  
     */  
	@SuppressWarnings("resource")
	public static boolean isExcel2003(InputStream is) {  
        try {  
            new HSSFWorkbook(is);  
        } catch (Exception e) {  
            return false;  
        }  
        return true;  
    }  
      
    /**  
     * 判断是否为新版本的excel  
     * @Title: isExcel2007  
     * @param filePath   *.xlsx  
     * @return  
     * boolean  
     * @author wangqinghua   
     * @date 2017-3-21 上午11:05:43  
     */  
	@SuppressWarnings("resource")
	public static boolean isExcel2007(InputStream is) {  
        try {  
            new XSSFWorkbook( is);  
        } catch (Exception e) {  
            return false;  
        }  
        return true;  
    }  
    
    public static List<Map<String,String>> readExcel2007(String filepath, String filename, int startrow, int startcol, int sheetnum) {
		List<Map<String,String>> varList = new ArrayList<Map<String,String>>();
		try {
			File target = new File(filepath, filename);
			FileInputStream fi = new FileInputStream(target);
			XSSFWorkbook wb = new XSSFWorkbook(fi);
			XSSFSheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
			int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号
			for (int i = startrow; i < rowNum; i++) {					//行循环开始
				Map<String,String> varpd = new HashMap<String,String>();
				XSSFRow row = sheet.getRow(i); 							//行
				int cellNum = row.getLastCellNum(); 					//每行的最后一个单元格位置

				for (int j = startcol; j < cellNum; j++) {				//列循环开始
					
					XSSFCell cell = row.getCell(Short.parseShort(j + ""));
					String cellValue = null;
					if (null != cell) {
						System.out.println(cell.getCellType());
						switch (cell.getCellType()) { 					// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case 0:
							cellValue = String.valueOf((int) cell.getNumericCellValue());
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = cell.getNumericCellValue() + "";
							// cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue = "";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						}
					} else {
						cellValue = "";
					}
					varpd.put("var"+j, cellValue);
				}
				varList.add(varpd);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return varList;
	}
    
   
    
}
