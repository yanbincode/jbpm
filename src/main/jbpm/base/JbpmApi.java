package base;

import org.jbpm.api.Configuration;
import org.jbpm.api.Deployment;
import org.jbpm.api.DeploymentQuery;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.JobQuery;
import org.jbpm.api.ManagementService;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.ProcessInstanceQuery;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryActivityInstanceQuery;
import org.jbpm.api.history.HistoryDetail;
import org.jbpm.api.history.HistoryDetailQuery;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.history.HistoryProcessInstanceQuery;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.history.HistoryTaskQuery;
import org.jbpm.api.job.Job;
import org.jbpm.api.task.Task;

/**
 * 学习jbpm api
 * 
 * @author yanbin
 * 
 */
public class JbpmApi {

	/**
	 * 获取service api
	 */
	@SuppressWarnings("unused")
	public void api() {

		/**
		 * serviceIntance
		 */
		// 1、获取jbpm 引擎：初始化ProcessEngine会自动读取jbpm.cfg.xml
		ProcessEngine processEngine = new Configuration().buildProcessEngine();

		// 2、获取流程管理对象：对流程定义的部署，查询，删除
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RepositoryService repositoryService2 = processEngine.get(RepositoryService.class);

		// 3、流程实例管理对象：启动流程，执行，设置流程
		ExecutionService executionService = processEngine.getExecutionService();
		ExecutionService executionService2 = processEngine.get(ExecutionService.class);

		// 4、流程任务实例管理对象：对任务的创建，提交，查询，保存，删除
		TaskService taskService = processEngine.getTaskService();
		TaskService taskService2 = processEngine.get(TaskService.class);

		// 5、历史服务：完成的流程的归档，历史的查询
		HistoryService historyService = processEngine.getHistoryService();
		// 6、身份认证服务：流程用户，用户组以及组成员关系的服务
		IdentityService identityService = processEngine.getIdentityService();
		// 7、管理服务
		ManagementService managementService = processEngine.getManagementService();

		/**
		 * queryIntance
		 */
		// 1、部署查询对象
		DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
		// 2、流程定义查询对象
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		// 3、流程实例查询对象
		ProcessInstanceQuery processInstanceQuery = executionService.createProcessInstanceQuery();
		// 4、任务实例查询对象
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 5、任务历史查询对象
		HistoryTaskQuery historyTaskQuery = historyService.createHistoryTaskQuery();
		HistoryDetailQuery historyDetailQuery = historyService.createHistoryDetailQuery();
		HistoryActivityInstanceQuery historyActivityInstanceQuery = historyService.createHistoryActivityInstanceQuery();
		HistoryProcessInstanceQuery historyProcessInstanceQuery = historyService.createHistoryProcessInstanceQuery();
		// 6、管理job查询对象：异步工作
		JobQuery jobQuery = managementService.createJobQuery();

		/**
		 * entityIntance
		 */
		// 1、部署实体对象
		Deployment deployment = deploymentQuery.uniqueResult();
		// 2、 流程定义实体对象
		ProcessDefinition processDefinition = processDefinitionQuery.uniqueResult();
		// 3、执行对象
		Execution execution = executionService.findExecutionById("");
		// 4、流程实例对象
		ProcessInstance processInstance = executionService.findProcessInstanceById("");
		ProcessInstance processInstance2 = processInstanceQuery.uniqueResult();
		// 5、任务对象
		Task task = taskService.getTask("");
		Task task2 = taskQuery.uniqueResult();
		// 6、 历史
		HistoryTask historyTask = historyTaskQuery.uniqueResult();
		HistoryDetail historyDetail = historyDetailQuery.uniqueResult();
		HistoryActivityInstance historyActivityInstance = historyActivityInstanceQuery.uniqueResult();
		HistoryProcessInstance historyProcessInstance = historyProcessInstanceQuery.uniqueResult();
		// 7、管理：异步工作
		Job job = jobQuery.uniqueResult();

	}

	/**
	 * 部署API
	 * 
	 * @param repositoryService
	 */
	public void repository(RepositoryService repositoryService) {
		// 用于部署的
		NewDeployment newDeployment = repositoryService.createDeployment();
		// 部署，可以从url 路径， zip文件
		newDeployment.addResourceFromClasspath("").deploy();

		// 删除部署信息，不级联
		repositoryService.deleteDeployment("");
		// 删除部署信息，并且级联删除
		repositoryService.deleteDeploymentCascade("");
	}

	public void execution(ExecutionService executionService) {
	}

}
