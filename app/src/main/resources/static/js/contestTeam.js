// 공모전 상세페이지
$(".con").click(function () {
  contestNumber = $(this).attr("id"); // 공모전 번호
  let contestTeam = $(this).attr("value"); // 공모전 팀여부(true/false)

  // 공모전 개인전(false=0)이라면 상세보기 '팀원구해요' 태그 닫기
  if(contestTeam == "false") {
    document.querySelector("#contestTypeTeam").style.display = "none";
  }
  // 공모전 팀전(true=1)이라면 상세보기 '팀원구해요' 태그 열기
  if(contestTeam == "true") {
    document.querySelector("#contestTypeTeam").style.display = "block";
  }

  if ($('.modal2').css('display') == 'none'){
    $('.modal2').show();
    $('.modal3').hide();
    $('.modal4').hide();
    body.style.overflow = 'hidden';

    // 공모전 상세보기 Ajax 비동기 통신
    $.ajax({
      type: "POST",
      url: "/onemoa/contest/contestTeam/detail",
      data: { contestNumber: contestNumber}, // 공모전 번호를 서버에 전달
      success: function (result) {
        console.log(result);
        $("#contestDetailContOrg").text(result.contOrg.orgName); // 공모전 주최기관이름(대기업,공공기관,자영업자)
        $("#contestDetailOrg").text(result.org); // 공모전 주최기관명
        $("#contestDetailOrg2").text(result.org); // 공모전 주최기관명
        $("#contestDetailCategory").text(result.contCategory.categoryName); // 공모전 카테고리이름
        $("#contestDetailTitle").text(result.title); // 공모전 제목
        $("#contestDetailAppl").text(result.appl); // 공모전 접수방법(이메일)
        $("#contestDetailQual").text(result.qual); // 공모전 참가자격
        $("#contestDetailReceptionPeriod").text(result.sdate + "~" + result.edate); // 공모전 시작일 ~ 종료일
        $("#contestDetailReward").text(result.reward + "만원"); // 공모전 상금
        $("#contestDetailPage").html("<a href='"+ result.page + "'>" + result.page +"</a>"); // 공모전 홈페이지
        $("#contestDetailCont").text(result.cont); // 공모전 상세내용
        // result.thumbNail값을 꺼내서 img의 src값 변경
        $("#contestDetailImage").attr("src", "/onemoa/contest/files/" + result.thumbNail); // 공모전 썸네일

        // 응답받은 result에서 contestAttachedFiles(첨부파일) 추출
        // spanList : 첨부파일을 span 태그로 만들어서 td 태그 밑에 삽입
        let arr = result.contestAttachedFiles;
        let spanList = "";
        if (arr.length != 0) {
          for (let i = 0; i < arr.length; i++) {
            spanList += "<a href='" + "/onemoa/contest/files/" + arr[i].filepath + "'>"
                + "<span>" + arr[i].fname + "</span>" + "</a>";
          }
          var td = document.getElementById("xNameList");
          td.innerHTML = spanList;
        }
      },
    });

  } else{
    $('.modal2').hide();
    body.style.overflow = 'auto';
  }
});

function clo(){
  if ($('.modal2').css('display') == 'show'){
    $('.modal2').hide();

  } else{
    $('.modal2').hide();
    body.style.overflow = 'auto';
  }
}

function dis2(){
  if ($('.modal3').css('display') == 'none'){
    $('.modal3').show();
    $('.modal2').hide();
  } else{
    $('.modal3').hide();
    $('.modal2').hide();
  }
}
function clo2(){
  if ($('.modal3').css('display') == 'show'){
    $('.modal3').hide();
    $('.modal2').show();
  } else{
    $('.modal3').hide();
    $('.modal2').show();
  }
}

function dis3(){
  if ($('.modal4').css('display') == 'none'){
    $('.modal3').show();
    $('.modal4').show();
  } else{
    $('.modal4').hide();
  }
}
function clo3(){
  if ($('.modal4').css('display') == 'show'){
    $('.modal4').hide();
    $('.modal3').show();
  } else{
    $('.modal4').hide();
    $('.modal3').show();
  }
}

function dis4(){
  if ($('.modal5').css('display') == 'none'){
    $('.modal2').show();
    $('.modal5').show();
  } else{
    $('.modal2').show();
    $('.modal5').hide();
  }
}
function clo4(){
  if ($('.modal5').css('display') == 'show'){
    $('.modal5').hide();
    $('.modal2').show();
  } else{
    $('.modal5').hide();
    $('.modal2').show();
  }
}

function dis5(){
  if ($('.modal6').css('display') == 'none'){
    $('.modal4').show();
    $('.modal6').show();
  } else{
    $('.modal4').show();
    $('.modal6').hide();
  }
}
function clo5(){
  if ($('.modal5').css('display') == 'show'){
    $('.modal5').hide();
    $('.modal2').show();
  } else{
    $('.modal5').hide();
    $('.modal2').show();
  }
}

// 포폴 추가 버튼
$(document).on("click","button[name=addStaff]",function(){

  var addStaffText =
      '<div name="hhhhhh" class="hhhhhh">'+
      '<select name="port" class="po">'+
      '<option value="포트폴리오를 선택해주세요.">포트폴리오를 선택해주세요.</option>'+
      '<option value="포트폴리오1">포트폴리오1</option>'+
      '<option value="포트폴리오2">포트폴리오2</option>'+
      '<option value="포트폴리오3">포트폴리오3</option>'+
      '</select>'+
      '<button name="minus" class="mi"><img src="../img/minus.png"></button>'+
      '</div>';

  var trHtml = $( "div[name=hhhhhh]:last" ); //last를 사용하여 trStaff라는 명을 가진 마지막 태그 호출

  trHtml.after(addStaffText); //마지막 trStaff명 뒤에 붙인다.

});

// 모집분야 추가 버튼
$(document).on("click","button[name=addStaff2]",function(){

  var addStaffText =
      '<div name="rrr" class="recruit">'+
      '<select name="recru" class="re">'+
      '<option value="모집분야" selected>모집분야를 선택해주세요.</option>'+
      '<option value="촐영">촐영</option>'+
      '<option value="편집">편집</option>'+
      '<option value="음향">음향</option>'+
      '</select>'+
      '<select name="personnel" class="re2">'+
      '<option value="인원" selected>인원</option>'+
      '<option value="1">1</option>'+
      '<option value="2">2</option>'+
      '<option value="3">3</option>'+
      '<option value="4">4</option>'+
      '<option value="5">5</option>'+
      '<option value="6">6</option>'+
      '</select>'+
      '<button name="minus" class="mi2"><img src="../img/minus.png"></button>'+
      '</div>';

  var trHtml = $( "div[name=rrr]:last" ); //last를 사용하여 trStaff라는 명을 가진 마지막 태그 호출

  trHtml.after(addStaffText); //마지막 trStaff명 뒤에 붙인다.

});

// 포폴 삭제 버튼
$(document).on("click","button[name=minus]",function(){
  var trHtml = $(this).parent();
  trHtml.remove(); //tr 테그 삭제
});


// 팀원구해요 목록
function team(){
  console.log(contestNumber);
  $.ajax({
    type: "POST",
    url: "/onemoa/contest/contestTeam/teamList",
    data: { contestNumber: contestNumber}, // 공모전 번호를 서버에 전달
    success: function (result) {
      console.log(result)
      var listList = "";
      if (result.length > 0) {
        for (let i = 0; i < result.length; i++) {
          console.log(result[i]);
          console.log("팀목록 PK : " + result[i].tno);
          console.log("팀장 회원번호 : " + result[i].reader.no);
          console.log("팀장 닉네임 : " + result[i].reader.nickname);
          console.log("팀장 프로필파일경로 : " + result[i].reader.profile);
          listList += "<li>" + "<a href='" + "#'" + "onclick=\"dis4()\"" + "id='" + result[i].tno + "'" +  ">"
              + "<img src='/onemoa/member/files/"
              + result[i].reader.profile + "'"
              + "onmouseover=\"this.src='../img/profile11.png'\""
              + "onmouseout=\"" + "this.src='/onemoa/member/files/"
              + result[i].reader.profile
              + "'"
              + "\">"
              + "</a>"
              + "<br>"
              + "<p>" + result[i].reader.nickname
              + "</p>"
              + "</br>"
              + "</li>";
        }
        $("#teamReaderList").html(listList);
      }
    }
  });

  if ($('.person').css('display') == 'none'){
    $('.person').show();
    $('.contestIn').hide();
    $('.contestdtype p').eq(1).css('color', '#000');
    $('.contestdtype p').eq(1).css('border-bottom', '3px solid #000');
    $('.contestdtype p').eq(1).css('padding-bottom', '7px');
    $('.contestdtype p').eq(0).css('color', '#a5a5a5');
    $('.contestdtype p').eq(0).css('border-bottom', 'none');
  } else{
    $('.person').hide();
    $('.contestIn').show();
    $('.contestdtype p').eq(0).css('color', '#000');
    $('.contestdtype p').eq(0).css('border-bottom', '3px solid #000');
    $('.contestdtype p').eq(0).css('padding-bottom', '7px');
    $('.contestdtype p').eq(1).css('color', '#a5a5a5');
    $('.contestdtype p').eq(1).css('border-bottom', 'none');
  }
}

// 팀원지원 포폴 추가 버튼
$(document).on("click","button[name=addStaff3]",function(){

  var addStaffText2 =
      '<div name="hhhhhh2" class="hhhhhh2">'+
      '<select name="port2" class="po2">'+
      '<option value="포트폴리오를 선택해주세요.">포트폴리오를 선택해주세요.</option>'+
      '<option value="포트폴리오1">포트폴리오1</option>'+
      '<option value="포트폴리오2">포트폴리오2</option>'+
      '<option value="포트폴리오3">포트폴리오3</option>'+
      '</select>'+
      '<button name="minus2" class="mi2"><img src="../img/minus.png"></button>'+
      '</div>';

  var trHtml2 = $( "div[name=hhhhhh2]:last" ); //last를 사용하여 trStaff라는 명을 가진 마지막 태그 호출

  trHtml2.after(addStaffText2); //마지막 trStaff명 뒤에 붙인다.

});

// 팀원지원 포폴 삭제 버튼
$(document).on("click","button[name=minus2]",function(){
  var trHtml2 = $(this).parent();
  trHtml2.remove(); //tr 테그 삭제
});