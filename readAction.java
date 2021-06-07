package actions;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpSession;  
import org.apache.struts2.ServletActionContext;  


public class readAction {
	ArrayList<HashMap<String,String>> list;
    private int details_rowcount=0;


public ArrayList<HashMap<String, String>> getList() {
		return list;
	}

	public void setList(ArrayList<HashMap<String, String>> list) {
		this.list = list;
	}

	public int getDetails_rowcount() {
		return details_rowcount;
	}



	public String execute() throws ClassNotFoundException, SQLException{
	HttpServletRequest request=ServletActionContext.getRequest();  
     HttpSession session=request.getSession();  
    
    String s=(String)session.getAttribute("user");  
    System.out.print(s);
    if(s==null || !s.equals("admin")){  
    return "failure";  
     }
     else{ 
		dbclass db=new dbclass();
		ResultSet rs=db.readDetails();
		ResultSetMetaData rsmd = rs.getMetaData(); 
		int columnCount = rsmd.getColumnCount();
		 list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> hm;
		int i;
		while(rs.next()){
			i=1;
			details_rowcount++;
			while(i <= columnCount) {
			hm=new HashMap<String,String>();
			hm.put("name",rs.getString(i++));
			hm.put("regis",rs.getString(i++));
			hm.put("dept",rs.getString(i++));
			hm.put("sem",String.valueOf(rs.getInt(i++)));
			hm.put("cgpa",String.valueOf(rs.getDouble(i++)));
			hm.put("email",rs.getString(i++));
			hm.put("pass",rs.getString(i++));		
			list.add(hm);
			}		
		}
		//System.out.println(list);
		return "success";
		}	
	}
	
}
