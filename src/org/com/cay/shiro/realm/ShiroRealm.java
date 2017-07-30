 package org.com.cay.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		System.out.println("[ShiroRealm] doGetAuthenticationInfo...");
		//1����AuthenticationTokenת��ΪUsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//2����UsernamePasswordToken��ȡusername
		String username = upToken.getUsername();
		
		//3���������ݿ�ķ����������ݿ��в�ѯusername��Ӧ���û���¼
		System.out.println("�����ݿ��л�ȡusername: " + username + " ����Ӧ���û���Ϣ.");
		
		//4�����û������ڣ�������׳�UnknownAccountException�쳣
		if("unknown".equals(username)){
			throw new UnknownAccountException("�û�������!");
		}
		
		//5�������û�����Ϣ����������Ƿ���Ҫ�׳�������AuthenticationException�쳣
		if("monster".equals(username)){
			throw new LockedAccountException("�û�������!");
		}
		
		//6�������û��������������AuthenticationInfo���󲢷���
		//����������Ϣ�Ǵ����ݿ��л�ȡ��
		//1). principal: ��֤��ʵ����Ϣ��������username,Ҳ���������ݱ��Ӧ���û���ʵ�������
		Object principal = username;
		//2). credentials: �����ݿ��л�ȡ������
		Object credentials = null;
		if("admin".equals(username)){
			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
		}else if("user".equals(username)){
			credentials = "098d2c478e9c11555ce2823231e02ec1";
		}
		//3). realmName: ��ǰrealm�����name�����ø����getName()��������
		String realmName = getName();

		//����ֵ����
		//AuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
		
		//4). ��ֵ����
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		AuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		
		return info;
	}

	public static void main(String[] args) {
		String hashAlgorithmName = "md5";
		String credentials = "123456";
		Object salt = ByteSource.Util.bytes("Cay");
		int hashIterations = 1000;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		//1�� ��PrincipalCollection�����л�ȡ��¼�û�����Ϣ
		Object principal = principals.getPrimaryPrincipal();
		
		//2�� ���õ�¼���û���Ϣ����ȡ��ǰ�û��Ľ�ɫ��Ȩ��(������Ҫ�������ݿ�)
		Set<String> roles = new HashSet<>();
		roles.add("user");
		if("admin".equals(principal)){
			roles.add("admin");
		}
		
		//3�� ����AuthorizationInfo���󣬲�������roles����
		AuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		
		//4������SimpleAuthenticationInfo����
		return info;
	}

}
