function loginCheck(){
	if( document.frm.id.value == "" ){
		alert("아이디를 입력하세요");
		document.frm.id.focus();
		return false;
	} else if( document.frm.pw.value == "" ){
		alert("비밀번호를 입력하세요");
		document.frm.pw.focus();
		return false;
	} else {
		return true;
	}
}




function idCheck(){
	if( document.frm.id.value==""){
		alert('아이디를 입력하여 주십시오.');
		document.frm.id.focus();
		return;
	}
	var id = document.frm.id.value;
	var opt = "toolbar=no, menubar=no, resizable=no, width=450, height=200";
	window.open("idcheck?id=" + id, "중복체크", opt);
}




function idok(userid){
	opener.frm.id.value = userid;
	opener.frm.re_id.value = userid;
	self.close();
}




function joinCheck(){
	if( document.frm.name.value.length==0){  
		alert("이름을 써주세요");
		frm.name.focus();
		return false;
	}else if( document.frm.pw.value==""){  
		alert("암호는 반드시 입력하여야 합니다");
		frm.pw.focus();
		return false;
	}else if( document.frm.pw.value != document.frm.pw_check.value){
		alert("암호가 일치하지 않습니다");
		frm.pw_check.focus();
		return false;
	}else if (document.frm.re_id.value != document.frm.id.value) { 
		alert("중복 체크를 하지 않았습니다.");
		frm.id.focus();
		return false;
	} else {
		return true;
	}
}




function editCheck(){
	if( document.frm.name.value.length==0){  
		alert("이름을 써주세요");
		frm.name.focus();
		return false;
	}else if( document.frm.pw.value==""){  
		alert("암호는 반드시 입력하여야 합니다");
		frm.pw.focus();
		return false;
	}else if( document.frm.pw.value != document.frm.pw_check.value){
		alert("암호가 일치하지 않습니다");
		frm.pw_check.focus();
		return false;
	} else {
		return true;
	}
}


function boardCheck(){
	if(document.frm.pw.value.length==0){
		alert("비밀번호를 적어주세요. 수정 삭제시 사용됩니다.");
		frm.pw.focus();
		return false;
	} else if(document.frm.title.value.length==0){
		alert("게시물의 제목을 적어주세요.");
		frm.title.focus();
		return false;
	} else if(document.frm.content.value==""){
		alert("내용을 입력해주세요.");
		frm.content.focus();
		return false;
	} else{
		return true;
	}
}

function reply_check(){
	if(document.frm2.reply.value.length==0){
		altert("댓글을 입력해주세요");
		frm2.reply.focus();
	} else {
		return ture;
	}
}

function open_win(url, name){
	window.open(url, name, "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, heihgt=230");
}
