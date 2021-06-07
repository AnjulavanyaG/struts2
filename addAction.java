package actions;


import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class addAction{
	
	
	private String data;
	

public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

public String execute() throws ClassNotFoundException, SQLException, ParseException{
	JSONObject json = (JSONObject)new JSONParser().parse(data);

	double cgpa=Double.parseDouble(((String)json.get("cgpa")).trim());
	int sem=Integer.parseInt(((String)json.get("sem")).trim());
	String regis=((String)json.get("regis")).trim();
	String dept=((String)json.get("dept")).trim();
	String name=((String)json.get("name")).trim();
	String email=((String)json.get("email")).trim();
	String pass=((String)json.get("pass")).trim();
	



	dbclass db=new dbclass();
	String result =db.add(regis, name, dept, sem, cgpa, email, pass);
	
	return result;
		}
}

