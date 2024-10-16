function admin_login(){
	
	if(ad_id.value==""){
		alert("아이디를 입력하세요");
		ad_id.focus();
	}else{
		if(ad_pw.value==""){
			alert("패스워드를 입력하세요");
			ad_pw.focus();
		}else{
			
			frm_admin.method="post";
			frm_admin.action="./admin_login.do";
			frm_admin.submit();
			
		}
	}
}


function admin_logout() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "admin_logout", true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            window.location.href = './admin_login.jsp';
        }
    };
    xhr.send();
}