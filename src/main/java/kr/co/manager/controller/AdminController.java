package kr.co.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.manager.domain.jpa.AccountBcrypt;
import kr.co.manager.domain.jpa.Factory;
import kr.co.manager.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminService adminService;

	// 어드민 -> 사용자 관리 페이지 호출
	@RequestMapping(value = "/getUsersView", method = RequestMethod.GET)
	public String getUsersView(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		List<Factory> factoryList = adminService.getFactoryList(session);
        model.addAttribute("factorys", factoryList);
		return "falcon/admin-users";
	}
	
	// 어드민 -> 사용자 관리 페이지 호출 (tabulator 적용 페이지)
	@RequestMapping(value = "/getUsersView2", method = RequestMethod.GET)
	public String getUsersView2(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		List<Factory> factoryList = adminService.getFactoryList(session);
        model.addAttribute("factorys", factoryList);
		return "falcon/admin-users-tabulator";
	}
	
	// 어드민 - 사용자 관리 - 사용자 정보 수정
    @RequestMapping("/editMember")
    String editMember(@ModelAttribute AccountBcrypt account, @RequestParam(value = "approvalStatus") String approvalStatus){
        account.setAppovalStatus(approvalStatus);
        adminService.editMember(account);
        return "redirect:/admin/getUsersView?type=edit";
    }
    
    // 어드민 - 사용자 관리 - 사용자 등록
    @RequestMapping("/addMember")
    String addMember(AccountBcrypt account, @RequestParam(value = "approvalStatus") String approvalStatus){
        account.setAppovalStatus(approvalStatus);
        adminService.addMember(account);
        return "redirect:/admin/getUsersView?type=add";
    }
}
