<HTML>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<HEAD>

<TITLE>Admin Page</TITLE>
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
*{
  box-sizing: border-box;
}

.centered {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

.column {
  float: left;
  width: 50%;
  padding: 10px;
  text-align: right;

}
.column1 {
  float: right;
  width: 50%;
  padding: 10px;
}
.center {
  margin-left: auto;
  margin-right: auto;
}
.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>
</HEAD>

<BODY>

<br>
<div class="row">
  <div class="column">
		<H3 style='text-align:center'>Student Details</H3>
		<BR>

		<TABLE id="table" class="text-center" BORDER="1">
				<thead>
			
					<TR>

						<TH>REGISTER NUMBER</TH>
						<TH>NAME</TH>
						<TH>DEPARTMENT</TH>
						<TH>SEMESTER</TH>
						<TH>CGPA</TH>
					
						<TH>EMAIL</TH>
						<TH>PASSWORD</TH>
						<TH>Modify</TH>
					</TR>
				</thead>
				
				
				
		</table>
		
		<br> <br>
		<p style="text-align: center">
			<button id="add" onclick="add();" class="btn btn-info">Add</button></p>
			<br> <br>
		<p style="text-align: center">
			<a href="login.jsp?u=out" class="btn btn-info" role="button">Logout</a>
		</p>

	<script>
	
	$(document).ready(function() {	
		
		$.ajax({ 
			 url: "read",
			 dataType:"json",
			 failure: function(){
				 window.location = "/login.jsp?u=login";
			 }
			 success: function(res){
                   var i=0,j=0;
                    while(i<res.details_rowcount){
				 $("table").append("<tr><td>"+res.list[i].regis+"</td><td><div contentEditable='true' id='name_"+res.list[i].regis+"'>"+res.list[i].name+"</td> <td><div contentEditable='true' id='dept_"+res.list[i].regis+"'>"+res.list[i].dept+"</td><td><div contentEditable='true' id='sem_"+res.list[i].regis+"'>"+res.list[i].sem+"</td><td><div contentEditable='true' id='cgpa_"+res.list[i].regis+"'>"+res.list[i].cgpa+"</td><td>"+res.list[i].email+"</td><td>"+res.list[i].pass+"<td><button id='"+res.list[i].regis+"' class='btn btn-primary' onclick='marks(this.id);'>Show Marks</button><button id='"+res.list[i].regis+"' class='btn btn-success' onclick='editfn(this.id);'>Save</button><button id='"+res.list[i].regis+"' class='btn btn-danger' onclick='delfn(this.id);'>Del</button></td></tr>") ;              		
                   i++ 
                   
					 }
			 }
			});
	})

	</script>
	<script>
	function marks(regis) {

		 var value={"regis":regis};	
		 $.ajax({ 
			 url: "mark"+"?data="+JSON.stringify(value),
	         type: 'POST',
			 contentType:"application/json;charset=utf-8",
			 success: function(res){
				    $(".column1").remove();
				 $(".row" ).append("<div class='column1' style='text-align:center'><h3>Marks<h3><BR><h4>Student register no: "+res.regis+" </h4><br><TABLE id='table1' class='center' BORDER='1' class='table table-striped'><thead class='thead-dark'><TR><TH>SUBJECT</TH><TH>MARKS</TH><th>MODIFY</TH><TR><tbody></tbody></table><br> <br><p style='text-align: center'><button id='"+regis+"' onclick='addMarks(this.id);' class='btn btn-info'>Add</button></p>");
				  var j=0;
				while(j<res.n){	 
					 $("tbody").append("<tr><td id='sub_"+j+"' >"+res.subj[j]+"</td><td id='mark_"+j+"' contenteditable='true'>"+res.marks[j]+"</td><td><button id='"+res.regis+"' class='btn btn-success' onclick='editMarks(this.id,"+j+");'>save</button></td></tr>");
			      j++;
				 
			 }
			 }
			 });
	}
	function editMarks(regis,id) {
		var sub = document.getElementById("sub_"+id+"").textContent;
		var mark = document.getElementById("mark_"+id+"").textContent;
		var value={"regis":regis,"subj":sub,"mark":mark};
		 $.ajax({ 
			 url: "editMarks"+"?data="+JSON.stringify(value),
	         type: 'POST',
			 contentType:"application/json;charset=utf-8",
			 success: function(res){
				 document.getElementById("mark_"+id+"").innerText=res.marks;
	}
		 });
	}
	
	 function add() {
		  $(".column1").remove();
		  event.preventDefault();      
	    $("table").append("<tr><td><input type=text  size='6' id='regist'></td><td><input type=text size='6' id='name'></td> <td><input type=text size='6' id='dept'></td> <td><input type=text size='6' id='sem'></td> <td><input type=text size='6' id='cgpa'></td> <td><input size='9' type=text id='email'></td> <td><input type=text size='9' id='pass'> <td><button class='btn btn-success' onclick='save()'>Save</button></td></tr>")                    
	  
	 }
	 
	 function addMarks(regis) {
		  event.preventDefault();      
	    $("tbody").append("<tr><td><input type=text size='5' id='subj'></td><td><input type=text size='4' id='marks'></td><td><button id='"+regis+"' class='btn btn-success' onclick='saveMarks(this.id)'>Save</button></td></tr>")                    
	  
	 }
 
 function save() {
	 var regis = $("#regist").val();
	 var name = $("#name").val();
	 var dept = $("#dept").val();
	 var sem = $("#sem").val();
	 var cgpa = $("#cgpa").val();
	 var email = $("#email").val();
	 var pass = $("#pass").val();
	
	 var value={"cgpa":cgpa,"regis":regis,"name":name,"dept":dept,"sem":sem,"email":email,"pass":pass};
	 $.ajax({ 
		 url: 'add'+"?data="+JSON.stringify(value),	
		 success: function(){
			location.reload();
		 }	 
	 });			
		}
 
 function saveMarks(regis) {
	 var subj = $("#subj").val();
	 var marks = $("#marks").val();
	 var value={"regis":regis,"subj":subj,"marks":marks};
	 $.ajax({ 
		 url: 'addMarks'+"?data="+JSON.stringify(value),
	     dataType: "json",
		 success: function(res){
			 location.reload();
		 }	 
	 });			
		}
 
		function editfn(regis) {

			var Sname = document.getElementById("name_"+regis+"").textContent;
			var Sdept = document.getElementById("dept_"+regis+"").textContent;
			
			var Ssem = document.getElementById("sem_"+regis+"").textContent;
			var Scgpa = document.getElementById("cgpa_"+regis+"").textContent;
			 var value={"regis":regis,"name":Sname,"dept":Sdept,"sem":Ssem,"cgpa":Scgpa};
			
			$.ajax({
				 url: "edit"+"?data="+JSON.stringify(value),
				  type: 'POST',
					
					 contentType:"application/json;charset=utf-8",
					 dataType:'json',
					success : function(res) {
						console.log(res.name);
					document.getElementById("name_"+regis+"").innerText=res.name;
					document.getElementById("dept_"+regis+"").innerText=res.dept;
					document.getElementById("sem_"+regis+"").innerText=res.sem;
					document.getElementById("cgpa_"+regis+"").innerText=res.cgpa;
					
				}
					
			});
		}
		
		 
		 function delfn(regis) {
			 var value={"regis":regis};	
			 $.ajax({ 
				 url: "delete"+"?data="+JSON.stringify(value),
		         type: 'POST',
				 contentType:"application/json;charset=utf-8",
				 success: function(){
					location.reload();
				 }	 
			 })
		}
	</script>
</div>
</div>
</BODY>
</HTML>