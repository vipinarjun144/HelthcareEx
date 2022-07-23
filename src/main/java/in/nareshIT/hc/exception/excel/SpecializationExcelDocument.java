package in.nareshIT.hc.exception.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import in.nareshIT.hc.entity.Specialization;

public class SpecializationExcelDocument extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings({ "unused", "unchecked" })
		List<Specialization> list=(List<Specialization>) model.get("list");
		
		response.setHeader("Content-Disposition","attachment; filename=Specialization.xls");
		
		
		Sheet sheet =workbook.createSheet("SPECIALIZATION");
		setHead(sheet);
		setBody(sheet,list);
	}
	
	private void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("NOTE");
		
	}
	
	private void setBody(Sheet sheet,List<Specialization> list) {
		int rowCont=1;
		for(Specialization spec:list) {
			Row row=sheet.createRow(rowCont++);
			row.createCell(0).setCellValue(spec.getId());
			row.createCell(1).setCellValue(spec.getSpecCode());
			row.createCell(2).setCellValue(spec.getSpecName());
			row.createCell(3).setCellValue(spec.getSpecNote());
		}
		
	}

}
