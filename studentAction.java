package actions;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class studentAction {
	private String data;
	ArrayList<HashMap<String,String>> list;
    private int details_rowcount=0;
    ArrayList<String> subj;
    ArrayList<Double> marks;
    int n;
    

public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
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

public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setDetails_rowcount(int details_rowcount) {
		this.details_rowcount = details_rowcount;
	}

public ArrayList<HashMap<String, String>> getList() {
		return list;
	}

	public void setList(ArrayList<HashMap<String, String>> list) {
		this.list = list;
	}

	public int getDetails_rowcount() {
		return details_rowcount;
	}



	public String execute() throws ClassNotFoundException, SQLException, ParseException{
		
		JSONObject json = (JSONObject)new JSONParser().parse(data);
		
		dbclass db=new dbclass();
	
		 String email=(String)json.get("email");
		
		ResultSet rs=db.studentDetails(email);
		
list = new ArrayList<HashMap<String,String>>();
		
		HashMap<String,String> hm;
		String regis="";
		
		while(rs.next()){
			System.out.println("init");
			hm=new HashMap<String,String>();
			hm.put("name",rs.getString(1));
			hm.put("regis",rs.getString(2));
			regis=rs.getString(2);
			hm.put("dept",rs.getString(3));
			hm.put("sem",String.valueOf(rs.getInt(4)));
			hm.put("cgpa",String.valueOf(rs.getDouble(5)));
			hm.put("email",rs.getString(6));
			hm.put("pass",rs.getString(7));		
			list.add(hm);
					
		}
		
ResultSet rs1=db.readMarks(regis);
		
		subj=new ArrayList<String>();
		marks = new ArrayList<Double>();
System.out.println("btwn");
		while(rs1.next()){
			n++;
			subj.add(rs1.getString(2));
			marks.add(rs1.getDouble(3));
			}	
		
		return "success";
			
	}
	
}
