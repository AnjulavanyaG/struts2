
  
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<style>
.bg{
height:100%;
width:100%;
background-image:linear-gradient(rgba(0,0,0,0.4),rgba(0,0,0,0.4)),url("https://images.unsplash.com/photo-1551902675-a415b7df1ba1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80");
background-position:center;
background-size:cover;
position:absolute;
}
.form-box{
width:380px;
height:400px;
position:relative;
margin:6% auto;
background:#002266;
padding:5px;
overflow:hidden;
}

.input{
position:absolute;
width:280px;
transition:.5s;
}
.submit-btn{
width:55%;
padding:10px 30px;
cursor:pointer;
display:block;
margin:auto;
background:linear-gradient(to right,#ff105f,#ffad06);
border:0;
outline:none;
border-radius:30px;
}
.fnt{
color: #ffffff;
}
#form-align{
left:50px;
}
#events{
left:450px;
}
</style>
</head>
<body>

<div class="bg">
<div class="fnt">
<div class="form-box">
<div id="form-align">
<br>
<H3>Welcome</h3>
	<% String check=request.getParameter("u");
if(check==null)
	System.out.print("");
	else if(check.equals("login")){ %>
<p class="message">
	Please Login First !!!
					 </p>
					<%
					} 
 String check1=request.getParameter("u");
if(check==null)
	System.out.print("");
	else if(check.equals("fail")){ %>
<p class="message">
	Wrong Credentials !!!
					 </p>
					<%
					} 
	else if(check.equals("out")){
	 HttpSession ses=request.getSession();
	ses.invalidate();
}
%>
<p> 
					<label for="email"> Email </label><br>
					<input  id="uemail" required type="text" > 
</p>
<p> 
					<label for="password" > Password </label><br>
					<input  id="upswd" required="required" type="password" > 
					</p>
<h4>LOGIN AS:</H4>		
<p> 
					<input type="radio" name="user" id="admin" value="admin"> Admin

					 <input type="radio" name="user"  id="student" value="student">Student 
</p>


					
 <button onclick="submitfn();" class="submit-btn">Login</button>
</div>
</div>
</div>
</div>
<script>
function submitfn(){

		 var uemail = document.getElementById("uemail").value
	
		 var upswd = document.getElementById("upswd").value	
		
		  if(document.getElementById("admin").checked == true)
		     var user="admin";
		  else
			  var user="student";
		 var value={"uemail":uemail,"upswd":upswd,"user":user};
		
		 $.ajax({ 
			    url: "validate"+"?data="+JSON.stringify(value),
		         type: 'POST',				
				 contentType:"application/json;charset=utf-8",
					 dataType: "json",
					 success: function(res){
						 
						 if(res.result=="admin")
							 window.location.href = "index.jsp";
						 else if(res.result=="student"){
							// console.log(res.mail);
						var url="student.jsp?u="+res.mail;
							 window.location.href = url;
						 }
						 if(res.result=="failure")
							 window.location.href = "login.jsp?u=fail";
						
		            }
		            
			 })

		}
		</script> 
</body>
</html>