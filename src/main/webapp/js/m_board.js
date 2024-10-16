$(function(){
	var ck = 0;
	
	//1:1문의 select 부분
	$(".qa_part > li").click(function(){
		$n = $(this).index();
		$(".qa_part > li").attr("class","");
		$(".qa_part > li").eq($n).attr("class","onselect");
		var selectedValue = $(this).text();
		$("#selectedValue").val(selectedValue);
		ck = 1;
	});
	
	
	//1:1 문의등록 (문의작성 페이지)
	$("#qa_write").click(function(){
		if(ck == 0){
			alert("문의 유형을 선택해 주세요.");
		}
		else if($("#b_subj").val()!="" && $("#b_post").val()!=""){
			$("#frm_board").attr("method","post");
			$("#frm_board").attr("action","./m_qawrite.do");
			$("#frm_board").attr("enctype","multipart/form-data");
			$("#frm_board").submit();
			alert("문의가 등록되었습니다.");
			ck = 0;
		}
		else {
			alert("문의하실 제목과 내용을 모두 입력해 주세요.");
		}
	});
	
	$("#qa_modify").click(function(){
		$("#frm_qa_modify").attr("method","post");
		$("#frm_qa_modify").attr("action","./m_qamodify.do");
		$("#frm_qa_modify").attr("enctype","multipart/form-data");
		$("#frm_qa_modify").submit();
		alert("문의가 수정되었습니다.");
	});
	
	$("#qa_delete").click(function(){
		if(confirm("정말 문의를 삭제하시겠습니까?")){
			$("#frm_qa_delete").attr("method","post");
			$("#frm_qa_delete").attr("action","./m_qadelete.do");
			$("#frm_qa_delete").attr("enctype","application/x-www-form-urlencoded");
			$("#frm_qa_delete").submit();
			alert("문의가 삭제되었습니다.");
		}
	});
	
});


function checkFileExtension(fileInput) {
    const allowedExtensions = ['jpg', 'gif', 'bmp', 'png', 'svg'];
    const filePath = fileInput.value;
    const fileExtension = filePath.split('.').pop().toLowerCase();

    if (!allowedExtensions.includes(fileExtension)) {
        alert('올바른 파일 형식이 아닙니다. jpg, gif, bmp, png, svg 형식의 파일을 선택하세요.');
        fileInput.value = ''; // 파일 입력 초기화
        return false;
    }
    return true;
}

function checkFiles() {
    const fileInputs = document.querySelectorAll('input[type="file"]');
    
    for (const fileInput of fileInputs) {
        if (fileInput.value && !checkFileExtension(fileInput)) {
            return false; // 파일 형식이 올바르지 않으면 폼 제출 중단
        }
    }

    return true; // 모든 파일 형식이 올바르면 폼 제출 계속
}


function removeFileTag(elementId) {
    document.getElementById(elementId).style.display = 'none';
}
