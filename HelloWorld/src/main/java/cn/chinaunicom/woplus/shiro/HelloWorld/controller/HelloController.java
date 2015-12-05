package cn.chinaunicom.woplus.shiro.HelloWorld.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {

	
	@RequestMapping(value="/home"
		,method=RequestMethod.GET
		,headers = {"Accept=text/html"})
	public ModelAndView showHello(){
		ModelAndView mv = new ModelAndView();
		
		String cnt = RandomUtils.nextLong()+"";
		
		mv.addObject("message", cnt);
		
		mv.setViewName("home");
		return mv;
	}
	
	//@RequiresRoles("administrator")
	@RequiresPermissions("system:admin")
	@RequestMapping(value="/admin"
			,method=RequestMethod.GET
			,headers = {"Accept=text/html"})
		public ModelAndView showAdmin(){
			ModelAndView mv = new ModelAndView();
			
			String cnt = RandomUtils.nextLong()+"";
			mv.addObject("message", cnt);
			
			mv.setViewName("admin");
			return mv;
		}
	
	@RequestMapping(value="/login"
		,method=RequestMethod.GET
		,headers = {"Accept=text/html"})
	public ModelAndView showLogin(){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping(value="/logout"
			,method=RequestMethod.GET
			,headers = {"Accept=text/html"})
		public ModelAndView showLogout(){
			ModelAndView mv = new ModelAndView();
			
			SecurityUtils.getSubject().logout();
			
			mv.setViewName("login");
			return mv;
		}
	
	@RequestMapping(value="/login"
			,method=RequestMethod.POST
			,headers = {"Accept=text/html"})
	public ModelAndView postLogin(@RequestParam("login_name")String login_name,@RequestParam("login_password")String login_password){
		
		ModelAndView mv = new ModelAndView();
		
		try{
			
			Subject subject = SecurityUtils.getSubject();
			
			UsernamePasswordToken token = new UsernamePasswordToken(login_name,login_password);
			
			subject.login(token);
			
			mv.setViewName("redirect:/home");
		}
		catch(Exception ex){
			ex.printStackTrace();
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("login");
		}
		
		return mv;
		
	}
}
