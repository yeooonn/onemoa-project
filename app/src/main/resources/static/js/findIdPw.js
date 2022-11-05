$("#findByIdBtn").click(() => {
  let name = $("#findIdName").val();
  let email = $("#findIdEmail").val();

  $.ajax({
    type: "POST",
    url: "/onemoa/member/findById",
    data: {name: name, email, email},
    success: function (result) {
      $("#memberId").css("display", "block");
      $("#memberId").val(email);
    }
  });
});

$("#sendEmailBtn").click(() => {
  let name = $("#findPwdName").val();
  let email = $("#findPwdEmail").val();
  let emailCodeKey = ""
  $.ajax({
    type: "POST",
    url: "/onemoa/member/findByPwd",
    data: {name: name, email, email},
    success: function (result) {
      emailCodeKey = result;
    }
  });

  $("#findByPwdBtn").click(() => {
    if($("#findIdEmailCode").val() != emailCodeKey){
      alert("인증번호가 일치하지 않습니다.");
      return false;
    } else {
      $.ajax({
        type: "POST",
        url: "/onemoa/member/resetPassword",
        data: {name: name, email: email, emailCodeKey: emailCodeKey},
        success: function (result) {
          alert("패스워드 초기화 이메일이 발송되었습니다.");
          location.href="/onemoa/";
        },
      });
    };
  });
});