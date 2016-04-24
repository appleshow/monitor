package com.aps.monitor.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aps.monitor.comm.MD5Util;
import com.aps.monitor.comm.StringUtil;
import com.aps.monitor.comm.CommUtil;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.service.ILoginViewService;
import com.aps.monitor.service.IPreLoginHandler;

/**
 * 
 * @ClassName: LoginController
 * @Description:用户登入页面
 * @author: AppleShow
 * @date: 2016年3月15日 下午8:01:10
 * 
 * @since 1.0.0
 */
@Controller
public class LoginController extends BaseController {
	private Logger logger = LogManager.getLogger(LoginController.class.getName());

	@Resource(name = "loginViewServiceImpl")
	private ILoginViewService loginViewService;
	@Resource(name = "perLoginHandlerVerification")
	IPreLoginHandler preLoginHandler;

	/**
	 * 请求根目录
	 * 
	 * @Title: indexPage
	 * @Description:
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexPage() {
		return "index.html";
	}

	/**
	 * 请求登入验证码
	 * 
	 * @Title: perVerificationImg
	 * @Description: 请求登入验证码
	 * @param: @param session
	 * @param: @return
	 * @param: @throws Exception
	 * @return: Map<?,?>
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/perVerificationImg", method = RequestMethod.POST)
	@ResponseBody
	public Map<?, ?> perVerificationImg(HttpSession session) throws Exception {
		return preLoginHandler.handle(session);
	}

	/**
	 * 提交登入
	 * 
	 * @Title: login
	 * @Description:
	 * @param: @param request
	 * @param: @param response
	 * @param: @param userName
	 * @param: @param password
	 * @param: @param map
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String userName,
			@RequestParam("password") String password, @RequestParam("verification") String verification, ModelMap map) {
		HttpSession httpSession = request.getSession();
		ComPerson person;
		String userPassword, userVerification;

		userVerification = (String) httpSession.getAttribute(CommUtil.SESSION_VERIFICATION);
		if (!verification.equals(userVerification)) {
			return "index1.html";
		}
		person = loginViewService.selectByUserName(userName);

		if (null == person) {
			return "index2.html";
		}
		userPassword = StringUtil.desDecryptStr(person.getUserPsw(), CommUtil.LOCK_WORD);
		userPassword = MD5Util.getMD5String(MD5Util.getMD5String(userPassword) + userVerification);

		if (userPassword.equals(password)) {
			httpSession.setAttribute(CommUtil.SESSION_PERSON_ID, person.getPersonId());
			httpSession.setAttribute(CommUtil.SESSION_USER_ID, person.getUserId());
			httpSession.setAttribute(CommUtil.SESSION_USER_NAME, person.getUserName());

			CommUtil.updatePermissoned(httpSession, loginViewService.selectPersonPermissions(person.getPersonId()));

			return "main.html";
		} else {
			return "index3.html";
		}
	}
}
