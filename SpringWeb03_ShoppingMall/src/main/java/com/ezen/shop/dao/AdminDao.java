package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.shop.dto.AdminVO;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;
import com.ezen.shop.dto.Paging;
import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.dto.QnaVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class AdminDao {
	private JdbcTemplate template;
	@Autowired
	public AdminDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	public int workCheck(String workId, String workPwd) {
		int result = 0 ;
		String sql = "select pwd from worker where id=?";
		List<String> list = template.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String pwd = rs.getString("pwd");
				return pwd;
			}
		}, workId);
		if (list.size()==0) return result=-1;
		else if(workPwd.equals(list.get(0))) result=1;
		else result=0;
		
		return result;
	}
	public List<ProductVO> listProduct(Paging paging, String key) {
		//String sql = "select * from product order by pseq desc";
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, p.* from "
				+ "((select * from product where name like '%'||?||'%' order by pseq desc) p) "
				+ ") where rn>=?"
				+ ") where rn<=?";
		List<ProductVO> list = template.query(sql, new RowMapper<ProductVO>() {
			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductVO pvo = new ProductVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setIndate(rs.getTimestamp("indate"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice1(rs.getInt("price"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setPrice3(rs.getInt("price3"));
				pvo.setUseyn(rs.getString("useyn"));
				pvo.setBestyn(rs.getString("bestyn"));
				pvo.setImage(rs.getString("image"));
				pvo.setKind(rs.getString("kind"));
				pvo.setContent(rs.getString("content"));
				return pvo;
			}
		}, key, paging.getStartNum(), paging.getEndNum() );
		return list;
	}
	public int getAllCount(String tableName, String fieldName, String key) {
		int count = 0;
		String sql = "select count(*) as count from " + tableName + 
				" where " + fieldName + " like '%'||?||'%'";
		List<Integer> list = template.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int count = rs.getInt("count");
				return count;
			}
		}, key);
		return list.get(0);
	}
	public void insertProduct(ProductVO pvo) {
		String sql = "insert into product (name, price, price2, price3, content, image, kind, pseq) "
				+ "values(?,?,?,?,?,?,?,product_seq.nextval)";
		template.update(sql, pvo.getName(), pvo.getPrice1(), pvo.getPrice2(), pvo.getPrice3(),
				pvo.getContent(),pvo.getImage(),pvo.getKind());
	}
	
	public void updateProduct(ProductVO pvo) {
		String sql = "update product set name=?, price=?, price2=?, price3=?, content=?, "
				+ "image=?, kind=?, bestyn=?, useyn=? where pseq=?";
		template.update(sql, pvo.getName(), pvo.getPrice1(), pvo.getPrice2(), pvo.getPrice3(),
				pvo.getContent(),pvo.getImage(),pvo.getKind(), pvo.getBestyn(), pvo.getUseyn(), pvo.getPseq());
	}
	public List<OrderVO> listOrderAll(Paging paging, String key) {
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, o.* from "
				+ "((select * from order_view where mname like '%'||?||'%' order by result, odseq desc) o)"
				+ ") where rn>=?"
				+ ") where rn<=?";
		List<OrderVO> list = template.query(sql, new RowMapper<OrderVO>() {
			@Override
			public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				OrderVO ovo = new OrderVO();
				ovo.setOseq(rs.getInt("oseq"));
				ovo.setOdseq(rs.getInt("odseq"));
				ovo.setId(rs.getString("id"));
				ovo.setIndate(rs.getTimestamp("indate"));
				ovo.setMname(rs.getString("mname"));
				ovo.setZipnum(rs.getString("zip_num"));
				ovo.setAddress(rs.getString("address"));
				ovo.setPhone(rs.getString("phone"));
				ovo.setPseq(rs.getInt("pseq"));
				ovo.setPname(rs.getString("pname"));
				ovo.setQuantity(rs.getInt("quantity"));
				ovo.setPrice2(rs.getInt("price2"));
				ovo.setResult(rs.getString("result"));
				return ovo;
			}
		},key, paging.getStartNum(), paging.getEndNum());
		return list;
	}
	public void resultChange(int odseq) {
		String sql = "update order_detail set result='2' where odseq=?";
		template.update(sql, odseq);
	}
	public List<MemberVO> listMember(Paging paging, String key) {
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, m.* from "
				+ "((select * from member where name like '%'||?||'%') m)"
				+ ") where rn>=?"
				+ ") where rn<=?";
		List<MemberVO> list = template.query(sql, new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mvo = new MemberVO();
				mvo.setAddress(rs.getString("address"));
				mvo.setEmail(rs.getString("email"));
				mvo.setId(rs.getString("id"));
				mvo.setIndate(rs.getTimestamp("indate"));
				mvo.setName(rs.getString("name"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setUseyn(rs.getString("useyn"));
				mvo.setZip_num(rs.getString("zip_num"));
				return mvo;
			}
		},key, paging.getStartNum(), paging.getEndNum());
		return list;
	}
	public List<QnaVO> listQna(Paging paging, String key) {
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, q.* from "
				+ "((select * from qna where subject like '%'||?||'%'"
				+ "order by qseq desc) q)"
				+ ") where rn>=?"
				+ ") where rn<=?";
		List<QnaVO> list = template.query(sql, new RowMapper<QnaVO>() {
			@Override
			public QnaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				QnaVO qvo = new QnaVO();
				qvo.setId(rs.getString("id"));
				qvo.setIndate(rs.getTimestamp("indate"));
				qvo.setQseq(rs.getInt("qseq"));
				qvo.setSubject(rs.getString("subject"));
				qvo.setContent(rs.getString("content"));
				qvo.setRep(rs.getString("rep"));
				qvo.setReply(rs.getString("reply"));
				return qvo;
			}
		},key, paging.getStartNum(), paging.getEndNum());
		return list;
	}

	public void updateQna(QnaVO qvo) {
		String sql = "update qna set reply=?, rep='2' where qseq=?";
		template.update(sql, qvo.getReply(), qvo.getQseq());
	}
}
