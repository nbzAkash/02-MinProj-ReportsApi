package in.reports.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.reports.binding.CitizenPlan;
import in.reports.binding.SearchRequest;

public interface ReportService {
	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<CitizenPlan> getCitizenPlans(SearchRequest request);
	
	public void exportExcel(HttpServletResponse response)throws Exception;
	
	public void exportPdf(HttpServletResponse response)throws Exception;

}
