package service;

import model.Expense;

import org.springframework.stereotype.Service;

import dao.ExpenseDao;

@Service
public class ExpenseService {

	private ExpenseDao expenseDao;

	public void jbpmExecute(Expense expense) {
		expenseDao.update(expense);
	}

	public Expense get(Long id) {
		return expenseDao.select(id);
	}

	public void add(Expense expense) {

	}

	public void execute(Expense expense) {
	
	}

}
