package com.org.makgol.admin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.makgol.admin.Service.AdminService;
import com.org.makgol.users.vo.UserVo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@GetMapping("/userList")
	public String userList(Model model){
		String nextPage = "admin/userList";
		List<UserVo> userVos = adminService.getUserList();
		if(userVos != null) {
			model.addAttribute("userVos", userVos);
		}
		return nextPage;
	}
	
	
	
}
