package actions;

import java.sql.SQLException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  



public class validateAction implements SessionAware{


	private String data;
	private String result;
	private String mail;
	private SessionMap<String,Object> sessionMap;  
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	
	public String execute() throws ParseException, ClassNotFoundException, SQLException{
		
		JSONObject json = (JSONObject)new JSONParser().parse(data);
		String uemail=(String)json.get("uemail");
		String upswd=(String)json.get("upswd");
		String user=(String)json.get("user");
		dbclass db=new dbclass();
		 result=db.validate(uemail,upswd,user);
		 mail=uemail;
		sessionMap.put("user",result);    
	
		return "success";
	}
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}
}
