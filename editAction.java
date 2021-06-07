package actions;


import java.sql.SQLException;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class editAction{
	
	private String regis;
	private String name;
	

	private int new_sem;
	private double new_cgpa;
	private String data;
	
	private String dept;
	private int sem;
	private double cgpa;
	
	


	public String getRegis() {
		return regis;
	}

	public void setRegis(String regis) {
		this.regis = regis;
	}

	public int getSem() {
		return sem;
	}

	public void setSem(int sem) {
		this.sem = sem;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String getName() {
		return name;
	}

	public String getDept() {
		return dept;
	}
	
	public int getNew_sem() {
		return new_sem;
	}


	public double getNew_cgpa() {
		return new_cgpa;
	}

public void setNew_sem(int new_sem) {
		this.new_sem = new_sem;
	}

	public void setNew_cgpa(double new_cgpa) {
		this.new_cgpa = new_cgpa;
	}

public String execute() throws ClassNotFoundException, SQLException, ParseException{
	JSONObject json = (JSONObject)new JSONParser().parse(data);
System.out.println("im in");
	regis=((String)json.get("regis")).trim();
	dept=((String)json.get("dept")).trim();
	name=((String)json.get("name")).trim();
	cgpa=Double.parseDouble(((String)json.get("cgpa")).trim());	
    sem=Integer.parseInt(((String)json.get("sem")).trim());
	
	dbclass db=new dbclass();
	String result=db.update(regis, name, dept, sem, cgpa);  
	 
	if(result.equals("success")){
	 this.name=db.getName();
	 this.dept=db.getDept();
	 this.sem=db.getSem();
	 this.cgpa=db.getCgpa(); 
	 return "success";
		}
	return "failure";
	
}


}