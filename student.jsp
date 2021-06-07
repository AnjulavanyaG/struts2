
<html>
<head>
<title> Student Details</title>
    <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css">
	<script type = "text/javascript" 
    src = "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js">
 </script>
 <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"> </script>
<style>
<style>
.bg{
height:100%;
width:100%;
background-image:linear-gradient(rgba(0,0,0,0.4),rgba(0,0,0,0.4)),url("https://images.unsplash.com/photo-1551902675-a415b7df1ba1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80");
background-position:center;
background-size:cover;
position:absolute;
}
 .submit-btn{
width:40%;
padding:10px 30px;
cursor:pointer;
display:block;
margin:auto;
background:linear-gradient(to right,#ff105f,#ffad06);
border:0;
outline:none;
border-radius:30px;
}
.form-box{
width:380px;
height:400px;
position:relative;
margin:6% auto;
padding:7px;
overflow:hidden;
color:white;
}
</style>
</head>
<body>
<%
		//if (session.getAttribute("student") == null)
			//response.sendRedirect("login.jsp?u=login");
	%>
<div class="bg">
<br><br><br>
<h2 style="text-align:center"> Student Details</h2>
<div CLASS="form-box">
		<TABLE id="table" >
					
				
		</table>
		
		
         <br>
        
         <p style="text-align:center"> <a href="login.jsp?u=out" class="submit-btn" role="button">Logout</a></p>
    
         </div>
         </div>
         <script>
         $(document).ready(function() {	
        	 const queryString = window.location.search;

        	 const urlParams = new URLSearchParams(queryString);

        	 const email = urlParams.get('u');
       
        	 
        	 	 var value={"email":email};	
		$.ajax({ 
			
			 url: "student"+"?data="+JSON.stringify(value),
			 dataType:"json",
			 
			 success: function(res){	
				 console.log(res);
                   var i=0,j=0;
                    while(i<1){
				 $("table").append("<tr><td>REGISTER NUMBER</td><td>:</td><td>"+res.list[i].regis+"</td></tr><tr><td>NAME</td><td>:</td><td>"+res.list[i].name+"</td></tr><tr><td>DEPARTMENT</td><td>:</td><td>"+res.list[i].dept+"</td></tr><tr><td>SEM</td><td>:</td><td>"+res.list[i].sem+"</td></tr><tr><td>CGPA</td><td>:</td><td>"+res.list[i].cgpa+"</td></tr><tr><td>EMAIL</td><td>:</td><td>"+res.list[i].email+"</td></tr>");
                   i++ 
                   }
                    while(j<res.n){
       				 $("table").append("<tr><td>"+res.subj[j].toUpperCase()+"</td><td>:</td><td>"+res.marks[j]+"</td></tr>");
                          j++ 
                          }
			
			 }
			});
	})
	</script>
         
         </body>

</html>