package dao;

import model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleUserDao {

	@Autowired
	@Qualifier(value = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	public Role select(Long id) {
		return hibernateTemplate.get(Role.class, id);
	}

}
