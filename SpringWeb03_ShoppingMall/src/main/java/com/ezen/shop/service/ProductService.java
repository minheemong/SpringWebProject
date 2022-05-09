package com.ezen.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dao.ProductDao;
import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.dto.QnaVO;

@Service
public class ProductService {

	@Autowired
	ProductDao pdao;

	public List<ProductVO> getNewList() {
		return pdao.getNewList();
	}

	public List<ProductVO> getBestList() {
		return pdao.getBestList();
	}

	public List<ProductVO> getKindList(String kind) {
		return pdao.getKindList(kind);
	}

	public ProductVO getProduct(int pseq) {
		return pdao.getProduct(pseq);
	}

}
