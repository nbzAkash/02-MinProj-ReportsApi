package in.reports.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.reports.binding.CitizenPlan;
import in.reports.binding.SearchRequest;
import in.reports.service.ReportService;

@RestController
public class ReportServiceController {
	@Autowired
	private ReportService reportService;
	@GetMapping("/plannames")
	public ResponseEntity<List<String>> getPlanNames(){
		List<String> planNames = reportService.getPlanNames();
		return new ResponseEntity<>(planNames,HttpStatus.OK);
	}
	@GetMapping("/planstatuses")
	public ResponseEntity<List<String>> getPlanStatuses(){
		List<String> planStatuses = reportService.getPlanStatuses();
		return new ResponseEntity<>(planStatuses, HttpStatus.OK);
	}
	@PostMapping("/search")
	public ResponseEntity<List<CitizenPlan>> search(@RequestBody SearchRequest request){
		List<CitizenPlan> citizenPlans = reportService.getCitizenPlans(request);
		return new ResponseEntity<>(citizenPlans, HttpStatus.OK);
	}
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response)throws Exception {
		response.setContentType("application/octet-stream");
		
		String key = "Content-Disposition";
		String value = "attachment;filename=citizens.xlsx";
		
		response.setHeader(key, value);
		
		reportService.exportExcel(response);
		
		response.flushBuffer();
		
	}
	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse response)throws Exception {
		response.setContentType("application/pdf");
		
		String key = "Content-Disposition";
		String value = "attachement;filename=citizens.pdf";
		
		response.setHeader(key, value);
		
		reportService.exportPdf(response);
		
		response.flushBuffer();
	}

}
