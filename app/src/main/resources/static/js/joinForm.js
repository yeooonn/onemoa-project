function sample6_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function(data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ""; // 주소 변수
      var extraAddr = ""; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if (data.userSelectedType === "R") {
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== "" && data.apartment === "Y") {
          extraAddr +=
              extraAddr !== "" ?
                  ", " + data.buildingName :
                  data.buildingName;
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if (extraAddr !== "") {
          extraAddr = " (" + extraAddr + ")";
        }
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById("sample6_postcode").value = data.zonecode;
      document.getElementById("sample6_address").value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById("sample6_detailAddress").focus();
    },
  }).open();
}

// 관심사선택
var expanded = false;

function showInterestCheckboxes() {
  var checkboxes = document.getElementById("interest-checkboxes");
  if (!expanded) {
    checkboxes.style.display = "block";
    expanded = true;
  } else {
    checkboxes.style.display = "none";
    expanded = false;
  }
}


// 회원가입시 닉네임중복체크와 이메일인증을 했는지 체크
var isnickname = false;
var isemailauth = false;

// 닉네임 중복검사
$(function() {
  $(".nickname_check_btn").click(function() {
    var nickname = $("#nickname").val();

    if (nickname == "") {
      document.getElementById("nicknameCheckBox").value = "닉네임을 입력해주세요.";
      document.getElementById("nicknameCheckBox").style.display = "block";
      document.getElementById("nicknameCheckBox").style.color = "red";
      document.getElementById("nickname").focus();
      return false;
    }

    $.ajax({
      type: "POST",
      url: "/onemoa/nicknamecheck",
      data: {
        nickname: nickname
      },
      success: function(result) {
        if (result == "false") {
          document.getElementById("nicknameCheckBox").value = "중복된 닉네임입니다.";
          document.getElementById("nicknameCheckBox").style.display = "block";
          document.getElementById("nicknameCheckBox").style.color = "red";
          document.getElementById("nickname").focus();
          isnickname = false;
          return false;
        } else {
          document.getElementById("nicknameCheckBox").value = "사용가능한 닉네임입니다.";
          document.getElementById("nicknameCheckBox").style.display = "block";
          document.getElementById("nicknameCheckBox").style.color="green";
          isnickname = true;
        }
      },
    });
  })
})

// 이메일 인증번호 처리
$(function() {
  var email_auth_cd = "";
  const regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;


  $(".email_auth_btn").click(function() {
    const email = $("#joinEmail").val();

    if (email == "") {
      document.getElementById("emailAuthCheckBox").style.display = "block";
      document.getElementById("emailAuthCheckBox").style.color = "red";
      document.getElementById("emailAuthCheckBox").value = "이메일을 입력해주세요.";
      return false;
    }

    if (!regExp.test(email)) {
      document.getElementById("emailAuthCheckBox").style.display = "block";
      document.getElementById("emailAuthCheckBox").style.color = "red";
      document.getElementById("emailAuthCheckBox").value = "이메일 형식이 올바르지 않습니다.";
      return false;
    }


    $.ajax({
      type: "POST",
      url: "/onemoa/emailauth",
      data: {
        email: email
      },
      success: function(data) {
        if (data == "false") {
          document.getElementById("emailAuthCheckBox").style.display = "block";
          document.getElementById("emailAuthCheckBox").style.color = "red";
          document.getElementById("emailAuthCheckBox").value = "유효하지 않는 이메일 입니다."
          return false;
        } else {
          document.getElementById("emailAuthCheckBox").style.display = "block";
          document.getElementById("emailAuthCheckBox").style.color = "green";
          document.getElementById("emailAuthCheckBox").value = "인증번호가 발송되었습니다."
          document.getElementById("email-auth-box").style.display = "block";
          email_auth_cd = data;
        }
      },
      error: function(data) {
        alert("메일 발송에 실패했습니다.");
      },
    });

    $("#email_auth_check_btn").click(function() {
      if ($("#email-auth-key").val() != email_auth_cd) {
        document.getElementById("emailAuthCheckBox").style.display = "block";
        document.getElementById("emailAuthCheckBox").style.color = "red";
        document.getElementById("emailAuthCheckBox").value = "인증번호가 일치하지 않습니다."
        return false;
      } else {
        document.getElementById("email_auth_btn").disabled = true;
        document.getElementById("email_auth_check_btn").disabled = true;
        document.getElementById("joinEmail").readOnly = true;
        document.getElementById("email-auth-key").readOnly = true;
        document.getElementById("email_auth_btn").style.color = "#83879a";
        document.getElementById("email_auth_btn").style.backgroundColor = "#a3a3a3";
        document.getElementById("email_auth_check_btn").style.color = "#83879a";
        document.getElementById("email_auth_check_btn").style.backgroundColor = "#a3a3a3";
        document.getElementById("emailAuthCheckBox").style.display = "block";
        document.getElementById("emailAuthCheckBox").style.color = "green";
        document.getElementById("emailAuthCheckBox").value = "인증되었습니다.";
        isemailauth = true;
      }
    });
  });
});


$(document).ready(function() {
  $("form").submit(function(e) {
    const pwd = $("#password").val();
    const pwdCk = $("#password-ck").val();
    const joinForm = document.getElementById("join_frm");
    const jobNoCheck = document.getElementById("jobNo");

    // const jobNo = $("")
    if (pwd != pwdCk) {
      document.getElementById("password-cked").value = "비밀번호가 일치하지 않습니다.";
      document.getElementById("password-cked").style.display = "block";
      document.getElementById("password").focus();
      return false;
    }
    if (pwd.length < 8) {
      document.getElementById("password-cked").value = "8자 이상으로 입력해주세요.";
      document.getElementById("password-cked").style.display = "block";
      document.getElementById("password").focus();
      return false;
    } else if (pwd.length > 8) {
      document.getElementById("password-cked").style.display = "none";
    }
    if (isnickname == false) {
      document.getElementById("nicknameCheckBox").value = "닉네임 중복검사가 필요합니다.";
      document.getElementById("nicknameCheckBox").style.display = "red";
      document.getElementById("nicknameCheckBox").style.display = "block";
      document.getElementById("nickname").focus();
      return false;
    }
    if (isemailauth == false) {
      document.getElementById("emailAuthCheckBox").value = "이메일 인증이 필요합니다.";
      document.getElementById("emailAuthCheckBox").style.color = "red";
      document.getElementById("emailAuthCheckBox").style.display = "block";
      document.getElementById("joinEmail").focus();
      return false;
    }
    if (jobNoCheck.options[jobNoCheck.selectedIndex].value == 0) {
      document.getElementById("jobNoCheckBox").value = "직업을 선택해 주세요.";
      document.getElementById("jobNoCheckBox").style.color = "red";
      document.getElementById("jobNoCheckBox").style.display = "block";
      return false;
    } else {
      joinForm.submit();
      return true;
    }
  });
});