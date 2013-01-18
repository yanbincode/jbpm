package controller;

import jbpm.service.JbpmServiceImpl;
import model.Expense;

import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.ExpenseService;

@Controller
@RequestMapping("/expense.do")
public class ExpenseController {

	@Autowired
	private JbpmServiceImpl jbpmServiceImpl;
	@Autowired
	private ExpenseService expenseService;

	/**
	 * 
	 * @param processId
	 * @return
	 */
	@RequestMapping(params = "method=start", method = RequestMethod.GET)
	public String startProcess(String processId, ModelMap model) {
		Expense expense = new Expense();
		expense.setProcessId(processId);
		model.addAttribute("expense", expense);
		return "success";
	}

	/**
	 * 流程处理跳转
	 * 
	 * @param taskId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=formPage", method = RequestMethod.GET)
	public String formPage(String taskId, ModelMap model) {
		Long expId = Long.valueOf(jbpmServiceImpl.getProcessVariableByTaskId(taskId, "expId").toString());
		Expense expense = expenseService.get(expId);
		Task task = jbpmServiceImpl.getTaskById(taskId);
		model.addAttribute("expense", expense);
		model.addAttribute("taskId", taskId);
		return task.getFormResourceName();
	}

	@RequestMapping(params = "method=execute", method = RequestMethod.POST)
	public String execute(String taskId,Expense expense, ModelMap model) {
		expenseService.execute(expense);
		Task task = jbpmServiceImpl.getTaskById(taskId);
		model.addAttribute("expense",expense);
		return task.getFormResourceName();
	}

	@RequestMapping(params = "method=add", method = RequestMethod.POST)
	public String add(String taskId, Expense expense, ModelMap model) {
		expenseService.add(expense);
		Task task = jbpmServiceImpl.getTaskById(taskId);
		return task.getFormResourceName();
	}

	@RequestMapping(params = "method=modify", method = RequestMethod.POST)
	public String modify(String taskId, Expense expense, ModelMap model) {
		return null;
	}

}
