package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Contract implements Serializable {

	private static final long serialVersionUID = -4146930832415509200L;

	public static final String CONTRACT_DRAFT = "草拟";
	public static final String CONTRACT_SUBMIT = "提交";
	public static final String CONTRACT_REJECT = "拒绝";
	public static final String CONTRACT_APPROVE = "通过";
	public static final String CONTRACT_REPEAL = "作废";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_CONTRACT")
	@SequenceGenerator(name = "S_CONTRACT", allocationSize = 1, sequenceName = "S_CONTRACT")
	private Long cntId;
	private String comName;
	private String cntName;
	private String remark;
	private String status;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "DRAFTER_ID")
	private Role drafter;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "SUBMITER_ID")
	private Role submiter;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "CHECKER_ID")
	private Role checker;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "REPEALER_ID")
	private Role repealer;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "CREATOR_ID")
	private Role creator;
	private Date createdTime;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "MODIFIER_ID")
	private Role modifier;
	private Date modifiedTime;

	public Long getCntId() {
		return cntId;
	}

	public void setCntId(Long cntId) {
		this.cntId = cntId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getCntName() {
		return cntName;
	}

	public void setCntName(String cntName) {
		this.cntName = cntName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Role getCreator() {
		return creator;
	}

	public void setCreator(Role creator) {
		this.creator = creator;
	}

	public Role getModifier() {
		return modifier;
	}

	public void setModifier(Role modifier) {
		this.modifier = modifier;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Role getDrafter() {
		return drafter;
	}

	public void setDrafter(Role drafter) {
		this.drafter = drafter;
	}

	public Role getSubmiter() {
		return submiter;
	}

	public void setSubmiter(Role submiter) {
		this.submiter = submiter;
	}

	public Role getChecker() {
		return checker;
	}

	public void setChecker(Role checker) {
		this.checker = checker;
	}

	public Role getRepealer() {
		return repealer;
	}

	public void setRepealer(Role repealer) {
		this.repealer = repealer;
	}

}
