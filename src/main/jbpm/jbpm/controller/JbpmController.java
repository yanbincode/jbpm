package jbpm.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jbpm.service.JbpmServiceImpl;
import model.Contract;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import service.ContractService;

@Controller
@RequestMapping("/jbpm.do")
public class JbpmController {

	@Autowired
	private JbpmServiceImpl jbpmServiceImpl;
	@Autowired
	private ContractService contractService;

	/**
	 * 上传页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=uploadOne", method = RequestMethod.GET)
	public String uploadOne() {
		return "jbpm/upload_process";
	}

	/**
	 * 上传定义好的zip流程
	 * 
	 * @param name
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=uploadOne", method = RequestMethod.POST)
	public String uploadOne(String name, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileDir = request.getSession().getServletContext().getRealPath("/") + "temp_file";
		if (!file.isEmpty()) {
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fileDir, file.getOriginalFilename()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "success";
	}

	/**
	 * 查看已部署的流程<br>
	 * 可以考虑将zip包存放数据库，并对应的流程名，读取成列表及状态。比这种遍历文件系统要好
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=showProcess", method = RequestMethod.GET)
	public String showProcessZip(HttpServletRequest request, ModelMap model) {
		String fileDir = request.getSession().getServletContext().getRealPath("/temp_file/");
		File file = new File(fileDir);
		List<String> list = new ArrayList<String>();
		String fileName = "";
		// 遍历所有的zip包
		File[] files = file.listFiles();
		for (File subFile : files) {
			fileName = subFile.getName();
			if (null == subFile || StringUtils.isEmpty(fileName) || StringUtils.contains(fileName, ".svn")) {
				continue;
			}
			fileName = StringUtils.substring(fileName, 0, StringUtils.indexOf(fileName, "."));
			list.add(fileName);
		}
		model.addAttribute("processList", list);
		return "jbpm/deploy_process";
	}

	/**
	 * 部署一个流程
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=deplay", method = RequestMethod.GET)
	public String deplayProcess(HttpServletRequest request, String processName) throws IOException {
		// 获取流程打包的配置
		String fileDir = request.getSession().getServletContext().getRealPath("/temp_file/") + processName + ".zip";
		File file = new File(fileDir);
		jbpmServiceImpl.deployProcess(file);
		return "success";
	}

	/**
	 * 显示所有流程定义
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=processList", method = RequestMethod.GET)
	public String processList(ModelMap model) {
		List<ProcessDefinition> processDefinitions = jbpmServiceImpl.getProcessDefinitions();
		model.addAttribute("processDefinitions", processDefinitions);
		return "jbpm/process_list";
	}

	/**
	 * 启动一个流程
	 * 
	 * @param processId
	 * @return
	 */
	@RequestMapping(params = "method=startProcess", method = RequestMethod.GET)
	public String startProcess(String processId) {

		// 启动流程的同事，增加业务合同数据。关联实例
		Long cntId = contractService.add(new Contract());

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("cntId", cntId);
		// 当前登录人或者当前登录人的角色（我们系统可以直接获取） 模拟ID为1
		variables.put("owner", "1");

		// 赋值变量,保存ID
		jbpmServiceImpl.startProcessById(processId, variables);

		return "success";
	}

	/**
	 * 删除一个流程
	 * 
	 * @param processId
	 * @return
	 */
	@RequestMapping(params = "method=deleteProcess", method = RequestMethod.GET)
	public String deleteProcess(String processId) {
		jbpmServiceImpl.deleteProcess(processId);
		return "success";
	}

	/**
	 * 显示任务
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=taskList", method = RequestMethod.GET)
	public String taskList(ModelMap model) {
		// 根据当前登录人来获取对应的任务 ，也可以直接进入全部的任务页面供跟踪
		List<Task> tasks = jbpmServiceImpl.getTasks();
		model.addAttribute("tasks", tasks);
		return "jbpm/task_list";
	}

	@RequestMapping(params = "method=formPage", method = RequestMethod.GET)
	public String formPage(String taskId, ModelMap model) {
		Long cntId = Long.valueOf(jbpmServiceImpl.getProcessVariableByTaskId(taskId, "cntId").toString());
		Contract contract = contractService.get(cntId);
		Task task = jbpmServiceImpl.getTaskById(taskId);
		model.addAttribute("contract", contract);
		model.addAttribute("taskId", taskId);
		return task.getFormResourceName();
	}

	@RequestMapping(params = "method=viewProcessImage", method = RequestMethod.GET)
	public String viewProcessImage(String instanceId, ModelMap model) {
		Map<String, Object> point = jbpmServiceImpl.getImagePoint(instanceId);
		model.addAttribute("point", point);
		model.addAttribute("instanceId", instanceId);
		return "jbpm/view_image";
	}

	@RequestMapping(params = "method=getImage", method = RequestMethod.GET)
	public void getImage(String instanceId, HttpServletResponse response) {
		// 获取图片
		InputStream inputStream = jbpmServiceImpl.getImage(instanceId);
		byte[] b = new byte[256];
		int len = -1;
		try {
			while ((len = inputStream.read(b)) > 0) {
				response.getOutputStream().write(b, 0, len);
			}
			inputStream.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ==================================流程运转(包含业务数据)===============================//

	@RequestMapping(params = "method=draft", method = RequestMethod.POST)
	public String draft(String taskId, Contract contract, ModelMap model) {
		// TODO:考虑放到一个事务中
		contractService.draft(contract);
		jbpmServiceImpl.complete(taskId, "to submit");
		return "success";
	}

	@RequestMapping(params = "method=submit", method = RequestMethod.POST)
	public String submit(String taskId, Contract contract, ModelMap model) {
		contractService.submit(contract);
		jbpmServiceImpl.complete(taskId, "to check");
		return "success";
	}

	@RequestMapping(params = "method=approve", method = RequestMethod.POST)
	public String approve(String taskId, Contract contract, ModelMap model) {
		contractService.approve(contract);
		jbpmServiceImpl.complete(taskId, "to end");
		return "success";
	}

	@RequestMapping(params = "method=reject", method = RequestMethod.POST)
	public String reject(String taskId, Contract contract, ModelMap model) {
		contractService.reject(contract);
		jbpmServiceImpl.complete(taskId, "to modify");
		return "success";
	}

	@RequestMapping(params = "method=modify", method = RequestMethod.POST)
	public String modify(String taskId, Contract contract, ModelMap model) {
		contractService.modify(contract);
		jbpmServiceImpl.complete(taskId);
		return "success";
	}

	@RequestMapping(params = "method=repeal", method = RequestMethod.POST)
	public String repeal(String taskId, Contract contract, ModelMap model) {
		contractService.repeal(contract);
		jbpmServiceImpl.complete(taskId, "to end");
		return "success";
	}

	// ===========================转向页面，利用form属性取代============================//

	@RequestMapping(params = "method=draft", method = RequestMethod.GET)
	public String draft(String taskId, ModelMap model) {
		Long cntId = Long.valueOf(jbpmServiceImpl.getProcessVariableByTaskId(taskId, "cntId").toString());
		Contract contract = contractService.get(cntId);
		model.addAttribute("contract", contract);
		model.addAttribute("taskId", taskId);
		return "jbpm/contract/draft";
	}

	@RequestMapping(params = "method=submit", method = RequestMethod.GET)
	public String submit(String taskId, Long cntId, ModelMap model) {
		Contract contract = contractService.get(cntId);
		model.addAttribute("contract", contract);
		model.addAttribute("taskId", taskId);
		return "jbpm/contract/submit";
	}

	@RequestMapping(params = "method=check", method = RequestMethod.GET)
	public String check(String taskId, Long cntId, ModelMap model) {
		Contract contract = contractService.get(cntId);
		model.addAttribute("contract", contract);
		model.addAttribute("taskId", taskId);
		return "jbpm/contract/check";
	}

	@RequestMapping(params = "method=modify", method = RequestMethod.GET)
	public String modify(String taskId, Long cntId, ModelMap model) {
		Contract contract = contractService.get(cntId);
		model.addAttribute("contract", contract);
		model.addAttribute("taskId", taskId);
		return "jbpm/contract/modify";
	}

}
