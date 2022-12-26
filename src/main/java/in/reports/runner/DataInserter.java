package in.reports.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.reports.binding.CitizenPlan;
import in.reports.repo.CitizenPlanRepo;

@Component
public class DataInserter implements ApplicationRunner {
	@Autowired
	private CitizenPlanRepo repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		CitizenPlan cp1 = new CitizenPlan();
		cp1.setCname("Riya");
		cp1.setGender("Female");
		cp1.setCmail("riya@gmial.com");
		cp1.setPhno(122984l);
		cp1.setPlanName("SNAP");
		cp1.setPlanStatus("Approved");
		cp1.setSsn(986545258l);
		
		CitizenPlan cp2 = new CitizenPlan();
		cp2.setCname("Mathew");
		cp2.setGender("Male");
		cp2.setCmail("mthy@gmial.com");
		cp2.setPhno(122914l);
		cp2.setPlanName("Mediaid");
		cp2.setPlanStatus("Denied");
		cp2.setSsn(666505258l);
		
		CitizenPlan cp3 = new CitizenPlan();
		cp3.setCname("john");
		cp3.setGender("Male");
		cp3.setCmail("asw@gmial.com");
		cp3.setPhno(167214l);
		cp3.setPlanName("SNAP");
		cp3.setPlanStatus("Approved");
		cp3.setSsn(666554258l);
		
		CitizenPlan cp4 = new CitizenPlan();
		cp4.setCname("Miu Ti");
		cp4.setGender("Other");
		cp4.setCmail("aee@gmial.com");
		cp4.setPhno(142214l);
		cp4.setPlanName("CCAP");
		cp4.setPlanStatus("Pending");
		cp4.setSsn(666545258l);
		
		CitizenPlan cp5 = new CitizenPlan();
		cp5.setCname("Avinash");
		cp5.setGender("Male");
		cp5.setCmail("avw@gmial.com");
		cp5.setPhno(120014l);
		cp5.setPlanName("SNAP");
		cp5.setPlanStatus("Denied");
		cp5.setSsn(666545256l);
		
		List<CitizenPlan> list = Arrays.asList(cp1,cp2,cp3,cp4,cp5);
		
		repo.saveAll(list);
	}
	
    

}
