var count=0;
var no =""; //자동 등록방지 난수 전역변수

$(function(){
//1:1문의 select 부분
	//1:1문의 select 부분
	$(".qa_part > li").click(function(){
		$n = $(this).index();
		$(".qa_part > li").attr("class","");
		$(".qa_part > li").eq($n).attr("class","onselect");
	});

	//햄버거 버튼 부분
	$("#menu_slide").click(function(){
		$("#menus_bar").fadeToggle();
	});
	//보안코드 생성
	
	sec();
	
	if($(location).attr('pathname') == "/index.jsp"){
		let query = window.location.search;
		let param = new URLSearchParams(query);
		let rvlogincheck = param.get('rvlogincheck');
		if(rvlogincheck == "no"){
			login_pop();
		}
	}
});


/*------------
 *--seeun 시작  
------------*/

//index 펜션 리스트 뿌리기-seeun
function get_pensionlist(login){
	let $data;
	let $html; 

	$.ajax({
		url : "./json/pension_list.json",
		cache : false,
		type : "GET",
		dataType : "JSON",
		async : true,
		success : function($node,$result){
			$data = $node;
			$.fn.htmls($node);
		},
		error:function(){ //예외처리랑 같다고 생각하면 됨
			console.log("통신오류 발생!!")
		}
	});
	
	//통신이 끝난 후 얘가 실행되어야 함
	$.fn.htmls = function(data){
		var f;
		var arr = [];
		for(f=0; f<data.length; f++){
			var p_name = data[f].p_name;
			arr.push(p_name);	
		}
		
		const set = new Set(arr);
		const uniqueArr = [...set];

		var i,ii;
		var data_pension = [];
		for(i=0; i<uniqueArr.length; i++){
			var dataarr = [];
			for(ii=0; ii<data.length; ii++){
				if(data[ii].p_name == uniqueArr[i]){
					dataarr.push(data[ii])
				}
			}
			data_pension.push(dataarr);
		}
		
		var minDataarr_result = [];
		var dp,dpa;
		for(dp=0; dp<data_pension.length; dp++){
			var minData = data_pension[dp].map(function(v){
				return v.p_price;
			})
			
			minData = Math.min.apply(null,minData);
			var minDataarr = [];
			for(dpa=0; dpa<data_pension[dp].length; dpa++){
				if(data_pension[dp][dpa].p_price == minData){
					minDataarr.push(data_pension[dp][dpa]);
				}
			}
			minDataarr_result.push(minDataarr[0]);
		}		

		
		for(r=0; r<minDataarr_result.length; r++){
			$(".product").append(`<li>
	            <div onclick="go_reservation('${minDataarr_result[r].p_code}','${login}')">
	                <span><img src=${minDataarr_result[r].p_img}></span>
	                <span>${minDataarr_result[r].p_name}</span>
	                <span>${priceToString(minDataarr_result[r].p_price)}원~</span>
	            </div>
			</li>`);
		}
	}
}

//가격 ,붙이기-seeun
function priceToString(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

//예약페이지로 넘기기 - seeun
function go_reservation(pcode,login){
	if(login == 0){
		alert("회원만 예약이 가능합니다.");
		login_pop();
	}else{
		location.href="./m_reservation.jsp?pcode="+pcode;	
	}	
}

//seeun
function get_roomlist(){
	var query = window.location.search;
	var param = new URLSearchParams(query);
	var pcode = param.get('pcode');
	
	var http,result;
	
	http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if(http.readyState==4 && http.status==200){
			eval(this.response);
			roomlist_data(data);
		}
	}
	http.open("post","./go_reservation.do",true);
	http.setRequestHeader("content-type","application/x-www-form-urlencoded");
	http.send("pcode="+pcode);	
}

//seeun
function roomlist_data(data){
	selectRoom(data,0);
	var select = $("#p_roomname");
	var i;
	for(i=0; i<data.length; i++){
		select.append(`<option value='${i}'>${data[i][1]}</option>`)
	}
	
	select.change(function(){
		selectRoom(data,this.value);
	})
}

//seeun
function selectRoom(data,idx){
	$("#p_name").text(data[idx][0]);
	$("#p_roomtype").empty().text(data[idx][2]);
	$("#p_person").text(data[idx][3]);
	$("#p_maxperson").text(data[idx][4]);
	$("#p_price").text(priceToString(data[idx][5])+"원");
	$("#p_code").val(data[idx][7]);
	$("#r_rname").val(data[idx][1]);
	
	var maxperson = data[idx][4];
	
	$("#r_person option").remove();
	var i;
	for(i=0; i<maxperson; i++){
		if(i==0){
			$("#r_person").append(`<option checked value='${i+1}'>${i+1}명</option>`)	
		}else{
			$("#r_person").append(`<option value='${i+1}'>${i+1}명</option>`)			
		}
	}
}

function reservation_submit(){
	if($("#p_roomname").val() ==""){
		alert("객실을 선택해주세요");
	}else if($("#r_date").val() == ""){
		alert("예약 일자를 입력해주세요.");
	}else if($("#r_name").val() == ""){
		alert("예약자명을 입력해주세요.")
	}else if($("#r_phone").val() == ""){
		alert("휴대폰번호를 입력해주세요.")
	}else if($("#r_email").val() == ""){
		alert("이메일을 입력해주세요.")
	}else{
		$("#reservation_form").attr("method","post");
		$("#reservation_form").attr("action","./reservation_ok.do");
		$("#reservation_form").submit();
//		
//		console.log($("#r_date").val())
	}
}

function reservation_delete(idx){
	if(confirm("정말 취소하시겠습니까?")){
		$("#reservation_del_form #r_idx").val(idx);
		$("#reservation_del_form").attr("method","post");
		$("#reservation_del_form").attr("action","./reservation_delete.do");
		$("#reservation_del_form").submit();
		//location.href = "./reservation_delete.do?idx="+idx;
	}
}

/*------------
 *--seeun 끝
------------*/


function sec(){
//	console.log(document.getElementById("capcha"));
//	var k =1;
//	while(k<6){
//		var n = Math.floor(Math.random()*10);
//		no +=n;
//		k++;
//		
//	}
	//document.getElementById("capcha").value = no;
	
	var $k=1;
	while($k<6){
		var $n = Math.floor(Math.random()*10);
		no+=$n;
		$k++;
	}
	$("#capcha").val(no);
}


//회원가입
function join(){
	if(mid.value==""){ //아이디 미입력
		alert("아이디를 입력하셔야합니다.");
		mid.focus();
	}else{ //아이디 입력 시 
		if(mid.value.length<6||mid.value.length>16){ //아이디 자릿수 제어
			alert("아이디를 6~16자 이내의 영문,숫자로 하셔야합니다");
			mid.focus();
		}else{ //아이디 특수문자 제어
			var j2 = mid.value.match(/\W/g);
			if(j2 != null){
				alert("영어 대소문자,숫자 만 입력하실 수 있습니다.");
				mid.focus();
			}else{
				if(mname.value==""){
					alert("이름을 입력하셔야합니다.");
					mname.focus();
				}else{
					if(mpw.value==""){ //비밀번호 미입력
						alert("패스워드를 입력하셔야합니다.");
						mpw.focus();
					}else{//비밀번호 입력 시
						if(mpw.value.length<6 ||mpw.value.length>12){ //패스워드 자리수
							alert("패스워드는 6~12자리 이내로 입력하셔야합니다.");
							mpw.focus();
						}else{//패스워드 특수문자 제어
							var j2 = mpw.value.match(/\W/g);
							if(j2 != null){ 
								alert("영어 대소문자,숫자 만 입력하실 수 있습니다.");
								mpw.focus();
							}else{ //패스워드 확인
								if(mpw2.value!=mpw.value){
									alert("패스워드를 올바르게 입력하셔야합니다.");
									mpw2.focus();
								}else{ //이메일 확인
									if(memail.value==""){
										alert("이메일을 입력하셔야합니다.");
										memail.focus();
									}else{//이메일 유효성 검사
										var email=frm.memail.value;
										var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
										if(exptext.test(email)==false){
											alert("이메일 형식이 올바르지 않습니다.");
											memail.focus();
											return false;
										}else{
											if(mhp.value==""){
												alert("연락처를 입력하셔야합니다.");
												mhp.focus();
											}else{
												if(mhp.value.length<10){
													alert("연락처는 최소 10자 이상의 숫자를 입력하셔야합니다.");
													mhp.focus();
												}else{
													if(document.getElementById("sec").value!=no){
														alert("보안코드를 올바르게 입력하세요");
														sec.focus();
														no="";
														sec();
													}else{
														frm.method="POST";
														frm.action="./join.do";
														frm.submit();
													}
												}
											}	
										}
									}
								}	
							}
						}
					}
				}
			}
		}
	}
}


//기본정보 입력하기 버튼
function join_agree(){
	var ag1 = document.getElementById("ck1").checked;
	var ag2 = document.getElementById("ck2").checked;
	var ag3 = document.getElementById("ck3").checked;
	var ag4 = document.getElementById("ck4").checked;
	if(ag1 ==false ||ag2 ==false||ag3 ==false||ag4 ==false){
		alert("약관에 모두 동의하셔야 회원가입이 진행 됩니다.");
	}else{
		frm_ag.method="POST";
		frm_ag.action="./agree.do";
		frm_ag.submit();

	}
	
}
//전체 동의
function all_ag(){
	var ck = document.getElementById("ck6");
	var w=1;
	//var st = document.getElementById("ck"+w);
		while(w<=6){
			document.getElementById("ck"+w).checked=ck.checked;
			w++;
	}
}
//개별 선택 동의
 function ck_box(){
	var count = 0;
	var b;
	var w=1;
	while(w<=5){
		var box = document.getElementById("ck"+w).checked;
		if(box ==true){
			count++;
		}
		w++;
	}
	if(count == 5){
		document.getElementById("ck6").checked = true;
	}else{
		document.getElementById("ck6").checked = false;
	}
}

//로그인 팝업
function login_pop(){
	var pop = document.getElementById("popup");
	if(pop.style.display=="none"){
		pop.style.display = "flex";
	}
	else{
		pop.style.display = "none";
	}
}

//로그인 팝업 닫기
function pop_close(){
	var pop = document.getElementById("popup");
	pop.style.display = "none";
}

//페이지 이동
function page_location(n){
	var url = "";
	if(n==1){
		url = "./m_idsearch.jsp";
	}
	else if(n==2){
		url = "./m_join.jsp";
	}
	location.href = url;
}



