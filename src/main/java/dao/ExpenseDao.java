package dao;

import model.Expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExpenseDao {

	@Autowired
	@Qualifier(value = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	public void insert(Expense expense) {
		hibernateTemplate.save(expense);
	}

	public Expense select(Long id) {
		return hibernateTemplate.get(Expense.class, id);
	}

	public void update(Expense expense) {
		hibernateTemplate.update(expense);
	}

}
