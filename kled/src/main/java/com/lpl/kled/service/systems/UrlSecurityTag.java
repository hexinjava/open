package com.lpl.kled.service.systems;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lpl.kled.common.utils.SessionUtil;
import com.lpl.kled.entity.systems.User;

public class UrlSecurityTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PowerService powerService;

	private String verifyUrl;

	@Override
	public int doStartTag() throws JspException {
		if (powerService == null) {
			ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(this.pageContext.getServletContext());
			powerService = ac.getBean(PowerService.class);
		}
		if (StringUtils.isNotBlank(verifyUrl)) {
			// 如果是超级管理员则不进行检查
			User user = (User) SessionUtil.getAttr((HttpServletRequest) this.pageContext.getRequest(), "currentUser");
			if (user != null) {
				if ("admin".equals(user.getAccount())) {
					return EVAL_BODY_INCLUDE;
				} else {
					// 检查路径是否有权限访问
					if (powerService.exist(user, verifyUrl)) {
						return EVAL_BODY_INCLUDE;
					}
				}
			}
		}
		return SKIP_BODY; // 跳过主体内容
	}

	public void setVerifyUrl(String verifyUrl) {
		this.verifyUrl = verifyUrl;
	}
}
