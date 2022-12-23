package in.reports.binding;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CITIZENS_PLANS_INFO")
public class CitizenPlan {
	
	@Id
	private Integer cid;
	private String planName;
	private String planStatus;
	private String cname;
	private String cmail;
	private String gender;
	private String ssn;
	private Long phno;

}
