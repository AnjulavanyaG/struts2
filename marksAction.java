package actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class marksAction {

	ArrayList<String> subj;
	ArrayList<Double> marks;
    private int n=0;
	private String data;
	private String regis;
	
	
	public String getRegis() {
		return regis;
	}

	public void setRegis(String regis) {
		this.regis = regis;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public ArrayList<String> getSubj() {
		return subj;
	}

	public void setSubj(ArrayList<String> subj) {
		this.subj = subj;
	}

	public ArrayList<Double> getMarks() {
		return marks;
	}

	public void setMarks(ArrayList<Double> marks) {
		this.marks = marks;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public String execute() throws ClassNotFoundException, SQLException, ParseException{
		JSONObject json = (JSONObject)new JSONParser().parse(data);
		
		dbclass db=new dbclass();
		 regis=(String)json.get("regis");
		ResultSet rs=db.readMarks(regis);
		
		subj=new ArrayList<String>();
		marks = new ArrayList<Double>();
	
		while(rs.next()){
			n++;
			subj.add(rs.getString(2));
			marks.add(rs.getDouble(3));
			}	
		
		return "success";
			
	}
	
}
