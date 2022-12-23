package in.reports.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import in.reports.binding.CitizenPlan;
import in.reports.binding.SearchRequest;
import in.reports.repo.CitizenPlanRepo;

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
		
		if(request.getGender() !=null && !request.getGender().equals("")) {
			entity.setGender(request.getGender());
		}
		
		Example<CitizenPlan> example = Example.of(entity);
		
		List<CitizenPlan> records = cpRepo.findAll(example);
		return records;
	}

	@Override
	public void exportExcel(HttpServletRequest response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exportPdf(HttpServletRequest response) {
		// TODO Auto-generated method stub

	}

}
