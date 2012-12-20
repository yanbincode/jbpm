package jbpm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class JbpmServiceImpl {

	@Autowired
	@Qualifier(value = "processEngine")
	private ProcessEngine processEngine;

	/**
	 * 部署一个指定的流程
	 * 
	 * @param processFile
	 *            流程文件
	 * @return
	 */
	public String deployProcess(File processFile) {
		String deployId = "";
		try {
			FileInputStream fis = new FileInputStream(processFile);
			ZipInputStream zip = new ZipInputStream(fis);
			// jbpm api 发起流程
			RepositoryService repositoryService = processEngine.getRepositoryService();
			NewDeployment newDeployment = repositoryService.createDeployment().addResourcesFromZipInputStream(zip);
			deployId = newDeployment.deploy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deployId;
	}

	/**
	 * 开始一个流程
	 * 
	 * @param instanceName
	 *            流程名
	 * @return
	 */
	public String startProcessByName(String processName) {
		ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey(processName);
		String instanceId = processInstance.getId();
		return instanceId;
	}

	/**
	 * 开始一个流程
	 * 
	 * @param processId
	 *            流程id
	 * @return
	 */
	public String startProcessById(String processId) {
		ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceById(processId);
		String instanceId = processInstance.getId();
		return instanceId;
	}

	/**
	 * 开始一个流程，并且存放变量
	 * 
	 * @param processId
	 * @param variables
	 * @return
	 */
	public String startProcessById(String processId, Map<String, Object> variables) {
		// 绑定实例变量，变量可以是map 也可以是string
		ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceById(processId,
				variables);
		String instanceId = processInstance.getId();
		return instanceId;
	}

	/**
	 * 利用taskId和key 获取对应的变量
	 * 
	 * @param taskId
	 * @param key
	 * @return
	 */
	public Object getProcessVariableByTaskId(String taskId, String key) {
		String instanceId = processEngine.getTaskService().getTask(taskId).getExecutionId();
		Object obj = processEngine.getExecutionService().getVariable(instanceId, key);
		return obj;
	}

	/**
	 * 删除流程
	 * 
	 * @param processId
	 */
	public void deleteProcess(String processId) {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 级联删除
		// 如果希望级联删除一个发布中流程定义的 所有流程实例， 可以使用deleteDeploymentCascade。
		repositoryService.deleteDeploymentCascade(processId);
		// 删除流程属性
		// repositoryService.deleteDeployment(deploymentId);

	}

	/**
	 * 获取所有流程定义的集合
	 * 
	 * @return
	 */
	public List<ProcessDefinition> getProcessDefinitions() {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		List<ProcessDefinition> pdList = repositoryService.createProcessDefinitionQuery().list();
		return pdList;
	}

	/**
	 * 获取图片
	 * 
	 * @param instanceId
	 * @return
	 */
	public InputStream getImage(String instanceId) {
		// 先做 获取一张图片
		ProcessInstance processInstance = processEngine.getExecutionService().findProcessInstanceById(instanceId);
		ProcessDefinitionQuery processDefinitionQuery = processEngine.getRepositoryService()
				.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId());
		ProcessDefinition processDefinition = processDefinitionQuery.uniqueResult();

		// 获取图片
		InputStream inputStream = processEngine.getRepositoryService().getResourceAsStream(
				processDefinition.getDeploymentId(), processDefinition.getImageResourceName());
		return inputStream;
	}

	/**
	 * 获取流程进展的节点
	 * 
	 * @param instanceId
	 * @return
	 */
	public Map<String, Object> getImagePoint(String instanceId) {
		ProcessInstance processInstance = processEngine.getExecutionService().findProcessInstanceById(instanceId);
		Set<String> activitySet = processInstance.findActiveActivityNames();
		ActivityCoordinates ac = processEngine.getRepositoryService().getActivityCoordinates(
				processInstance.getProcessDefinitionId(), activitySet.iterator().next());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("x", ac.getX());
		map.put("y", ac.getY());
		map.put("w", ac.getWidth());
		map.put("h", ac.getHeight());
		return map;
	}

	/**
	 * 获取所有待办事项
	 * 
	 * @return
	 */
	public List<Task> getTasks() {
		TaskService taskService = processEngine.getTaskService();
		// 获取的当前登录人的角色
		List<Task> taskList = taskService.createTaskQuery().list();
		return taskList;
	}

	/**
	 * 根据主键获取task
	 * 
	 * @param taskId
	 * @return
	 */
	public Task getTaskById(String taskId) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.getTask(taskId);
	}

	/**
	 * 单流程，只需要一个id
	 * 
	 * @param taskId
	 */
	public void complete(String taskId) {
		TaskService taskService = processEngine.getTaskService();
		// 结束任务
		taskService.completeTask(taskId);
	}

	/**
	 * 多流程，需要id和流程的名字
	 * 
	 * @param taskId
	 * @param toName
	 */
	public void complete(String taskId, String toName) {
		TaskService taskService = processEngine.getTaskService();
		taskService.completeTask(taskId, toName);
	}

}
