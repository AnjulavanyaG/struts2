package actions;

import java.sql.SQLException;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class deleteAction {

	private String data;
	private String regis;
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getRegis() {
		return regis;
	}

	public void setRegis(String regis) {
		this.regis = regis;
	}

	public String execute() throws ClassNotFoundException, SQLException, ParseException{
		
		JSONObject json = (JSONObject)new JSONParser().parse(data);
		dbclass db=new dbclass();
		regis=(String)json.get("regis");
		 String result=db.delete(regis);
		
		return result;
		}

	
}