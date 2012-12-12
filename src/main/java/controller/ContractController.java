package controller;

import model.Contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.ContractService;

@Controller
@RequestMapping("/contract.do")
public class ContractController {

	@Autowired
	private ContractService contractService;

	/**
	 * 草拟
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=draft", method = RequestMethod.GET)
	public String draft(ModelMap model) {
		Contract contract = new Contract();
		model.addAttribute("contract", contract);
		return "contract/draft";
	}

	@RequestMapping(params = "method=draft", method = RequestMethod.POST)
	public String draft(Contract contract, ModelMap model) {
		contractService.draft(contract);
		return "success";
	}

	/**
	 * 提交审核
	 * 
	 * @param cntId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=submit", method = RequestMethod.GET)
	public String submit(Long cntId, ModelMap model) {
		Contract contract = contractService.get(cntId);
		model.addAttribute("contract", contract);
		return "contract/submit";
	}

	@RequestMapping(params = "method=submit", method = RequestMethod.POST)
	public String submit(Contract contract, ModelMap model) {
		contractService.submit(contract);
		return "success";
	}

	/**
	 * 审核
	 * 
	 * @param cntId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=check", method = RequestMethod.GET)
	public String check(Long cntId, ModelMap model) {
		Contract contract = contractService.get(cntId);
		model.addAttribute("contract", contract);
		return "contract/check";
	}

	/**
	 * 审核通过
	 * 
	 * @param contract
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=approve", method = RequestMethod.POST)
	public String approve(Contract contract, ModelMap model) {
		contractService.approve(contract);
		return "success";
	}

	/**
	 * 拒绝，返回
	 * 
	 * @param contract
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=reject", method = RequestMethod.POST)
	public String reject(Contract contract, ModelMap model) {
		contractService.reject(contract);
		return "success";
	}

	/**
	 * 修改
	 * 
	 * @param cntId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=modify", method = RequestMethod.GET)
	public String modify(Long cntId, ModelMap model) {
		Contract contract = contractService.get(cntId);
		model.addAttribute("contract", contract);
		return "contract/modify";
	}

	@RequestMapping(params = "method=modify", method = RequestMethod.POST)
	public String modify(Contract contract, ModelMap model) {
		contractService.modify(contract);
		return "success";
	}

	@RequestMapping(params = "method=repeal", method = RequestMethod.POST)
	public String repeal(Contract contract, ModelMap model) {
		contractService.repeal(contract);
		return "success";
	}

}
