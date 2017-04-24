/**
 * 
 */
package com.unionpay.opdept.mchntaudit.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.service.AuditUserService;

/**
 * @author DATAN
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	@Resource
	private AuditUserService auditUserService;

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestParam("loginId") String loginId, @RequestParam("pwd") String pwd,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		AuditUser user = auditUserService.loginAuth(loginId, pwd);
		if (null == user) {
			return null;
		}
		// 用户信息缓存
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		result.put("user", user);
		return result;
	}

	@RequestMapping(value = "modPwd.do", method = RequestMethod.POST)
	@ResponseBody
	public int modifyPwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd,
			HttpServletRequest request) {
		AuditUser user = (AuditUser) request.getSession().getAttribute("user");
		int result = -1;
		if (null != user) {
			result = auditUserService.modifyPwd(user, oldPwd, newPwd);
		}
		return result;
	}

	@RequestMapping(value = "logout.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logout(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		result.put("result", "1");
		return result;
	}

	@RequestMapping(value = "getUser.do", method = RequestMethod.POST)
	@ResponseBody
	public AuditUser getUserBySession(HttpServletRequest request) {
		AuditUser user = (AuditUser) request.getSession().getAttribute("user");
		if (null != user) {
			return user;
		}
		return null;
	}
	@RequestMapping(value = "getAll.do",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAll(HttpServletRequest request) {
		List<AuditUser> auditUser =auditUserService.selectAll();
		Map<String, Object> resultMap =new HashMap<String,Object>();
		resultMap.put("result", auditUser);
		
		return resultMap;
		
	}
	@RequestMapping(value = "modfla.do",method = RequestMethod.POST)
	@ResponseBody
	public int modFla(@RequestParam("userId") Integer usr_id,@RequestParam("flaBa") String fla,HttpServletRequest request){
		int result = auditUserService.updateFla(usr_id, fla );
		
		return result;
		
	}
	/*@RequestMapping(value = "addUser.do",method = RequestMethod.POST)
	@ResponseBody
	public int addUser(@RequestParam("") ,HttpServletRequest request){
		
		return 0;
		
	}*/
	@RequestMapping(value = "getAlla.do",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAlla(HttpServletRequest request) {
		List<AuditUser> auditUser =auditUserService.selectAlla();
		Map<String, Object> resultMap =new HashMap<String,Object>();
		resultMap.put("result", auditUser);
		
		return resultMap;
		
	}
	
}
