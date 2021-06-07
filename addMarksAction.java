package actions;


import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class addMarksAction{
	
	
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
	 regis=((String)json.get("regis")).trim();
	String subj=((String)json.get("subj")).trim();;
	double marks=Double.parseDouble(((String)json.get("marks")).trim());
	dbclass db=new dbclass();
	String result=db.addMarks(regis,subj,marks);

if(result.equals("success")){
	
	return "successs";
}
return "failure";
}
}