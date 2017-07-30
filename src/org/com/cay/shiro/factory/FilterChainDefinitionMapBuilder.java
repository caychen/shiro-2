package org.com.cay.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	//���Դ����ݿ��л�ȡȨ�޵�����
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
		
		System.out.println("�����ݿ��ȡȨ������...");
		map.put("/login.jsp", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");
		map.put("/user.jsp", "authc, roles[user]");
		map.put("/admin.jsp", "authc, roles[admin]");
		map.put("/list.jsp", "user");//��ʾlist.jspҳ�棬ֻҪRememberMeΪtrue��Ҳ�ɷ��ʡ�
		map.put("/**", "authc");
		
		return map;
	}
}
