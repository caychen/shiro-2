package org.com.cay.shiro.handler;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.com.cay.shiro.service.ShiroService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shiro")
public class ShiroHandler {

	@Resource
	private ShiroService shiroService;
	
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		Subject currentUser = SecurityUtils.getSubject();

		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (AuthenticationException ae) {
				// ������֤ʱ�쳣�ĸ���
				// unexpected condition? error?
				System.out.println("��½ʧ��: " + ae.getMessage());
			}
		}

		return "redirect:/list.jsp";
	}
	
	@RequiresRoles(value={"admin"})
	@RequestMapping("/testShiroAnnotation")
	public String testShiroAnnotation(){
		shiroService.testMethod();
		return "redirect:/list.jsp";
	}
	
	@RequestMapping("/testSession")
	public String testSession(HttpSession session){
		session.setAttribute("key", "value");
		shiroService.testSession();
		return "redirect:/list.jsp";
	}
}
