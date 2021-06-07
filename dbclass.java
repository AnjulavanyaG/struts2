package actions;

import java.sql.*;


public class dbclass{
	
	Connection conn;
	java.sql.Statement st;
	ResultSet rs;
	private String name;
	private String dept;
	private int sem;
	private double cgpa;
	private double marks;
	
	
	public double getMarks() {
		return marks;
	}

	public String getName() {
		return name;
	}

	public String getDept() {
		return dept;
	}

	public int getSem() {
		return sem;
	}

	public double getCgpa() {
		return cgpa;
	}

	public Connection connect() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://@localhost:3306/lav","root","anju lavanya");
		return conn;

	}
	
	public ResultSet readDetails() throws ClassNotFoundException, SQLException{
		conn=connect();
		PreparedStatement pst=conn.prepareStatement("select * from students");
		return rs=pst.executeQuery();
	}
	
	public ResultSet studentDetails(String email) throws ClassNotFoundException, SQLException {
		conn=connect();
		PreparedStatement pst=conn.prepareStatement("select * from students where email=?");
		pst.setString(1, email);
		return rs=pst.executeQuery();
		
	}	
	
	public ResultSet readMarks(String regis) throws ClassNotFoundException, SQLException {
		conn=connect();
		PreparedStatement pst=conn.prepareStatement("select * from marks where register_no=?");
		pst.setString(1, regis);
		return rs=pst.executeQuery();
	}

	public String update(String regis, String name, String dept, int sem, double cgpa) throws ClassNotFoundException, SQLException {
			conn=connect();
		PreparedStatement pst=conn.prepareStatement("update students set name=?,deptartment=?,semester=?,cgpa=? where register_no=?");
		
		pst.setString(1,name);
		pst.setString(2,dept);
		pst.setInt(3,sem);
		pst.setDouble(4,cgpa);
		pst.setString(5,regis);
		boolean check=pst.execute();
		if(check){
			PreparedStatement pst1=conn.prepareStatement("select * from marks where register_no=?");
			pst1.setString(1, regis);
			ResultSet rs1=pst1.executeQuery();
			rs1.next();
			this.name=rs.getString(1);
			this.dept=rs.getString(3);
			this.sem=rs.getInt(4);
			this.cgpa=rs.getDouble(5);
		}
		return "updated";
}

	public String delete(String regis) throws SQLException, ClassNotFoundException {
		conn=connect();
		PreparedStatement pst=conn.prepareStatement("delete from students where register_no=?");
		pst.setString(1,regis);
		pst.execute();
		return "success";
	}



	public String add(String regis, String name, String dept, int sem, double cgpa, String email, String pass) throws ClassNotFoundException, SQLException {
		conn=connect();
		PreparedStatement pst=conn.prepareStatement("insert into students values(?,?,?,?,?,?,?)");
		pst.setString(1,name);
		pst.setString(2,regis);
		pst.setString(3,dept);
		pst.setInt(4,sem);
		pst.setDouble(5,cgpa);
		pst.setString(6,email);
		pst.setString(7,pass);
		pst.executeUpdate();
		return "success";
	
		
	}




	public String validate(String uemail, String upswd, String user) throws ClassNotFoundException, SQLException {
		conn=connect();
		if(user.equals("admin")){
		PreparedStatement pst=conn.prepareStatement("select password from admins where email=?");
		pst.setString(1,uemail);
		rs=pst.executeQuery();
		rs.next();
		if((rs.getString(1)).equals(upswd)){
			
			return "admin";
		}
		else{
			return "failure";
		}
	}
		else{
			PreparedStatement pst=conn.prepareStatement("select password from students where email=?");
			pst.setString(1,uemail);
			rs=pst.executeQuery();
			rs.next();
			if((rs.getString(1)).equals(upswd)){
			    return "student";
			}
			else{
				return "failure";
			}
		}
	}




	public String addMarks(String regis, String subj, double marks) throws ClassNotFoundException, SQLException {
		conn=connect();
		PreparedStatement pst=conn.prepareStatement("insert into marks values(?,?,?)");
		pst.setString(1,regis);
		pst.setString(2,subj);
		pst.setDouble(3,marks);
		pst.executeUpdate();
		return "success";
	}

	public String updateMarks(String regis, String subj, double mark) throws ClassNotFoundException, SQLException {
		conn=connect();
		PreparedStatement pst=conn.prepareStatement("update marks set marks=? where subject=? && register_no=?");
		pst.setDouble(1, mark);
		pst.setString(2, subj);
		pst.setString(3, regis);
	      pst.execute();
			this.marks=mark;
			
		return "updated";
		
	}

	

	

	
		
}
