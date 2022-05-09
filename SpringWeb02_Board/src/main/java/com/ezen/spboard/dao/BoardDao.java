package com.ezen.spboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.spboard.dto.Paging;
import com.ezen.spboard.dto.ReplyVO;
import com.ezen.spboard.dto.SpBoard;
import com.ezen.spboard.utill.DataBaseManager;

@Repository
public class BoardDao {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Autowired
	DataBaseManager dbm;
	
	public ArrayList<SpBoard> selectBoard(Paging paging) {
		ArrayList<SpBoard> list = new ArrayList<SpBoard>();
		con = dbm.getConnection();
		// String sql = "select * from board order by num desc";
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, B.* from"
				+ "(( select * from board order by num desc ) B )"
				+ ") where rn >= ? "
				+ ")where rn <= ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, paging.getStartNum());
			pstmt.setInt(2, paging.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SpBoard sb = new SpBoard();
				sb.setNum(rs.getInt("num"));
				sb.setPass(rs.getString("pass"));
				sb.setUserid(rs.getString("userid"));
				sb.setTitle(rs.getString("title"));
				sb.setEmail(rs.getString("email"));
				sb.setContent(rs.getString("content"));
				sb.setWritedate(rs.getTimestamp("writedate"));
				sb.setReadcount(rs.getInt("readcount"));
				sb.setImagename(rs.getString("imgfilename"));
				
				String sql2 = "select count(*) as cnt from reply where boardnum=?";
				int num = rs.getInt("num");
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1,num);
				ResultSet rs2 = pstmt2.executeQuery();
				if(rs2.next()) sb.setReplycnt(rs2.getInt("cnt"));
				else sb.setReplycnt(0);
				
				pstmt2.close();
				rs2.close();
				
				list.add(sb);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally {		dbm.close(con, pstmt, rs);  }
		return list;
	}

	public void plusReadCount(String num) {
		String sql = "update board set readcount = readcount+1 where num = ?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  Integer.parseInt(num) );
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { dbm.close(con, pstmt, rs);  }		
	}

	public SpBoard getBoard(String num) {
		SpBoard sb = new SpBoard();
		String sql  = "select * from board where num = ?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sb.setNum(rs.getInt("num"));
				sb.setUserid(rs.getString("userid"));
				sb.setPass(rs.getString("pass"));
				sb.setEmail(rs.getString("email"));
				sb.setTitle(rs.getString("title"));
				sb.setContent(rs.getString("content"));
				sb.setWritedate(rs.getTimestamp("writedate"));
				sb.setReadcount(rs.getInt("readcount"));
				sb.setImagename(rs.getString("imgfilename"));
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { dbm.close(con, pstmt, rs);  }
		return sb;
	}

	public ArrayList<ReplyVO> selectReply(String num) {
		ArrayList<ReplyVO> list = new ArrayList<ReplyVO>();
		String sql = "select * from reply where boardnum=? order by num desc";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(num));
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				ReplyVO rvo = new ReplyVO();
				rvo.setNum(rs.getInt("num"));
				rvo.setBoardnum( rs.getInt("boardnum") );
				rvo.setUserid(rs.getString("userid"));
				rvo.setWritedate(rs.getTimestamp("writedate") );
				rvo.setContent(rs.getString("content"));
				list.add(rvo);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { dbm.close(con, pstmt, rs);  }
		return list;
	}

	public void addReply(ReplyVO rvo) {
		String sql = "insert into reply(num, boardnum, userid, content) "
				+ "values( reply_seq.nextVal , ?, ?, ?)";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  rvo.getBoardnum());
			pstmt.setString(2, rvo.getUserid());
			pstmt.setString(3, rvo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) { 	e.printStackTrace();		
		} finally {	dbm.close(con, pstmt, rs); }	
	}

	public void deleteReply(String num) {
		String sql = "delete from reply where num=?";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally {dbm.close(con, pstmt, rs);
		}
	}

	public void insertBoard(SpBoard sb) {
		con = dbm.getConnection();
		String sql = "insert into board(num, pass, userid, email, title, content, imgfilename) "
				+ "values( board_seq.nextVal, ?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sb.getPass());
			pstmt.setString(2, sb.getUserid());
			pstmt.setString(3, sb.getEmail());
			pstmt.setString(4, sb.getTitle());
			pstmt.setString(5, sb.getContent());
			pstmt.setString(6, sb.getImagename());
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally {dbm.close(con, pstmt, rs);
		}
	}

	public void boardUpdate(SpBoard sb) {
		con = dbm.getConnection();
		String sql = "update board set pass=?, userid=?, email=?, title=?, content=?, imgfilename=? "
				+ "where num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,sb.getPass());
			pstmt.setString(2, sb.getUserid());
			pstmt.setString(3, sb.getEmail());
			pstmt.setString(4, sb.getTitle());
			pstmt.setString(5, sb.getContent());
			pstmt.setString(6, sb.getImagename());
			pstmt.setInt(7, sb.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally { dbm.close(con, pstmt, rs);
		}
	}

	public void boardDelete(String num) {
		con = dbm.getConnection();
		String sql = "delete from board where num=?"; 
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally { dbm.close(con, pstmt, rs);
		}
	}

	public int getAllCount() {
		int count = 0;
		con = dbm.getConnection();
		String sql = "select count(*) as count from board";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt("count");
		} catch (SQLException e) {e.printStackTrace();
		} finally { dbm.close(con, pstmt, rs);
		} return count;
	}
}
