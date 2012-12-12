package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = 6767924934161946763L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ROLE")
	@SequenceGenerator(name = "S_ROLE", allocationSize = 1, sequenceName = "S_ROLE")
	private Long roleId;
	private String roleName;
	private String roleLevel;
	private String userId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
