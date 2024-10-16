//아이디 찾기
function id_find(){
var fname = document.getElementById("find_name").value;
var fhp = document.getElementById("find_hp").value;
var femail = document.getElementById("find_email").value;

	if(frm_find.find_name.value==""){
		alert("가입하신 고객명을 입력하세요.");
		find_name.focus();
	}else{
		if(frm_find.find_hp.value==""){
			alert("가입하신 연락처를 입력하세요.");
			find_hp.focus();
		}else{
			if(frm_find.find_email.value==""){
				alert("가입하신 이메일을 입력하세요.");
				find_email.focus();
			}else{ //ajax 통신
				var http, result;
				http =new XMLHttpRequest();
				http.onreadystatechange = function(){
					if(http.readyState==4 && http.status == 200){
						result = this.response;
						if(result!=null&&result!=""){
							alert("고객 정보가 확인되었습니다.");
							document.getElementById("id_ser").innerHTML="고객의 아이디 :"+result; 	
						}else{
							alert("고객정보가 확인되지않습니다.");
						}		
					}
				}				
				http.open("post","./m_idsearch.do",true);
				http.setRequestHeader("content-type","application/x-www-form-urlencoded");
				http.send("fname="+fname+"&"+"fhp="+fhp+"&"+"femail="+femail);		
			}
		}
	}
}

//비밀번호 찾기
function pw_modify(){
var mid = document.getElementById("modify_id").value;
var mname = document.getElementById("modify_name").value;
var mhp = document.getElementById("modify_hp").value;
	
	if(frm_find.modify_id.value==""){
		alert("가입하신 아이디를 입력하세요.");
		modify_id.focus();
	}else{
		if(frm_find.modify_name.value==""){
			alert("가입하신 고객명을 입력하세요.");
			modify_name.focus();
		}else{
			if(frm_find.modify_hp.value==""){
				alert("가입하신 연락처를 입력하세요.");
			}else{
				var http, result;
				http =new XMLHttpRequest();
				http.onreadystatechange = function(){
					if(http.readyState==4 && http.status == 200){
						result = this.response;
						if(result!=null&&result!=""){
							alert("정상적으로 패스워드가 변경되었습니다.");
							location.href='./index.jsp';
						}else{
							alert("해당 고객정보는 확인되지않습니다.");
						}		
					}
				}								
				http.open("post","./m_pwsearch.do",true);
				http.setRequestHeader("content-type","application/x-www-form-urlencoded");
				http.send("mid="+mid+"&"+"mname="+mname+"&"+"mhp="+mhp);
			}
		}
	}	
}