function auto_check(){
	if(frm_top.login_auto.checked==true){
		
	}else{
		localStorage.removeItem("auto");
		localStorage.removeItem("auto_id");
	}
}


//로그인 
function login_btn(){
	const idElement = document.getElementById("login_id").value;
	var login_auto_ck = document.getElementById("login_auto").checked;
	var va = document.getElementById("login_auto").value;
	if(login_id.value==""){
		alert("아이디를 입력하세요");
		return false;
	}else{
		if(login_pw.value==""){
			alert("패스워드를 입력하세요");
			return false;
		}else{
			if(login_auto_ck==true){
				frm_top.login_auto.value=true;
				if(confirm("PC방 및 공공장소에서는 자동 로그인을 권장하지 않습니다.")){
					return true;
				}else{
					return false;
				}
			}else{
				frm_top.login_auto.value=false;
			}
		}
	}	
}
