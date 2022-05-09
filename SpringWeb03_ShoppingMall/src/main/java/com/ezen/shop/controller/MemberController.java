package com.ezen.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.shop.dto.AddressVO;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService ms;
	
	@RequestMapping("loginForm")
	public String login_form(Model model, HttpServletRequest request) {
		return "member/login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberVO mvo = ms.getMember(id);
		
		if(mvo == null) {
			model.addAttribute("message","ID가 없습니다");
			return "member/login";
		} else if(mvo.getPwd() == null) {
			model.addAttribute("message","비밀번호가 없습니다");
			return "member/login";
		} else if(!mvo.getPwd().equals(pwd)) {
			model.addAttribute("message","비밀번호가 맞지 않습니다");
			return "member/login";
		} else if(mvo.getPwd().equals(pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
			return "redirect:/";
		} else {
			model.addAttribute("message", "알 수 없는 이유로 로그인 할 수 없습니다");
			return "member/login";
		}
	}	
	
	@RequestMapping("contract")
	public String contract(Model model, HttpServletRequest request) {
		return "member/contract";
	}
	
	@RequestMapping("logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	@RequestMapping(value="joinForm", method=RequestMethod.POST)
	public String join_form(Model model, HttpServletRequest request) {
		return "member/joinForm";
	}
	
	@RequestMapping("idCheckForm")
	public String id_check_form(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		int result = ms.confirmID(id);
		model.addAttribute("result",result);
		model.addAttribute("id",id);
	
		return "member/idcheck";
	}
	
	@RequestMapping("findZipNum")
	public String find_zip(Model model, HttpServletRequest request) {
		String dong = request.getParameter("dong");
	
		if(dong!=null && dong.trim().equals("")==false) {
			//List<AddressVO> addressList = ms.selectAddressByDong(dong);
			model.addAttribute("addressList", ms.selectAddressByDong(dong));
		}
		return "member/findZipNum";	
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(Model model, HttpServletRequest request) {
		MemberVO mvo = new MemberVO();
		mvo.setId(request.getParameter("id"));
		mvo.setPwd(request.getParameter("pwd"));
		mvo.setName(request.getParameter("name"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setPhone(request.getParameter("phone"));
		mvo.setZip_num(request.getParameter("zip_num"));
		mvo.setAddress(request.getParameter("addr1")+" "+request.getParameter("addr2"));
		ms.insertMember(mvo);
		return "member/login";
	}
	
	@RequestMapping("memeberEditForm")
	public String member_Edit_Form(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		String addr = mvo.getAddress(); // 주소 추출
		int k1 = addr.indexOf(" "); // 첫번째 공백의 위치 찾음
		int k2 = addr.indexOf(" ", k1+1); // 첫번째 공백 위치의 다음 위치부터 두번째 공백 위치 찾음
		int k3 = addr.indexOf(" ", k2+1); // 두번째 공백 위치의 다음 위치부터 세번째 공백 위치 찾음
		// 서울시 마포구 대현동 115-15 세번째 공백 위치 k3값 ->11 (0부터 시작)
		String addr1 = addr.substring(0, k3); // 맨 앞부터 세번째 공백 위치 바로 전까지... 주소 앞부분
		String addr2 = addr.substring(k3+1);
		model.addAttribute("member",mvo);
		model.addAttribute("addr1",addr1);
		model.addAttribute("addr2",addr2);
		return "member/memberUpdateForm";
	}
	
	@RequestMapping(value="/memberUpdate", method=RequestMethod.POST)
	public String member_Update(Model model, HttpServletRequest request) {
		MemberVO mvo = new MemberVO();
		mvo.setId(request.getParameter("id"));
		mvo.setPwd(request.getParameter("pwd"));
		mvo.setName(request.getParameter("name"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setPhone(request.getParameter("phone"));
		mvo.setZip_num(request.getParameter("zip_num"));
		mvo.setAddress(request.getParameter("addr1")+" "+request.getParameter("addr2"));
		ms.memberUpdate(mvo);
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", mvo);
		return "redirect:/";
	}
}
