// 로그인 ajax 비동기 처리
$(document).ready(function () {
  $(".loginbtn").click(function () {
    var email = $("#email").val();
    var password = $("#password").val();
    var saveEmail = $("input[type=checkbox][name=saveEmail]:checked").val();
    console.log(saveEmail);

    $.ajax({
      type: "POST",
      url: "/onemoa/login",
      data: { email: email, password: password, saveEmail: saveEmail},
      success: function (result) {
        if(result == "true") {
          window.location.reload();
        } else if (result == "false") {
          document.getElementById("emailPasswordCheck").style.display = "block";
          document.getElementById("emailPasswordCheck").value="이메일 또는 비밀번호가 일치하지 않습니다.";
          document.getElementById("emailPasswordCheck").style.color="red";
        }
      },
    });
  });


  $("#loginPage").click(() =>{
    var email = $("#emailPage").val();
    var password = $("#passwordPage").val();
    var saveEmail = $("input[type=checkbox][id=saveEmailPage]:checked").val();
    console.log(saveEmail);
    $.ajax({
      type: "POST",
      url: "/onemoa/loginPage",
      data: { email: email, password: password, saveEmail: saveEmail},
      success: function (result) {
        if(result == "true") {
          location.href="/onemoa/";
        } else if (result == "false") {
          document.getElementById("emailPasswordCheck").style.display = "block";
          document.getElementById("emailPasswordCheck").value="이메일 또는 비밀번호가 일치하지 않습니다.";
          document.getElementById("emailPasswordCheck").style.color="red";
        }
      },
    });
  });

  $(".btn-open-popup2").click(() => {
    location.href="/onemoa/member/mypage";
  });
});
