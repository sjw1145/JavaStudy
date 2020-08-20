<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	var seccion = false;
	function inputCheck() {
		if (true) {
			checkId();
			if (seccion) {
				seccion = false;
				checkPass();
				if (seccion) {
					seccion = false;
					checkPasswd();
					if (seccion) {
						seccion = false;
						checkEmail();
						if (seccion) {
							seccion = false;
							checkName();
							if (seccion) {
								seccion = false;
								checkNumber();
								if (seccion) {
									seccion = false;
									checkHobby();
									if (seccion) {
										seccion = false;
										checkMyView();
										if (seccion) {
											alert("데이터를전송합니다.");
											return true;
										}
									}
								}
							}

						}
					}
				}
			}
		}
		return false;
	}
	function checkName() {

		if (mj.names.value == "") {
			alert("이름을 입력하지 않음.");
			mj.names.value = "";
			mj.names.focus();
			return seccion;
		}
		/* <!-- 정규식 ^ : 처음부터 [가-힣] {2글자~5글자} 마지막글자--> */
		var checkNames = /^[가-힣]{2,5}$/;

		if (!checkNames.test(mj.names.value)) {
			alert("한글만 입력해라 ...");
			mj.names.value = "";
			mj.names.focus();
			return seccion;
		} else {
			seccion = true;
			return seccion;
		}

	}
	function checkId() {
		if (mj.id.value == "") {
			alert("아이디를 입력하지 않음.");
			mj.id.value = "";
			mj.id.focus();
			return seccion;
		}
		var regExpId = /^[a-zA-Z0-9]{4,12}$/;
		if (!regExpId.test(mj.id.value)) {
			alert("4~12자의 영문 대소문자와 숫자로만 입력");
			mj.id.value = "";
			mj.id.focus();
			return seccion;
		} else {
			seccion = true;
			return seccion;
		}
	}
	function checkPass() {
		if (mj.passwd.value == "") {
			alert("비밀번호를 입력하지 않음.");
			mj.passwd.value = "";
			mj.passwd.focus();
			return seccion;
		}
		if (mj.id.value == mj.passwd.value) {
			alert("아이디와 비밀번호가 같다.");
			mj.id.value = "";
			mj.passwd.value = "";
			mj.id.focus();
			return seccion;
		} else {
			seccion = true;
			return seccion;
		}

		var regExpPassword = /^(?=.*[a-zA-Z])(?=.*[0-9]).{4,12}$/;

		if (check) {
			if (!regExpPassword.test(mj.passwd.value)) {
				alert("4~12자의 영문 대소문자와 숫자로만 입력");
				mj.passwd.value = "";
				mj.passwd.focus();
				return false;
			} else {
				seccion = true;
				return seccion;
			}
		}
	}
	function checkPasswd() {
		if (mj.passwdd.value == "") {
			alert("비밀번호확인을 입력하지 않음.");
			mj.passwdd.value = "";
			mj.passwdd.focus();
			return seccion;
		}
		if (mj.passwd.value != mj.passwdd.value) {
			alert("비밀번호가 서로 다르다");
			mj.passwd.value = "";
			mj.passwdd.value = "";
			mj.passwd.focus();
			return seccion;
		} else {
			seccion = true;
			return seccion;
		}
	}
	function checkEmail() {
		if (mj.email.value == "") {
			alert("이메일을 입력하지 않음.");
			mj.email.value = "";
			mj.email.focus();
			return seccion;
		}
		var regExpEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
		if (!regExpEmail.test(mj.email.value)) {
			alert("이메일 형식에 맞지 않습니다.<br>" + "다시 입력하세요");
			mj.email.value = "";
			mj.email.focus();
			return seccion;
		} else {
			seccion = true;
			return seccion;
		}
	}
	function checkNumber() {
		var num = mj.manNumber.value;
		if (mj.manNumber.value == "" || mj.manNumber.value.length < 13) {
			alert("주민번호를 제대로 입력하지 않음");
			mj.manNumber.value = "";
			mj.manNumber.focus();
			return seccion;
		}
		var checkNum = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))[1-4][0-9]{6}$/;
		var birthArr = new Array();
		var plus = 2;
		var sum = 0;
		for (var i = 0; i < 12; i++) {
			birthArr[i] = num.charAt(i);
			if (i >= 0 && i <= 7) {
				sum += num[i] * plus;
				plus++;
				if (i == 7)
					plus = 2;
			} else {
				sum += num[i] * plus;
				plus++;
			}
		}
		var result = 11 - (sum % 11) % 10;
		if ((!checkNum.test(mj.manNumber.value)) || (result != num[12])) {
			alert("주민번호 형식이 맞지 않음.");
			mj.manNumber.value = "";
			mj.manNumber.focus();
			return seccion;
		} else {
			seccion = true;
			return seccion;
		}
	}
	function checkHobby() {/*
		var arr_check = document.getElementsByName("hobby");
		var checkData = false;
		
		for(var i=0;i<arr_check.length;i++)
		{
	            if(arr_check[i].checked == true)
				{
					checkData = true;
					break;
	            }
	    }
		if(checkData == false)
		{
			alert("관심분야 체크안됨");
			return false;
		}
		else
		{
			seccion = true;
			return seccion;
		}
	 */
		seccion = true;
		return seccion;
	}
	function checkMyView() {

		var arr_text = document.getElementById("myView");
		var whatData = arr_text;

		if (arr_text.value == "") {
			alert("자기소개 좀 더 입력해라 ...");
			mj.myView.focus();
			return seccion;
		} else {
			seccion = true;
			return seccion;
		}
	}
</script>

<title>Insert title here</title>
</head>
<body>
	<form name="mj" onsubmit="return inputCheck()" action="JoinProcess.do"
		method="post">
		<table border=1 cellpadding=1 cellspacing=0 align=center>
			<th colspan=2 bgcolor="#999999">회원기본정보</th>
			<tr>
				<td>아이디</td>
				<td><input type="text" name=id maxlength=12>4~12자의 영문
					대소문자와 숫자로만 입력</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name=passwd maxlength=12>4~12자의
					영문 대소문자와 숫자로만 입력</td>
			</tr>

			<tr>
				<td>비밀번호확인</td>
				<td><input type="password" name=passwdd maxlength=12></td>
			</tr>

			<tr>
				<td>메일주소</td>
				<td><input type="text" name=email size=20>예)
					id@domain.com</td>
			</tr>

			<tr>
				<td>이름</td>
				<td><input type="text" name=names size=20></td>
			</tr>

			<th colspan=2 bgcolor="#999999">개인 신상 정보</th>
			<tr>
				<td>주민등록번호</td>
				<td><input type="password" name=manNumber size=20 maxlength=13>예)
					1234561234567</td>
			</tr>
			<tr>
				<td>관심분야</td>
				<td><input type="checkbox" name="hobby1" value="컴퓨터">컴퓨터&nbsp
					<input type="checkbox" name="hobby2" value="인터넷">인터넷&nbsp
					<input type="checkbox" name="hobby3" value="여행">여행&nbsp <input
					type="checkbox" name="hobby4" value="영화감상">영화감상&nbsp <input
					type="checkbox" name="hobby5" value="음악감상">음악감상&nbsp</td>
			</tr>
			<tr>
				<td>자기소개</td>
				<td><textarea cols="50" rows="5" id=myView name=myView></textarea></td>
			</tr>

		</table>
		<br>
		<center>
			<input type="submit" value="전송"> <input type="reset"
				value="다시입력">
		</center>
	</form>

</body>
</html>