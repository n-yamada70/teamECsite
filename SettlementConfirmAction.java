package com.internousdev.arizona.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.arizona.dao.DestinationInfoDAO;
import com.internousdev.arizona.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementConfirmAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	private List<DestinationInfoDTO> dtoList;

	public String execute() {

		String result=SUCCESS;

		DestinationInfoDAO dao=new DestinationInfoDAO();

		if(!session.containsKey("tempUserId") && !session.containsKey("userId")){
			return "sessionTimeout";
		}
		int logined=Integer.parseInt(String.valueOf(session.get("logined")));
		if(logined!=1){
			return "sessionTimeout";
		}

		try{
		String userId=session.get("userId").toString();

		dtoList=dao.destinationInfoDTOList(userId);
		}catch(SQLException e){
			e.printStackTrace();
			result=ERROR;
		}

		return result;
	}

  public Map<String, Object> getSession() {
	return session;
  }

  public void setSession(Map<String, Object> session) {
	this.session = session;
  }

  public List<DestinationInfoDTO> getDtoList() {
	return dtoList;
  }

  public void setDtoList(List<DestinationInfoDTO> dtoList) {
	this.dtoList = dtoList;
  }
}
