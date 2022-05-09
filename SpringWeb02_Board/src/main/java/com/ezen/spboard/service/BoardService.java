package com.ezen.spboard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spboard.dao.BoardDao;
import com.ezen.spboard.dto.Paging;
import com.ezen.spboard.dto.ReplyVO;
import com.ezen.spboard.dto.SpBoard;

@Service
public class BoardService {

	@Autowired
	BoardDao bdao;
	
	public ArrayList<SpBoard> selectBoard(Paging paging) {
		ArrayList<SpBoard> list = bdao.selectBoard(paging);
		return list;
	}

	public SpBoard boardView(String num) {
		// 서비스의 역할 1. 비즈니스 단위로 서비스를 호출한다.

		bdao.plusReadCount(num);
		return bdao.getBoard(num);
	}

	public ArrayList<ReplyVO> selectReply(String num) {
		return bdao.selectReply(num);
	}

	public void addReply(ReplyVO rvo) {
		bdao.addReply(rvo);		
	}

	public SpBoard getBoard(String num) {
		return bdao.getBoard(num);
	}

	public void deleteReply(String num) {
		bdao.deleteReply(num);
	}

	public void insertBoard(SpBoard sb) {
		bdao.insertBoard(sb);
	}

	public void boardUpdate(SpBoard sb) {
		bdao.boardUpdate(sb);
	}

	public void boardDelete(String num) {
		bdao.boardDelete(num);
	}

	public int getAllCount() {
		return bdao.getAllCount();
	}

}
