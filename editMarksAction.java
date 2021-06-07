package actions;

import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class editMarksAction {
private String data;
private double marks;

public String getData() {
	return data;
}

public void setData(String data) {
	this.data = data;
}


public double getMarks() {
	return marks;
}

public void setMarks(double marks) {
	this.marks = marks;
}

public String execute() throws ParseException, ClassNotFoundException, SQLException{
JSONObject json = (JSONObject)new JSONParser().parse(data);
	String regis=((String)json.get("regis")).trim();
	//System.out.println(regis);
	String subj=((String)json.get("subj")).trim();
	//System.out.println(subj);
	double mark=Double.parseDouble(((String)json.get("mark")).trim());
	
	dbclass db=new dbclass();
	String result=db.updateMarks(regis,subj,mark);
	if(result.equals("updated")){
		this.marks=db.getMarks();
	return "success";
}
return "failure";
}
}