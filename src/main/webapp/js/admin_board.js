function reply_write(){
	var frm_replyWrite = document.getElementById("frm_replyWrite");
	frm_replyWrite.setAttribute("action","./admin_reply.do");
	frm_replyWrite.setAttribute("method","post");
	frm_replyWrite.submit();
	alert("답변이 등록되었습니다.");	
}