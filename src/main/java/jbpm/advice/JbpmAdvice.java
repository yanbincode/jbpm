package jbpm.advice;

import jbpm.service.JbpmServiceImpl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class JbpmAdvice {

	@Autowired
	private JbpmServiceImpl jbpmService;

	@Pointcut("execution(* service.*.jbpm*(..))")
	public void anyMethod() {

	}

	/**
	 * 多流程，需要id和流程的名字
	 * 
	 * @param taskId
	 * @param toName
	 */
	@AfterReturning(value = "anyMethod()", returning = "result")
	public void complete(JoinPoint jp, String result) {
		System.out.println("==========进入after advice=========== \n");
		System.out.println("切入点方法执行完了 \n");

		System.out.print(jp.getArgs()[0] + "在");
		System.out.print(jp.getTarget().getClass() + "对象上被");
		System.out.print(jp.getSignature().getName() + "方法删除了");
		System.out.print("只留下：" + result + "\n\n");
	}

}
