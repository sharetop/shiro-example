package cn.chinaunicom.woplus.shiro.HelloWorld;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

public class HelloWorldRealm extends AuthorizingRealm{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		String u = (String)principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if(u.equals("aaa")){
			info.addRole("administrator");
			info.addStringPermission("system:admin");
		}
		else {
			info.addRole("sales");
			info.addStringPermission("system:view");
		}
		
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String uname = token.getUsername();
		String upassword = String.valueOf(token.getPassword());
		
		//连到真正的数据源,鉴权
		if(uname.equals("aaa") && upassword.equals("111")){
			return new SimpleAuthenticationInfo("aaa","111",getName());
		}
		else if(uname.equals("bbb")&& upassword.equals("222")){
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
			SimplePrincipalCollection principals = new SimplePrincipalCollection();
			principals.add("bbb", getName());
			principals.add("bbb@helloworld", getName());
			info.setPrincipals(principals);
			info.setCredentials(upassword);
			
			return info;
		}
		else
			throw new AuthenticationException();
	}

}
