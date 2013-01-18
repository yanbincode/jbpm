package controller;

import model.Expense;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/expense.do")
public class ExpenseController {

	public String expense(String taskId, Expense expense, ModelMap model) {
		return null;
	}

}
