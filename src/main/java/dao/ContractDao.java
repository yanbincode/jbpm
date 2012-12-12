package dao;

import model.Contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContractDao {

	@Autowired
	@Qualifier(value = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	public void insert(Contract contract) {
		hibernateTemplate.save(contract);
	}

	public Contract select(Long id) {
		return hibernateTemplate.get(Contract.class, id);
	}

	public void update(Contract contract) {
		hibernateTemplate.update(contract);
	}

}
