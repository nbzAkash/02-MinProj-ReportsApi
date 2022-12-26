package in.reports.service;

import java.awt.Color;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.reports.binding.CitizenPlan;
import in.reports.binding.SearchRequest;
import in.reports.repo.CitizenPlanRepo;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepo cpRepo;

	@Override
	public List<String> getPlanNames() {
		return cpRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return cpRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> getCitizenPlans(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		if (request.getPlanName() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanName(request.getPlanName());
		}

		if (request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		if (request.getGender() != null && !request.getGender().equals("")) {
			entity.setGender(request.getGender());
		}

		Example<CitizenPlan> example = Example.of(entity);

		List<CitizenPlan> records = cpRepo.findAll(example);
		return records;
	}

	@Override
	public void exportExcel(HttpServletResponse response) throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Citizines Info");
		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Id");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("SSN");

		List<CitizenPlan> records = cpRepo.findAll();

		int dataRowIndex = 1;
		for (CitizenPlan record : records) {

			XSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(record.getCid());
			dataRow.createCell(1).setCellValue(record.getCname());
			dataRow.createCell(2).setCellValue(record.getGender());
			dataRow.createCell(3).setCellValue(record.getPlanName());
			dataRow.createCell(4).setCellValue(record.getPlanStatus());
			dataRow.createCell(5).setCellValue(record.getSsn());

			dataRowIndex++;
		}
		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}

	@Override
	public void exportPdf(HttpServletResponse response) throws Exception {

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.MAGENTA);

		Paragraph p = new Paragraph("Citizens Plans Info", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.3f, 3.5f, 2.5f, 3.5f, 3.5f, 2.3f });
		table.setSpacingBefore(10);

		// set table header data

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.pink);
		cell.setPadding(5);

		Font f = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Id", f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Status", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("SSN", font));
		table.addCell(cell);

		// set table data
		List<CitizenPlan> records = cpRepo.findAll();

		for (CitizenPlan record : records) {
			table.addCell(String.valueOf(record.getCid()));
			table.addCell(record.getCname());
			table.addCell(record.getGender());
			table.addCell(record.getPlanName());
			table.addCell(record.getPlanStatus());
			table.addCell(String.valueOf(record.getSsn()));

		}

		document.add(table);
		document.close();

	}

}
