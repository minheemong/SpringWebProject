package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.shop.dto.AddressVO;
import com.ezen.shop.dto.MemberVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class MemberDao {
	private JdbcTemplate template;
	@Autowired
	public MemberDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	public MemberVO getMember(String id) {
		String sql = "select * from member where id=?";
		List<MemberVO> list = null;
		
		list = template.query(sql, new RowMapper<MemberVO>() {

			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setPwd(rs.getString("pwd"));
				mvo.setName(rs.getString("name"));
				mvo.setEmail(rs.getString("email"));
				mvo.setZip_num(rs.getString("zip_num"));
				mvo.setAddress(rs.getString("address"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setUseyn(rs.getString("useyn"));
				mvo.setIndate(rs.getTimestamp("indate"));
				return mvo;
			}	
		}, id);	
		if(list.size()==0) return null;
		else return list.get(0);
	}
	
	// 1. template.query의 첫번째 전달인수로 sql을 위치시킵니다.
	// 2. 두번째 전달인수로 new RowMapper<>(){} 를 위치시킵니다.
	// 3. 이때 <> 안에는 리턴형 자료의 Dto의 이름을 써줍니다.
	// 4. 두번째 전달인수로 들어간 RowMapper 생성자를 이용하여 Override될 메서드를 추가합니다.
	// 5. 추가된 메서드에 Dto 객체를 생성하고, 각 멤버변수를 rs.getInt or rs.getString 등을 이용하여 set합니다.
	// 6. 다 채워진 Dto 객체를 리턴합니다.
	// 7. 마지막 괄호 앞에 (,)로 구분하여 ?에 들어갈 인수를 차례로 써줍니다.
	// 8. List가 아닌 단일 Dto 리턴되어야 한다면 list.get(0)을 리턴합니다.
	// 		단, 리턴값이 null일 경우 if로 처리합니다.
	
	public int confirmID(String id) {
		int result = 0;
		String sql = "select * from member where id=?";
		List<MemberVO> list = template.query(sql, new RowMapper<MemberVO>() {

			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				return mvo;
			}
		}, id);
		if(list.size()==0) result = 1; // 사용 가능, 검색한 아이디 없음
		else result = -1; // 사용 불가능, 검색한 아이디가 존재
		System.out.print(result);
		return result;
	}
	
	public List<AddressVO> selectAddressByDong(String dong) {
		String sql = "select * from address where dong like '%'||?||'%'";
		List<AddressVO> list = template.query(sql, new RowMapper<AddressVO>() {

			@Override
			public AddressVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AddressVO avo = new AddressVO();
				avo.setSido(rs.getString("sido"));
				avo.setGugun(rs.getString("gugun"));
				avo.setDong(rs.getString("dong"));
				avo.setBunji(rs.getString("bunji"));
				avo.setZip_code(rs.getString("zip_code"));
				avo.setZip_num(rs.getString("zip_num"));
				return avo;
			}
			
		}, dong);
		return list;
	}
	
	public void insertMember(MemberVO mvo) {
		String sql = "insert into member(id, pwd, name, zip_num, address, email, phone) "
				+ "values(?,?,?,?,?,?,?)";
		template.update(sql, mvo.getId(), mvo.getPwd(), mvo.getName(), mvo.getZip_num(), mvo.getAddress(), 
				mvo.getEmail(), mvo.getPhone());
	}
	
	public void memberUpdate(MemberVO mvo) {
		String sql = "update member set pwd=?, name=?, zip_num=?, address=?, email=?, phone=? "
				+ "where id=?";
		template.update(sql, mvo.getPwd(), mvo.getName(), mvo.getZip_num(), mvo.getAddress(), 
				mvo.getEmail(), mvo.getPhone(), mvo.getId());
	}
}
