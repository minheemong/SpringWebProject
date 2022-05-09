package com.ezen.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;
import com.ezen.shop.service.CartService;
import com.ezen.shop.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService os;
	@Autowired
	CartService cs;
	
	@RequestMapping("/orderInsert")
	public String orderInsert(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		int oseq = 0;
		if(mvo == null) {
			return "member/login";
		} else {
			// 현재 로그인 중인 사용자의 아이디로 카트리스트 조회
			List<CartVO> cartList = cs.listCart(mvo.getId());
			
			// 카드 리스트를 주문으로 추가
			oseq = os.insertOrder(cartList, mvo.getId());
			
			// 방금 추가한 주문 번호를 리턴받아 오더리스트의 주문번호로 적용합니다
		}
		return "redirect:/orderList?oseq="+oseq;
	}
	
	@RequestMapping("/orderList")
	public ModelAndView orderList(HttpServletRequest request, 
			@RequestParam("oseq") int oseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo==null) {
			mav.setViewName("member/login");
		} else {
			List<OrderVO> list = os.listOrderByOseq(oseq);
			int totalPrice=0;
			for(OrderVO ovo : list)
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
				mav.addObject("orderList", list);
				mav.addObject("totalPrice", totalPrice);
				mav.setViewName("mypage/orderList");
		}
		return mav;
	}
	
	@RequestMapping("myPage")
	public ModelAndView myPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo==null) mav.setViewName("member/login");
		else {
			
			//mypage.jsp 전달될 리스트
			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
			// 1. 현재 로그인 유저의 아이디로 미처리된 주문의 주문번호들(order_view 테이블에서 조회)을
			// 		리스트로 받습니다(중복제거)
			List<Integer> oseqList = os.selectSeqOrderIng(mvo.getId());
			for(int oseq: oseqList) {
				// 2. 리턴 받은 리스트의 주문번호를 하나씩 꺼내서(반복실행문 사용),
				//		해당 주문번호의 주문내역을 리스트로 받습니다
				List<OrderVO> orderListIng = os.listOrderByOseq(oseq);
				// 3. 리턴받은 주문상세내역 중 맨 첫번째 상품의 이름을 "가나다 포함 X건"으로 변경합니다
				OrderVO ovo = orderListIng.get(0);
				ovo.setPname(ovo.getPname() + " 포함 " + orderListIng.size() + " 건");
				// 4. 리스트를 이용해서 총 가격을 계산합니다
				int totalPrice = 0;
				for(OrderVO ovo1 : orderListIng) 
					totalPrice += ovo1.getPrice2() * ovo1.getQuantity();
				// 5. 제목을 변경한 주문상세내역에 금액도 현재 총 금액으로 변경합니다
				ovo.setPrice2(totalPrice);
				// 6. 현재 주문상세리스트 중 첫번째(주문이름과 총금액을 담고 있는)주문을 orderList에 담습니다
				orderList.add(ovo);
			}
			mav.addObject("title","진행 중인 주문내역");
			mav.addObject("orderList", orderList);
		}
		mav.setViewName("mypage/mypage");
		return mav;
	}
	
	@RequestMapping("orderAll")
	public ModelAndView orderAll (HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session= request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo==null) mav.setViewName("member/login");
		else {
			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
			List<Integer> oseqList = os.oseqListAll(mvo.getId()); // 주문번호들 조회
			for(int oseq : oseqList) {
				List<OrderVO> orderListAll = os.listOrderByOseq(oseq); // 주문번호로 주문상품조회
				OrderVO ovo = orderListAll.get(0); // 상품 중 첫 번째 추출
				ovo.setPname(ovo.getPname() + " 포함 " + orderListAll.size() + " 건"); // 상품명 변경
				int totalPrice = 0;
				for(OrderVO ovop : orderListAll) 
					totalPrice += ovop.getPrice2() * ovop.getQuantity();
				ovo.setPrice2(totalPrice);
				orderList.add(ovo);
			}
			mav.addObject("title","총 주문내역");
			mav.addObject("orderList", orderList);
			mav.setViewName("mypage/mypage");
		}
		return mav;
	}
	
	@RequestMapping("/orderDetail")
	public ModelAndView orderDetail(HttpServletRequest request, 
			@RequestParam("oseq") int oseq) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser==null) mav.setViewName("member/login");
		else {
			List<OrderVO> orderList = os.listOrderByOseq(oseq); // 주문번호로 주문상품들의 리스트 리턴
			int totalPrice = 0;
			for(OrderVO ovo : orderList)
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			mav.addObject("orderList", orderList);
			mav.addObject("totalPrice", totalPrice);
			mav.setViewName("mypage/orderDetail");
			
			mav.addObject("orderDetail", orderList.get(0));
		}
		
		return mav;
	}
}
