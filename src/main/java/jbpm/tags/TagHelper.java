package jbpm.tags;

import java.util.Collection;

import javax.servlet.jsp.PageContext;

import jbpm.service.JbpmServiceImpl;

import org.springframework.web.context.WebApplicationContext;

/**
 * 标签帮助类，从spring容器中获取对应的实例
 * 
 * @author yanbin
 * 
 */
public class TagHelper {

	public static JbpmServiceImpl getJbpmService(PageContext pageContext) {
		WebApplicationContext context = (WebApplicationContext) pageContext.getServletContext().getAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		Collection<JbpmServiceImpl> collection = context.getBeansOfType(JbpmServiceImpl.class).values();
		if (collection.size() <= 0) {
			throw new IllegalArgumentException("there is no type of the bean");
		}
		// 默认获取第一个实例
		return collection.iterator().next();
	}

}
