<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/headerfooter/header.jsp"%>

<%@ include file="/resources/sub01/sub_image.html"%>
<%@ include file="/resources/sub01/sub_menu.html"%>


<article>
<h2>Update Profile</h2>
<form method="post" name="formm" action="memberUpdate">
	<fieldset>
		<legend>Basic Info</legend>
		<label>User ID</label><input type="text" name="id" value="${member.id}" readonly><br>		
		<label>Password</label><input type="password" name="pwd"><br>	
		<label>Retype Password</label><input type="password" name="pwdCheck"><br>
		<label>Name</label><input type="text" name="name" value="${member.name}"><br>		
		<label>E-Mail</label><input type="text" name="email" value="${member.email}"><br>
	</fieldset>
	<fieldset>
		<legend>Optional</legend>
		<label>Zip Code</label><input type="text" name="zip_num" size="10" value="${member.zip_num}">
			<input type="button" value="주소 찾기" class="dup" onclick="post_zip()"><br>
		<label>Address</label><input type="text" name="addr1" size="50" value="${addr1}"><br>
		<label>&nbsp;</label><input type="text" name="addr2" size="25" value="${addr2}"><br>
		<label>Phone Number</label><input type="text" name="phone" value="${member.phone}"><br>		
	</fieldset><div class="clear"></div>
	<div id="buttons"><input type="button" value="정보수정" class="submit" onclick="go_update();">
		<input type="reset" value="취소" class="cancel"></div>
</form>
</article>
<%@ include file="/resources/headerfooter/footer.jsp"%>
