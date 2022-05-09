function loginCheck(){
	if(document.loginFrm.id.value==""){
		alert("아이디를 입력하세요");
		document.loginFrm.id.focus();
		return false;
	}
	if(document.loginFrm.pwd.value==""){
		alert("비밀번호를 입력하세요");
		document.loginFrm.pws.focus();
		return false;
	}return true;
}

function go_next(){
	if(document.formm.okon[1].checked==true){
		alert("약관에 동의하셔야 회원가입이 가능합니다.");	
	}else{
		document.formm.action="joinForm";
		document.formm.submit();
	}
}

function idcheck(){
	if(document.formm.id.value==""){
		alert("아이디를 입력하세요");
		document.formm.id.focus();
		return;
	}
	var url = "idCheckForm?id="+document.formm.id.value;
	var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250";
	window.open(url,"IdCheck",opt)
}

function idok(userid){
	opener.formm.id.value=userid;
	opener.formm.reid.value=userid;
	self.close();
}

function post_zip(){
	var url = "findZipNum";
	var opt ="toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500";
	opt = opt + "height=300, top=300, left=300";
	window.open(url,"우편번호 찾기",opt);
}

function go_save(){
	if(document.formm.id.value==""){
		alert("아이디를 입력해주세요");
		document.formm.id.focus();
	}
	if(document.formm.reid.value!=document.formm.id.value){
		alert("아이디 중복확인을 하지 않았습니다");
		document.formm.id.focus();
	}
	if(document.formm.pwd.value==""){
		alert("비밀번호를 입력해주세요");
		document.formm.pwd.focus();
	}
	if(document.formm.pwd.value!=document.formm.pwdCheck.value){
		alert("비밀번호와 비밀번호 확인이 일치하지 않습니다");
		document.formm.pwd.focus();
	}
	if(document.formm.name.value==""){
		alert("이름을 입력해주세요");
		document.formm.name.focus();
	}
	if(document.formm.email.value==""){
		alert("이메일을 입력해주세요");
		document.formm.email.focus();
	} else{
		document.formm.action = "join";
		document.formm.submit();
	}
}

function go_update(){
	if(document.formm.pwd.value==""){
		alert("비밀번호를 입력해주세요");
		document.formm.pwd.focus();
	}
	if(document.formm.pwd.value!=document.formm.pwdCheck.value){
		alert("비밀번호와 비밀번호 확인이 일치하지 않습니다");
		document.formm.pwd.focus();
	}
	if(document.formm.name.value==""){
		alert("이름을 입력해주세요");
		document.formm.name.focus();
	}
	if(document.formm.email.value==""){
		alert("이메일을 입력해주세요");
		document.formm.email.focus();
	} else{
		document.formm.action = "memberUpdate";
		document.formm.submit();
	}
}

function find_id(){
	var url = "shop.do?command=findIdPwd";
	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=700";
	opt = opt + "height=500, top=300, left=300";
	window.open(url,"Find Id/Pw",opt);
}