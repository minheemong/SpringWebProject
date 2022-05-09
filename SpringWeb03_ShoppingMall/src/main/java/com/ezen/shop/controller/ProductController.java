package com.ezen.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService ps;
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String index(Model model) {
		
		//List<ProductVO> nlist = ps.getNewList();
				//List<ProductVO> blist = ps.getBestList();
				// List는 ArrayList의 부모객체입니다
				// 우리가 이 프로젝트에서 사용하려고 하는 템플릿(데이터베이스 객체)가 List만 지원할 수 있어서 List를 사용합니다
				//model.addAttribute("newProductList", nlist);
				//model.addAttribute("bestProductList", blist);

				// 위 내용을 간략하게 작성하기
				model.addAttribute("newProductList", ps.getNewList());
				model.addAttribute("bestProductList", ps.getBestList());
				
		return "index";
	}
	
	@RequestMapping("category")
	public ModelAndView category(/* Model model, */@RequestParam("kind") String kind
			/* , HttpServletRequest request */) {
		// ModelAndView : model에 addAttribute로 저장할 내용과 이동할 jsp 파일의 이름을
		// 동시에 저장하고 리턴하여 전달값과 이동페이지를 한 번에 다룰 수 있게하는 클래스입니다.
		ModelAndView mav = new ModelAndView(); // 객체 생성
		
		/* String kind = request.getParameter("kind"); */
		
		// 매개변수 위치에 있는 @RequestParam("kind") String kind는
		// 변수 선언과 동시에 전달되는 파라미터를 받아서 저장하는 역할을 합니다.
		// HttpServletRequest request와 String kink = request.getParameter("kind")는
		// 생략이 가능합니다.
		// 전달된 파라미터가 10개면 매개변수가 @RequestParam("") 형태로 10개 선언되어 사용됩니다.
		// int 형 변수도 선언이 가능합니다.
		//List<ProductVO> list = ps.getKindList(kind);
		// model.addAttribute("productKindList", list);
		// return "product/productKind";
		
		mav.addObject("productKindList", ps.getKindList(kind));
		mav.setViewName("product/productKind");
		
		return mav;
	}
	
	@RequestMapping("productDetail")
	public ModelAndView productDetail(@RequestParam("pseq") int pseq) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("productVO", ps.getProduct(pseq));
		mav.setViewName("product/productDetail");
		
		return mav;
		
	}
}
