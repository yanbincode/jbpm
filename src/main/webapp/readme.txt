利用业务键 来发起 流程 ：保存业务关联主键：

	最佳实践

	executionService.startProcessInstanceByKey("ICL","business id")

	流程主键保存为：ICL.business id  

变量

流程等待状态：
	
		进入一个state 活动时：wait state 等待状态 task活动也会进入等待状态
		需要一个signal 外部触发信号 ： executionService.signalExecutionById(ID);
		最好实践，给state 加一个 事件监听器  94页

		