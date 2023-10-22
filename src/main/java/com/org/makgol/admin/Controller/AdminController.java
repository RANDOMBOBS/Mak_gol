package com.org.makgol.admin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.makgol.admin.Service.AdminService;
import com.org.makgol.users.vo.UserVo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	/**
	 * User리스트 전체 SELECT
	 * @param model (다음 화면으로 값(userVos : 모든 유저의 정보)을 전달
	 * @return userList.jsp
	 */
	@GetMapping("/userList")
	public String userList(Model model){
		String nextPage = "admin/userList";
		List<UserVo> userVos = adminService.getUserList();
		if(userVos != null) {
			model.addAttribute("userVos", userVos);
		}
		return nextPage;
	}
	
	
	/**
	 * user 등급 수정 UPDATE
	 * @param userVo : 현재 유저 id값과 grade값
	 * @return userList.jsp
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyGrade", method = { RequestMethod.GET, RequestMethod.POST })
		public int modifyGrade(@RequestBody UserVo userVo) {
		System.out.println("컨트롤러+ "+ userVo);
		int result = adminService.modGrade(userVo);
		System.out.println("결과는?" + result);
		return result;
	}
	
}
