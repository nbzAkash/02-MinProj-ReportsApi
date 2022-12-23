package in.reports.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import in.reports.binding.CitizenPlan;
import in.reports.binding.SearchRequest;

public interface ReportService {
	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<CitizenPlan> getCitizenPlans(SearchRequest request);
	
	public void exportExcel(HttpServletRequest response);
	
	public void exportPdf(HttpServletRequest response);

}
