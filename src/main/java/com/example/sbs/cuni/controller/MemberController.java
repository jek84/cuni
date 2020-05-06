package com.example.sbs.cuni.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sbs.cuni.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("member/login")
	public String showLogin(Model model) {
		return "member/login";
	}
	
	@RequestMapping("member/join")
	public String showJoin(Model model) {
		return "member/join";
	}
	
	@RequestMapping("member/doJoin")
	public String doJoin(Model model, @RequestParam Map<String, Object> param) {
		Map<String, Object> rs = memberService.join(param);
		
		String resultCode = (String) rs.get("resultCode");
		
		String msg = (String) rs.get("msg");
		
		model.addAttribute("msg", msg);
		
		if (resultCode.startsWith("S-")) {
			String redirectUrl = "/member/login";
			model.addAttribute("locationReplace", redirectUrl);
		} else {
			model.addAttribute("historyBack", true);
		}
		
		return "common/redirct";
	}
}
