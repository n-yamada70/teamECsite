package com.internousdev.arizona.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.arizona.dto.DestinationInfoDTO;
import com.internousdev.arizona.util.DBConnector;

public class DestinationInfoDAO{

	public int addDestinationData(String userId,String family_Name,String first_Name,
			String family_Name_Kana,String first_Name_Kana,String email,
			String tel_Number,String user_Address) throws SQLException{

		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		int count=0;

		String sql="INSERT INTO destination_info"
				+ " (user_id,family_name,first_name,family_name_kana,first_name_kana,"
				+ "email,tel_number,user_address,regist_date,update_date) "
				+ "VALUES (?,?,?,?,?,?,?,?,now(),now())";

		try{
			PreparedStatement ps=con.prepareStatement(sql);

			ps.setString(1, userId);
			ps.setString(2, family_Name);
			ps.setString(3, first_Name);
			ps.setString(4, family_Name_Kana);
			ps.setString(5, first_Name_Kana);
			ps.setString(6, email);
			ps.setString(7, tel_Number);
			ps.setString(8, user_Address);

			count=ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return count ;
	}

	public List<DestinationInfoDTO> destinationInfoDTOList(String userId) throws SQLException{

		List<DestinationInfoDTO> destinationInfoDTOList=new ArrayList<DestinationInfoDTO>();

		DBConnector db=new DBConnector();
		Connection con=db.getConnection();

		String sql="SELECT "
				+ "id,family_name,first_name,family_name_kana,"
				+ "first_name_kana,email,tel_number, user_address "
				+ "FROM "
				+ "destination_info "
				+ "WHERE "
				+ "user_id=?";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);

			ResultSet rs=ps.executeQuery();

			while(rs.next()){

				DestinationInfoDTO dto=new DestinationInfoDTO();

				dto.setId(rs.getInt("id"));
				dto.setFamilyName(rs.getString("family_name"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setFamilyNameKana(rs.getString("family_name_kana"));
				dto.setFirstNameKana(rs.getString("first_name_kana"));
				dto.setUserAddress(rs.getString("user_address"));
				dto.setEmail(rs.getString("email"));
				dto.setTelNumber(rs.getString("tel_number"));

				destinationInfoDTOList.add(dto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return destinationInfoDTOList;
	}

	public boolean isExistsDestinationInfo (String userId, int destinationId) throws SQLException {
		boolean result = false;
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();

		String sql="SELECT "
				+ "COUNT(id) As cnt "
				+ "FROM "
				+ "destination_info "
				+ "WHERE "
				+ "user_id=? and id=?";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, destinationId);

			ResultSet rs=ps.executeQuery();

			if(rs.next()){
				if(rs.getInt("cnt") > 0){
					result = true;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return result;
	}

	public int deleteDestinationData(int id) throws SQLException{

		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		int deleteCount=0;

		String sql="DELETE FROM destination_info WHERE id=?";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);

			deleteCount=ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return deleteCount;
	}
}
