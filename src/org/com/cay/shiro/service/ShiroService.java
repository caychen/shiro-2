package org.com.cay.shiro.service;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

@Service
public class ShiroService {

	//@RequiresRoles({"admin"})
	public void testMethod(){
		System.out.println("testMethod's time: " + new Date());
	}

	public void testSession() {
		// TODO Auto-generated method stub
		Session session = SecurityUtils.getSubject().getSession();
		
		Object val = session.getAttribute("key");
		System.out.println("ShiroService's session['key'] = " + val);
	}
}
