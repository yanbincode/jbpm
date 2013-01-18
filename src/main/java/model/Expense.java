package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Expense implements Serializable {

	private static final long serialVersionUID = 7800873173602876982L;

	@Id
	@SequenceGenerator(name = "S_EXPENSE", sequenceName = "S_EXPENSE")
	@GeneratedValue(generator = "S_EXPENSE", strategy = GenerationType.SEQUENCE)
	private Long expId;
	private String reason;
	private BigDecimal money;
	private String processId;

	public Long getExpId() {
		return expId;
	}

	public void setExpId(Long expId) {
		this.expId = expId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

}
