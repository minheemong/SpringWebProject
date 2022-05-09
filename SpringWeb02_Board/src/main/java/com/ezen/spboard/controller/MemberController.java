package com.ezen.spboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.spboard.dto.SpMember;
import com.ezen.spboard.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService ms;
	
	
	// @RequestMapping(value="/", method = RequestMethod.GET)
	@RequestMapping("/")
	public String firstRequest( Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		if( session.getAttribute("loginUser") == null)	
			return "loginform";
		else 
			return "redirect:/main";
	}
	// "redirect:/리퀘스트이름   -> 리퀘스트이름에 해당하는 매핑으로 이동  
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login( Model model, HttpServletRequest request) {
		
		String url = "loginform";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		SpMember sdto = ms.getMember(id);
		
		if( sdto == null) {
			model.addAttribute("message", "id를 확인하세요");
		} else if( sdto.getPw() == null ) {
			model.addAttribute("message", "비번이 null 입니다. 관리자에 문의 하세요");
		} else if( !sdto.getPw().equals(pw) ) {
			model.addAttribute("message", "비밀번호가 맞지 않습니다");
		} else if( sdto.getPw().equals(pw) ){
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", sdto);
			url = "redirect:/main";
		} else {
			model.addAttribute("message", "알수없는 이유로 로그인하지 못했습니다");
		}
		return url;
	}
	
	
	
	@RequestMapping("/memberJoinForm")
	public String memberJoinForm(Model model, HttpServletRequest request) {
		return "member/memberJoinForm";
	}
	
	
	
	@RequestMapping("idcheck")
	public String idcheck( Model model, HttpServletRequest request) {
		String id=request.getParameter("id");
		int result = ms.confirmID(id);
		model.addAttribute("result", result);
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	
	
	@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public String memberJoin(Model model , HttpServletRequest request) {
		SpMember sm = new SpMember();
		sm.setId( request.getParameter("id") );
		sm.setPw(request.getParameter("pw"));
		sm.setName(request.getParameter("name"));
		sm.setPhone1(request.getParameter("phone1"));
		sm.setPhone2(request.getParameter("phone2"));
		sm.setPhone3(request.getParameter("phone3"));
		sm.setEmail(request.getParameter("email"));
		
		int result = ms.insertMember(sm);
		
		// 회원가입 결과 성공여부에 따라서  message 이름으로 "회원가입 성공. 로그인하세요", 
		// "회원가입 실패. 다음에 다시 시도하세요" 를 Model 에 담아주세요
		if( result == 1) model.addAttribute("message","회원가입 성공. 로그인하세요");
		else model.addAttribute("message","회원가입 실패. 다음에 다시 시도하세요");
		return "loginform";
	}
	
	
	@RequestMapping("/logout")
	public String logout(Model model , HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		model.addAttribute("message", "로그아웃되었습니다");
		return "loginform";
		// return "redirect:/"; -> model이 전달되지 않음. model이 request보다 수명이 짧다
	}
	
	
	@RequestMapping("/memberEditForm")
	public String memberEditForm(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null) 
			return "loginform";
		
		return "member/memberEditForm";
	}
	
	
	
	
	@RequestMapping(value="/memberEdit", method=RequestMethod.POST)
	public String memberEdit(Model model, HttpServletRequest request) {
		
		SpMember sm = new SpMember();
		sm.setId( request.getParameter("id") );
		sm.setPw(request.getParameter("pw"));
		sm.setName(request.getParameter("name"));
		sm.setPhone1(request.getParameter("phone1"));
		sm.setPhone2(request.getParameter("phone2"));
		sm.setPhone3(request.getParameter("phone3"));
		sm.setEmail(request.getParameter("email"));
		
		int result = ms.updateMember(sm);
		
		// 세션에 새로운 로그인정보를 다시 저장
		HttpSession session = request.getSession();
		if(result==1) session.setAttribute("loginUser", sm);
		
		return "redirect:/main";
	}
	
}

















