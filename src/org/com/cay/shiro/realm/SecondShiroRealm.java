 package org.com.cay.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class SecondShiroRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		System.out.println("[SecondShiroRealm] doGetAuthenticationInfo...");
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
			credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
		}else if("user".equals(username)){
			credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
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
		String hashAlgorithmName = "sha1";
		String credentials = "123456";
		Object salt = ByteSource.Util.bytes("admin");
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}

}
