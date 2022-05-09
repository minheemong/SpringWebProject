package com.ezen.spboard.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseManager {
	
	@Autowired
	DataBaseUserInfo uif;
	
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(uif.getDriver());
			con = DriverManager.getConnection(uif.getUrl(), uif.getId(), uif.getPw());
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace(); 	}
		return con;
	}
	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(con!=null)con.close();
			if(pstmt!=null)pstmt.close();
			if(rs!=null)rs.close();
		} catch (SQLException e) {  e.printStackTrace();	}
	}
}
