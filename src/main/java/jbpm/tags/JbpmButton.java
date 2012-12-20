package jbpm.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import jbpm.service.JbpmServiceImpl;

import org.springframework.web.servlet.tags.form.AbstractHtmlInputElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * jbpm自定义标签。嵌入jsp页面，作用按钮<br>
 * 继承spring封装AbstractHtmlInputElementTag类的自定义标签。重写writeTagContent()方法
 * 
 * @author yanbin
 * 
 */
public class JbpmButton extends AbstractHtmlInputElementTag {

	private static final long serialVersionUID = -8229075665342686774L;

	private String taskId = "";
	private String toName = "";
	private Long businessId = 0l;

	@Override
	protected int writeTagContent(TagWriter arg0) throws JspException {
		try {
			StringBuffer htmlContent = new StringBuffer();
			htmlContent.append("<input type=\"submit\" value=\"");
			htmlContent.append(getButtonName(taskId));
			htmlContent.append("\" />");
			pageContext.getOut().write(htmlContent.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	private String getButtonName(String taskId) {
		JbpmServiceImpl jbpmService = getJbpmService();
		String value = jbpmService.getTaskById(taskId).getName();
		return value;
	}

	private JbpmServiceImpl getJbpmService() {
		return TagHelper.getJbpmService(pageContext);
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

}
