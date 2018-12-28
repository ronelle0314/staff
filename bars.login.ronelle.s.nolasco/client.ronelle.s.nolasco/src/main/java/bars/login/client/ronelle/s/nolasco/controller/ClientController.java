package bars.login.client.ronelle.s.nolasco.controller;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bars.login.client.ronelle.s.nolasco.ClientProxy;
import bars.login.server.ronelle.s.nolasco.DAO.User;

@Controller
@RequestMapping("/")
public class ClientController {
	
	@Autowired
	private ClientProxy clientProxy;
	
	@RequestMapping("/login")
	public String index() {
		
		return "login";
	}
	@PostMapping("/insertPage")
	public ModelAndView insertPage(HttpSession session) {
		ModelAndView model = new ModelAndView();
			model.addObject("currentUser", session.getAttribute("userName"));
			model.setViewName("insertPage");
			return model;
	
	
	}
	
	@GetMapping("/all")
	public List<User> allUser(){
		
		return clientProxy.getAll();
	}
	@PostMapping("/adminSuccess")
	public ModelAndView admin(HttpSession session) {
		ModelAndView model = new ModelAndView();
		List<User> listOfUser = clientProxy.getAll();
			model.addObject("currentUser", session.getAttribute("userName"));
			model.addObject("userList", listOfUser);
			model.setViewName("adminSuccess");
			return model;
	
	
	}	
	@PostMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView model = new ModelAndView();
			session.invalidate();
			model.setViewName("login");
			return model;
	
	
	}
	
	@PostMapping("/loginMember")
	public ModelAndView getUserPassword(HttpServletRequest request, HttpSession session) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
		
		
		ModelAndView model = new ModelAndView();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		session.setAttribute("username", userName);
		try {
			if(clientProxy.getUserByNameAndPass(userName, password).size() > 0) {
				
				List<User> user = clientProxy.getUserByNameAndPass(userName, password);
				if(encoder.matches(password, user.get(0).getPassword())) { 
					
					if(user.get(0).getUserType().equals("client")) {
						session.setAttribute("userName", userName);
						List<User> listOfUser = clientProxy.getAll();
						
						model.addObject("userList", listOfUser);
						model.setViewName("success");
						return model;
					}else if(user.get(0).getUserType().equals("admin")) {
					
						List<User> listOfUser = clientProxy.getAll();
						session.setAttribute("userName", userName);
						model.addObject("currentUser", session.getAttribute("userName"));
						model.addObject("userList", listOfUser);
						model.setViewName("adminSuccess");
						return model;
					}else{
						model.addObject("message", "Username or Password Incorrect");
						model.setViewName("login");
						return model;
					}
					
				}else {
					model.addObject("message", "Username or Password Incorrect");
					model.setViewName("login");
					return model;
				}
			}else{
				model.addObject("message", "Username or Password Incorrect");
				model.setViewName("login");
				return model;
			}
		}catch(Exception ex) {
			
			model.addObject("message", "Username or Password Incorrect");
			model.setViewName("login");
			return model;
		}
		
		
	}
	
	@PostMapping("/insertMember")
	public ModelAndView insertUserPassword(HttpServletRequest request, HttpSession session) {
		
	
		
		ModelAndView model = new ModelAndView();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		
	
		User user = clientProxy.geteUsername(userName);
		List<User> role = clientProxy.getAll();
		
		if(userType.equals("admin")) {
			for(int i = 0; i < role.size(); i++) {
				
				if(role.get(i).getUserType().equals("admin")) {
					model.addObject("message", "Only One Administrator is allowed in the system");
					model.setViewName("insertPage");
					return model;
				}
			}
		}
		
	
		
		if(user == null) {
			
			clientProxy.saveUserByNameAndPass(userName, password, userType);
			List<User> listOfUser = clientProxy.getAll();
			User insertedUser = clientProxy.geteUsername(userName);
			model.addObject("currentUser", session.getAttribute("userName"));
			model.addObject("insertedUser", insertedUser.getUserName() + " Sucessfully Inserted to the list of Users");
			model.addObject("userList", listOfUser);
			model.setViewName("adminSuccess");
			return model;
		}else {
			model.addObject("message", "Username Already exist");
			model.setViewName("insertPage");
			return model;
		}
		
		
	}
		@PostMapping("/deleteUser")
		public ModelAndView deleteUser(HttpServletRequest request, HttpSession session) {
			
			ModelAndView model = new ModelAndView();
			String userName = request.getParameter("userName");
			
			
			if( clientProxy.geteUsername(userName) != null) {
				
				clientProxy.deleteUserByName(userName);
				List<User> listOfUser = clientProxy.getAll();
				model.addObject("currentUser", session.getAttribute("userName"));
				model.addObject("userList", listOfUser);
				model.setViewName("adminSuccess");
				return model;
			}else {
				model.setViewName("fail");
				return model;
			}
			
		}
	
		@PostMapping("/updateUser")
		public ModelAndView updatetUserPassword(HttpServletRequest request, HttpSession session) {
			
			ModelAndView model = new ModelAndView();
			
			String username = request.getParameter("userName");
			String password = request.getParameter("password");
			
			if(clientProxy.geteUsername(username) != null) {
				
				User user = clientProxy.geteUsername(username);
				
				clientProxy.updateUserByName(user.getUserName(),password);
				
				List<User> listOfUser = clientProxy.getAll();

				User insertedUser = clientProxy.geteUsername(username);
				model.addObject("currentUser", session.getAttribute("userName"));
				model.addObject("insertedUser", insertedUser.getUserName() + "'s password was updated");
				model.addObject("userList", listOfUser);
				model.setViewName("adminSuccess");
				return model;
			}else {
				model.setViewName("fail");
				return model;
			}
		}
		
		
		@PostMapping("/updatePage")
		public ModelAndView updatePage(HttpServletRequest request, HttpSession session) {
			ModelAndView model = new ModelAndView();

				String userName = request.getParameter("userName");
				model.addObject("currentUser", session.getAttribute("userName"));
				model.addObject("userName", userName);
				model.setViewName("updatePage");
				return model;
		
			
		
		}
		 
		   
}
