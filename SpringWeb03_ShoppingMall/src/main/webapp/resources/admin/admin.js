function workerCheck() {
	if(document.frm.workId.value==""){
		alert("아이디를 입력하세요");
		return false;
	} else if(document.frm.workPwd.value==""){
		alert("비밀번호를 입력하세요");
		return false;
	} return true;
}