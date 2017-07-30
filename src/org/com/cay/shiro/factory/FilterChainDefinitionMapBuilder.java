package org.com.cay.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	//可以从数据库中获取权限的数据
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		
//		/login.jsp = anon
//      /shiro/login = anon
//      /shiro/logout = logout
//        
//      /user.jsp = roles[user]
//      /admin.jsp = roles[admin]
//        
//      # everything else requires authentication:
//      /** = authc
		
		System.out.println("从数据库获取权限数据...");
		map.put("/login.jsp", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");
		map.put("/user.jsp", "authc, roles[user]");
		map.put("/admin.jsp", "authc, roles[admin]");
		map.put("/list.jsp", "user");//表示list.jsp页面，只要RememberMe为true，也可访问。
		map.put("/**", "authc");
		
		return map;
	}
}
