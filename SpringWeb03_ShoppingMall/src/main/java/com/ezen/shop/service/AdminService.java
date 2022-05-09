package com.ezen.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dao.AdminDao;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;
import com.ezen.shop.dto.Paging;
import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.dto.QnaVO;

@Service
public class AdminService {

	@Autowired
	AdminDao adao;

	public int workerCheck(String workId, String workPwd) {
		return adao.workCheck(workId, workPwd);
	}

	public List<ProductVO> listProduct(Paging paging, String key) {
		return adao.listProduct(paging, key);
	}

	public int getAllCount(String tableName, String fieldName, String key) {
		return adao.getAllCount(tableName, fieldName, key);
	}

	public void insertProduct(ProductVO pvo) {
		adao.insertProduct(pvo);
	}

	public void updateProduct(ProductVO pvo) {
		adao.updateProduct(pvo);

	}

	public List<OrderVO> listOrderAll(Paging paging, String key) {
		return adao.listOrderAll(paging, key);
	}

	public void resultChange(int odseq) {
		adao.resultChange(odseq);
	}

	public List<MemberVO> listMember(Paging paging, String key) {
		return adao.listMember(paging, key);
	}

	public List<QnaVO> listQna(Paging paging, String key) {
		return adao.listQna(paging,key);
	}

	public void updateQna(QnaVO qvo) {
		adao.updateQna(qvo);
	}
	
}
