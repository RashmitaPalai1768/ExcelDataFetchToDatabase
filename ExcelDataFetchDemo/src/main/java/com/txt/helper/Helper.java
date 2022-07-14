package com.txt.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.txt.entity.ExcelEntity;

public class Helper {
	//check file is excel type
		public static boolean checkExcelFormat(MultipartFile file) {
			String contentType=file.getContentType();
			if(!contentType.equals("application/vnd.ms-excel")) {
				return true;
			}else {
				return false;
			}
		}
		//convert Excel to database
		public static List<ExcelEntity> convertToList(InputStream is){
			
			List<ExcelEntity> list=new ArrayList<>();
		    try {
				XSSFWorkbook workbook= new XSSFWorkbook(is);
				XSSFSheet sheet=workbook.getSheetAt(0);//name of the sheet
				int rownum=0;
				Iterator<Row> iterator=sheet.iterator();
				while(iterator.hasNext()){
					Row row=iterator.next();
					if(rownum==0) {
						rownum++;
						continue;
					}
				Iterator<Cell> cell=row.iterator();
				int cid=0;
				ExcelEntity p=new ExcelEntity();
				while(cell.hasNext()) {
					Cell next=cell.next();
					switch (cid) {
					case 0:
						p.setId((int)next.getNumericCellValue());
						break;
					case 1:
						p.setName(next.getStringCellValue());
	                    break;
					case 2:
						p.setAddress(next.getStringCellValue());
						
						break;
					case 3:
						p.setSalary(next.getNumericCellValue());
						break;
					default:
						break;
					}
					cid++;
				}
				list.add(p);
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return list;
		}
}
