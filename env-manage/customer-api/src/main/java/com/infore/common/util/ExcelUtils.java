/**
 * 
 */
package com.infore.common.util;

import java.text.SimpleDateFormat;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @desc   
 * @class  ExcelUtils
 * @author  create author by deer
 * @date  2017年12月11日下午5:20:53
 */
public class ExcelUtils {
	
	// @描述：是否是2003的excel，返回true是2003   
    public static boolean isExcel2003(String filePath)  {    
        return filePath.matches("^.+\\.(?i)(xls)$");    
    }    
     
    //@描述：是否是2007的excel，返回true是2007   
    public static boolean isExcel2007OrHight(String filePath)  {    
        return filePath.matches("^.+\\.(?i)(xlsx)$");    
    }    
      
  /** 
   * 验证EXCEL文件 
   * @param filePath 
   * @return 
   */  
  public static boolean validateExcel(String filePath){  
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007OrHight(filePath))){    
            return false;    
        }    
        return true;  
  }  
  
  /**
	 * 
	 * @Title: dillErrorCell   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param errorCode
	 *                 0:null
	 *                 1:数据错误
	 * @param: @param workbook
	 * @param: @param sheetName
	 * @param: @param row
	 * @param: @param cellIndex
	 * @param: @param errorCellSet      
	 * @return: void      
	 * @throws
	 */
    public static void dillErrorCell(int errorCode,Workbook workbook,String sheetName,Row row, 
			int cellIndex,Set<String> errorCellSet) {
		
		CellStyle style = getCellStyle(errorCode,workbook);//数据错误
		String errorInfo = setErrorCellColor(sheetName,style,row,cellIndex);
     	 errorCellSet.add(errorInfo);
	}
	
	/**
	 * 设置错误单元格的颜色
	 * @Title: setErrorCellColor   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param style
	 * @param: @param row
	 * @param: @param column      
	 * @return: String      
	 * @throws
	 */
	public static String setErrorCellColor(String sheetName,CellStyle style,Row row, int column) {
		Cell cell = row.getCell(column);
		if(cell == null) {
			 cell = row.createCell(column);
		}
		cell.setCellStyle(style);
		StringBuffer sb = new StringBuffer();
		sb.append("sheetName=["+sheetName+"]")
		  .append("rowNum=["+row.getRowNum()+"]")
		  .append("cellNum=["+column+"]");
		
		return sb.toString();
	}
	
	
	
	/**
	 * 根据错误形式获取不同的颜色
	 * //solid 填充  foreground  前景色
	 * @Title: getCellStyle   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param errorCode
	 *              0:为空
	 *              1：数据错误
	 * @param: @param workbook
	 * @param: @return      
	 * @return: CellStyle      
	 * @throws
	 */
	public static CellStyle getCellStyle(int errorCode,Workbook workbook) {
		//生成单元格样式
	    CellStyle style = workbook.createCellStyle();
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		switch(errorCode) {
			case 0://cell为空
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				break;
			case 1://cell值错误
				style.setFillForegroundColor(IndexedColors.PINK.getIndex());
				break;
			default:
				break;
		}
		
		return style;
	}
	
	
	@SuppressWarnings({ "unused", "deprecation" })
	public static String getCellValue(Cell cell) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");  
		 int cellType = cell.getCellType();  
       String cellValue = null;  
       switch(cellType) {  
           case Cell.CELL_TYPE_STRING: //文本  
               cellValue = cell.getStringCellValue();  
               break;  
           case Cell.CELL_TYPE_NUMERIC: //数字、日期  
               if(DateUtil.isCellDateFormatted(cell)) {  
                   cellValue = fmt.format(cell.getDateCellValue()); //日期型  
               }  
               else {  
                   cellValue = String.valueOf(cell.getNumericCellValue()); //数字  
               }  
               break;  
           case Cell.CELL_TYPE_BOOLEAN: //布尔型  
               cellValue = String.valueOf(cell.getBooleanCellValue());  
               break;  
           case Cell.CELL_TYPE_BLANK: //空白  
               cellValue = null;  
               break;  
           case Cell.CELL_TYPE_ERROR: //错误  
               cellValue = null;  
               break;  
           case Cell.CELL_TYPE_FORMULA: //公式  
               cellValue = null;  
               break;  
           default:  
               cellValue = null;  
       }  
       
       return cellValue;
	}
  
  
}
