package com.ezen.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dao.QnaDao;
import com.ezen.shop.dto.QnaVO;

@Service
public class QnaService {

	@Autowired
	QnaDao qdao;

	public List<QnaVO> listQna(String id) {
		return qdao.listQna(id);
	}

	public QnaVO getQna(int qseq) {
		return qdao.getQna(qseq);
	}

	public void insertQna(QnaVO qvo, String id) {
		qdao.insertQna(qvo, id);
	}
}
