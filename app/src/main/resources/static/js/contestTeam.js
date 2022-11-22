let memberNo = "";
let readerNumber = 0; // 팀장 mno
let teamNumber; //

// 공모전 상세페이지
$(".con").click(function () {
  contestNumber = $(this).attr("id"); // 공모전 번호
  let contestTeam = $(this).attr("value"); // 공모전 팀여부(true/false)
  console.log(contestTeam);
  // 공모전 개인전(false=0)이라면 상세보기 '팀원구해요' 태그 닫기
  if(contestTeam == "개인전") {
    document.querySelector("#contestTypeTeam").style.display = "none";
  }
  // 공모전 팀전(true=1)이라면 상세보기 '팀원구해요' 태그 열기
  if(contestTeam == "팀전") {
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

  // 리로드???
  window.location.reload();
}

// 공모전 팀원구해요 : 팀원 모집하기 버튼 -> 팀원 모집하기 폼
function dis2(){
  console.log(contestNumber)// 공모전 번호;

  $.ajax({
    type: "POST",
    url: "/onemoa/contest/contestTeam/teamRecruitForm",
    success: function (result) {
      console.log("팀원구해요");
      if(result.no == undefined){
        swal({
          title: "로그인이 필요한 화면입니다.",
          text: "로그인 창으로 이동합니다.",
          icon: "error",
          closeOnClickOutside : false}).then(() =>{
          window.location.href = "/onemoa/pageLogin";
        });
      }
      else {
        memberNo = result.no;
        $("#teamRecruitFormReaderNo").attr("value", result.no)
        $("#teamRecruitFormReaderProfile").attr("src", "/onemoa/member/files/" + result.profile);
        $("#teamRecruitFormReaderNickname").html(result.nickname);
        teamRecruitForm2();
      }
    },
  });

  if ($('.modal3').css('display') == 'none'){
    $('.modal3').show(); // 팀원모집하기 모달
    $('.modal2').hide(); // 공모전 팀원구해요 창(팀 팀장 리스트)
  } else{
    $('.modal3').hide();
    $('.modal2').hide();
  }
}

function teamRecruitForm2() {
  $.ajax({
    type: "POST",
    url: "/onemoa/contest/contestTeam/teamRecruitForm2",
    success: function (result2) {
      console.log(result2);
      for (let i = 0; i < result2.length; i++) {
        $("#xx-portfolioBox").append("<option value='" + result2[i].ptNo  + "'>" + result2[i].title + "</option>");
      }
    },
  });
}

function portfolioBoxChange() {
  let selectPortfolioNumber = document.getElementById("xx-portfolioBox").options[document.getElementById("xx-portfolioBox").selectedIndex].value
  let selectPortfolioText = document.getElementById("xx-portfolioBox").options[document.getElementById("xx-portfolioBox").selectedIndex].text;
  let aList = "";
  aList += "<li value='ptNo" + selectPortfolioNumber + "'>" +
      "<a href='/onemoa/mypage/firstportfolio?ptNo=" + selectPortfolioNumber + "'" + "onClick=\"window.open(this.href, '', 'width=1000px, height=1080px')\"; target=\"_blank\">" + selectPortfolioText + "</a>" + "&nbsp&nbsp&nbsp" + "<span class='portfolioDeleteBtn' id='ptNo" + selectPortfolioNumber + "'" + " style='cursor: pointer'>삭제</span>" + "</li>";
  $("#innerPortfolio").append(aList);

  $(".portfolioDeleteBtn").click(function (e) {
    e.preventDefault();
    $(this).parent().remove();
    $(this).remove();
  });
}

function personnelSelect() {
  let selectRecruNumber = document.getElementById("xx-recru").options[document.getElementById("xx-recru").selectedIndex].value;
  let selectRecruText = document.getElementById("xx-recru").options[document.getElementById("xx-recru").selectedIndex].text;
  let selectPersonnelNumber = document.getElementById("xx-personnel").options[document.getElementById("xx-personnel").selectedIndex].value;
  let selectPersonnelText = document.getElementById("xx-personnel").options[document.getElementById("xx-personnel").selectedIndex].text;
  let bList = "";
  bList += "<li>"
      + "<span id='" + selectRecruNumber + "'>" + selectRecruText + "</span>"
      + "<span id='" + selectPersonnelNumber + "'>" + selectPersonnelText + "</span>"
      +"</li>";
  $("#innerRecruitment").append(bList);
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
  console.log()
  if ($('.modal4').css('display') == 'none'){
    $('.modal3').show();
    $('.modal4').show();
  } else{
    $('.modal4').hide();
  }
}

function closeTest() {
  if ($('.modal6').css('display') == 'show'){
    $('.modal6').hide();
    $('.modal5').show();
  } else{
    $('.modal6').hide();
    $('.modal5').show();
  }
}

function clo6(){
  $('.modal7').hide();
}


// 팀원 모집하기 등록 버튼
function leaderjoin() {
  let textArea = $("#teamRecruitFormReaderIntroduction").val();
  let portfolios = [];
  let recruitments = [];

  let aLength = $("#innerPortfolio").find("a").length;
  let aeq = "";
  for(let i=0; i<aLength; i++) {
    aeq = $("#innerPortfolio").find("a").eq(i);
    portfolios[i] = aeq.attr("href");
  }

  let sLength = $("#innerRecruitment").find("span").length;
  let seq = "";
  for(let i=0; i<sLength; i++){
    seq = $("#innerRecruitment").find("span").eq(i);
    recruitments[i] = seq.attr("id");
  }

  $.ajax({
    type: "POST",
    url: "/onemoa/contest/contestTeam/teamRecruit",
    data: {
      "contestNumber": contestNumber, // 공모전 번호;
      "memberNo": memberNo, // 팀장 번호
      "textArea": textArea, // 팀장 소개글
      "portfolios": portfolios, // 팀장 포트폴리오
      "recruitments": recruitments, // 모집분류
    },
    statusCode: {
      500: function (result) {
        swal({
          title: "이미 등록하신 공모전입니다.",
          icon: "error",
          closeOnClickOutside : false}).then(() =>{
          window.location.href = "/onemoa/";
        });
      },
    },
    success: function() {
      if ($('.modal2').css('display') == 'none'){
        $('.modal2').show();
        $('.modal3').hide();
        $('.modal4').hide();
        body.style.overflow = 'hidden';
      } else{
        $('.modal2').hide();
        body.style.overflow = 'auto';
      }
    },
    error: function (result) {
      swal({
        title: "이미 등록하신 공모전입니다.",
        icon: "error",
        closeOnClickOutside : false}).then(() =>{
        window.location.href = "/onemoa/";
      });
    }
  });
};

function dis(){
  leaderjoin();

  if ($('.modal2').css('display') == 'none'){
    $('.modal2').show();
    $('.modal3').hide();
    $('.modal4').hide();
    body.style.overflow = 'hidden';
  } else{
    $('.modal2').hide();
    body.style.overflow = 'auto';
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

// 팀장 상세보기
function dis4(clicked_id) {
  memberNo = clicked_id;
  teamReaderDetail();

  // modal5 팀장상세보기
  if ($('.modal5').css('display') == 'none'){
    $('.modal2').show();
    $('.modal5').show();
  } else{
    $('.modal2').show();
    $('.modal5').hide();
  }
}


function teamReaderDetail() {
  // 팀장 정보 정보 요청
  $.ajax({
    type: "POST",
    url: "/onemoa/contest/contestTeam/readerDetail",
    data: {
      "contestNumber": contestNumber,
      "memberNumber": memberNo,
    },
    success: function (result) {
      console.log("팀장 정보 정보 요청");
      console.log(result);
      teamNumber = result.tno; // 팀번호
      let teamCont = result.cont; // 팀장 소개글
      readerNumber = result.reader.no; // 팀장 번호
      let readerNickame = result.reader.nickname; // 팀장 닉네임
      let readerProfile = result.reader.profile;
      let readerPortfoliosList = "";
      for (let i = 0; i < result.contestTeamPortfolios.length; i++) {
        readerPortfoliosList += "<li>" +
            "<a href='" + result.contestTeamPortfolios[i].fpath + "'" + "onClick=\"window.open(this.href, '', 'width=1000px, height=1080px')\"; target=\"_blank\">" + "http://onemoa.com" + result.contestTeamPortfolios[i].fpath +"</li>"
      }

      $("#xx-readerProfile").attr("src", "/onemoa/member/files/" + readerProfile);
      $("#xx-readerNickname1").text(readerNickame + "님 상세정보");
      $("#xx-readerNickname2").text(readerNickame);
      $("#xx-readerContent").text(teamCont);
      $("#xx-readerPortfolios").html(readerPortfoliosList);

      // 팀장이 등록한 팀 분류 정보 요청
      $.ajax({
        type: "POST",
        url: "/onemoa/contest/contestTeam/readerField",
        data: {"teamNumber": teamNumber},
        success: function (result2) {
          console.log("팀장이 등록한 팀 분류 정보 요청")
          console.log(result2);
          let fieldHead = "";
          let fieldList = "";
          let fieldSize = 0;
          for (let i = 0; i < result2.length; i++) {
            fieldList += "<span>" + result2[i].name + "</span>"
                + "<span>" + result2[i].size + "</span>"
            fieldSize += Number(result2[i].size);
          }
          fieldHead = "<li>" +  "모집인원 총" + fieldSize + "명 " + fieldList + "</li>" + "<button class=\"tmm\" onclick=\"dis5()\">팀원 지원하기</button>"
          $("#xx-readerField").html(fieldHead);
        },
      });
      fieldMemberList();
    },
  });
}

function fieldMemberList() {
  // 팀원 지원자 모든 리스트
  $.ajax({
    type: "POST",
    url: "/onemoa/contest/contestTeam/fieldList",
    data: {"teamNumber": teamNumber},
    success: function (result3) {
      console.log("팀원 지원자 모든 리스트");
      console.log(result3);
      console.log(readerNumber);
      console.log($("#uNumber").val());
      let uNumber = $("#uNumber").val();
      console.log(uNumber != readerNumber);
      let fieldMember = "";
      for (let i = 0; i < result3.length; i++) {
        for (let j = 0; j < result3[i].contestTeamFieldMembers.length; j++) {
          let type = result3[i].contestTeamFieldMembers[j].type;
          if(uNumber != readerNumber){
            console.log("일반 사용자");
            fieldMember +=
                "<ul style='width: 100%; height: 150px;'>" +
                "<li style='float:left; width: 20%;'>" +
                "<img src='/onemoa/member/files/" + result3[i].contestTeamFieldMembers[j].applicant.profile + "'style='width: 50%; margin-left: 20%;border-radius: 70%;overflow: hidden;'>" +
                "</li>" +
                "<li>" +
                "<p>" + result3[i].contestTeamFieldMembers[j].cont + "</p>" +
                // "<p><a href=\"#\">수정</a><a href=\"#\">삭제</a></p>" +
                "</li>" +
                "<li>" +
                "<p>" +
                "<a href='#' id='tfmno-" + result3[i].contestTeamFieldMembers[j].tfmno + "' onclick='dis7(this.id, this.text)'>지원자보기</a>" +
                "</p></li></ul>";
          }
          else if (type == "미승인") {
            console.log("if");
            console.log(type);
            fieldMember +=
                "<ul style='width: 100%; height: 150px;'>" +
                "<li style='float:left; width: 20%;'>" +
                "<img src='/onemoa/member/files/" + result3[i].contestTeamFieldMembers[j].applicant.profile + "'style='width: 50%; margin-left: 20%;border-radius: 70%;overflow: hidden;'>" +
                "</li>" +
                "<li>" +
                "<p>" + result3[i].contestTeamFieldMembers[j].cont + "</p>" +
                // "<p><a href=\"#\">수정</a><a href=\"#\">삭제</a></p>" +
                "</li>" +
                "<li>" +
                "<p>" +
                "<a href='#' id='tfmno-" + result3[i].contestTeamFieldMembers[j].tfmno + "' onclick='dis7(this.id, this.text)'>지원자보기</a>" +
                "</p></li></ul>";
          } else {
            console.log("else");
            console.log(type);
            fieldMember +=
                "<ul style='width: 100%; height: 150px;'>" +
                "<li style='float:left; width: 20%;'>" +
                "<img src='/onemoa/member/files/" + result3[i].contestTeamFieldMembers[j].applicant.profile + "'style='width: 50%; margin-left: 20%;border-radius: 70%;overflow: hidden;'>" +
                "</li>" +
                "<li>" +
                "<p>" + result3[i].contestTeamFieldMembers[j].cont + "</p>" +
                // "<p><a href=\"#\">수정</a><a href=\"#\">삭제</a></p>" +
                "</li>" +
                "<li>" +
                "<p style='border:#6F9475; background-color:#6F9475; color:#ffffff'>" +
                "<a href='#' id='tfmno-" + result3[i].contestTeamFieldMembers[j].tfmno + "' onclick='dis9(this.id, this.text)'>팀원취소</a>" +
                "</p></li></ul>";
          }
        }
      }
      $("#xx-fieldMemberUl").html(fieldMember);
    },
  });
}

// modal8 팀원 상세보기(지원자보기) 버튼
function dis7(clicked_id) {
  if ($(".modal8").css("display") == "none") {
    $(".modal5").hide();
    $(".modal8").show();
  } else {
    $(".modal8").hide();
  }

  let fieldMemberNumber = clicked_id;
  console.log(fieldMemberNumber);
  fieldMemberNumber = Number(fieldMemberNumber.substring(6));
  console.log(fieldMemberNumber);
  fieldMemberDetailView(fieldMemberNumber)

  console.log(readerNumber);
  console.log($("#uNumber").val());
  let uNumber = $("#uNumber").val();
  console.log(uNumber == readerNumber);
  let temp = uNumber == readerNumber;
  if (temp == true) {
    console.log("if")
    $("#teammate8").html(
        "<button id=\"tm8\" class=\"tm8\" onclick=\"dis8()\">팀원 채택하기</button>\n"
        + "<button class=\"tm8\" onclick=\"clo7()\">뒤로가기</button>"
    )
  } else if (temp == false) {
    console.log("else if")
    $("#teammate8").html(
        "<button class=\"tm8\" onclick=\"clo7()\">뒤로가기</button>"
    )
  }
}

// modal8 팀원 상세보기(지원자보기) 닫기 & 뒤로가기 버튼 -> 팀장 상세보기
function clo7(){
  if ($('.modal8').css('display') == 'show'){
    $('.modal8').hide();
    $('.modal5').show();
  } else{
    $('.modal8').hide();
    $('.modal5').show();
  }
}

// 지원자 데이터 상세 조회
// 공모전 팀원 상세보기(지원자보기)
function fieldMemberDetailView(fieldMemberNumber) {
  $.ajax({
    type: "POST",
    url: "/onemoa/contest/contestTeam/fieldMemberDetailView",
    data: {"fmNumber": fieldMemberNumber},
    success: function (result) {
      console.log(result);
      console.log(result.teamField.name);
      console.log(result.applicant.nickname);
      let fieldMemberDetailHeader = result.teamField.name + "을 지원한 " + result.applicant.nickname +"님 입니다.";
      $("#xx-fieldMemberDetailHeader").html(fieldMemberDetailHeader);
      $("#xx-fieldMemberDetailProfile").attr("src", "/onemoa/member/files/" + result.applicant.profile);
      $("#xx-fieldMemberDetailNickname2").text(result.applicant.nickname);
      $("#xx-fieldMemberDetailContent").text(result.cont);
      $("#teamb9").attr("value", result.tfmno);
      let portfolioLength = result.contestTeamFieldMemberPortfolioList.length;
      let liList = "";
      for (let i = 0; i < portfolioLength; i++) {
        liList += "<li><a href='" + result.contestTeamFieldMemberPortfolioList[i].fpath + "'"
            + "onClick=\"window.open(this.href, '', 'width=1000px, height=1080px')\"; target=\"_blank\">"
            + "https://onemoa.com" + result.contestTeamFieldMemberPortfolioList[i].fpath + "</a></li>"
      }
      $("#xx-fieldMemberDetailPortfolios2").html(liList);
    },
  });
}

// 팀원 채택하기 버튼
function dis8(clicked_value){
  let choiceNumber = clicked_value;
  console.log(choiceNumber)
  if ($('.modal9').css('display') == 'none'){
    $('.modal8').show();
    $('.modal9').show();
  } else{
    $('.modal8').hide();
    $('.modal9').hide();
    $('.modal5').show();
  }

  $.ajax({
    type: "POST",
    url: "/onemoa/contest/contestTeam/fieldMemberChoice",
    data: {"fmNo": choiceNumber, "reNumber":readerNumber},
    success: function(result){
      console.log(result);
      fieldMemberList();
    }
  });
}

// 팀원 채택하기 취소 버튼
function clo8(){
  if ($('.modal9').css('display') == 'show'){
    $('.modal9').hide();
    $('.modal8').show();
  } else{
    $('.modal9').hide();
    $('.modal8').show();
  }
}

// 팀원취소 버튼
function dis9(clicked_id, text) {
  let fieldMemberNumber = clicked_id;
  let ccc = text;
  console.log(fieldMemberNumber);
  console.log(ccc);
  fieldMemberNumber = Number(fieldMemberNumber.substring(6));
  console.log(fieldMemberNumber);
  fieldMemberDetailView(fieldMemberNumber)

  $.ajax({
    type: "POST",
    url: "/onemoa/contest/contestTeam/fieldMemberChoice",
    data: {"fmNo": fieldMemberNumber, "cType": ccc, "reNumber":readerNumber},
    statusCode: {
      500: function (result) {
        swal({
          title: "잘못된 요청입니다.",
          icon: "error",
          closeOnClickOutside : false}).then(() =>{
          window.location.href = "/onemoa/";
        });
      },
    },
    success: function (result) {
      console.log("팀원 취소완료")
      console.log(result);
      fieldMemberList();
    },
  });
}

// modal5 팀장상세보기 닫기 버튼
function clo4(){
  if ($('.modal5').css('display') == 'show'){
    $('.modal5').hide();
    $('.modal2').show();
  } else{
    $('.modal5').hide();
    $('.modal2').show();
  }
}

// 팀장 상세보기에서 팀원 모집하기 버튼
function dis5(){
  teamReaderDetail(); // 팀장 상세보기 리로드
  fieldMemberDetail();
  fieldMemberDetailField();

  if ($('.modal6').css('display') == 'none'){
    $('.modal5').show(); // 팀원 모집확인 모달 창 -> 확인 필요 -> 확인완료
    $('.modal6').show(); // 팀원 지원하기 모달 창
  } else{
     $('.modal5').show();
    $('.modal6').hide();
    $('.modal7').hide();
  }
}

// 팀원지원하기 뒤로가기 버튼
function clob(){
  if ($('.modal6').css('display') == 'show'){
    $('.modal6').hide();
    $('.modal5').show();
  } else{
    $('.modal6').hide();
    $('.modal5').show();
  }
}

// 지원자 회원 정보 조회
function fieldMemberDetail(){
  $.ajax({
    type: "POST",
    url: "/onemoa/contest/contestTeam/fieldMemberDetail",
    data: {"readerNumber": readerNumber},
    success: function (result) {
      console.log("지원자 회원 정보 조회")
      console.log(result);
      if (result == '') {
        swal({
          title: "로그인이 필요한 화면입니다.",
          text: "로그인 창으로 이동합니다.",
          icon: "error",
          closeOnClickOutside : false}).then(() =>{
          window.location.href = "/onemoa/pageLogin";
        });
      }
      let fieldMemberNumber = result[0].no;
      let fieldMemberNickname = result[0].nickname;
      let fieldMemberProfile = result[0].profile;
      let optionList = "";

      $("#xx-fieldMemberProfile").attr("src","/onemoa/member/files/" + fieldMemberProfile);
      $("#xx-fieldMemberNickname").html(fieldMemberNickname);
      for (let i = 0; i < result[0].portfoliosList.length; i++) {
        // $("#xx-fieldMemberPortfolioSelectBox").append("<option value='" + result[0].portfoliosList[i].ptNo  + "'>" + result[0].portfoliosList[i].title + "</option>");
        optionList += "<option value='" + result[0].portfoliosList[i].ptNo + "'>" + result[0].portfoliosList[i].title + "</option>";
      }
      $("#xx-fieldMemberPortfolioSelectBox").html(optionList);
    },
  });
}
// 지원자페이지에서 지원분야 조회
function fieldMemberDetailField() {
  console.log("fieldMemberDetailField():" + teamNumber);
  $.ajax({
    type: "POST",
    url: "/onemoa/contest/contestTeam/readerField",
    data: {"teamNumber": teamNumber},
    success: function (result){
      console.log(result);
      let inputList = "";
      for (let i = 0; i < result.length; i++) {
        inputList += "<p><input type=\"checkbox\" value='" + result[i].tfno + "'" + ">" + result[i].name + "</p>"
      }
      $("#xx-innerRecruitment").html(inputList);
    }
  })
}


// modal6에서 팀원 지원하기 버튼
function dis6(){
  teamJoin();
  teamReaderDetail(); // 팀장 상세보기 모달창
  
  if ($('.modal7').css('display') == 'none'){
    $('.modal6').show();
    $('.modal7').show();
  } else{
    $('.modal7').hide();
  }

}

// 팀원 지원하기
function teamJoin() {
  let textArea = $("#xx-fieldMemberContent").val();
  let portfolios1 = [];
  let select_obj = "";
  let selectObj;

  let aLength = $("#innerPortfolio2").find("a").length;

  // 포트폴리오 select option 에서 선택한 값 가져오기
  for(let i=0; i<aLength; i++) {
    portfolios1[i] = $("#innerPortfolio2").find("a").eq(i).attr("href");
  }

  // 팀원분류 체크박스 체크된값 가져오기
  $('input[type="checkbox"]:checked').each(function (index) {
    if (index != 0) {
      select_obj += ', ';
    }
    select_obj += $(this).val();
  });

  console.log(textArea);
  console.log(portfolios1);
  selectObj = select_obj.split(",");
  console.log(selectObj);


  $.ajax({
    type: "POST",
    url: "/onemoa/contest/contestTeam/fieldMemberAdd",
    data: {
      "textArea": textArea,
      "portfolios1": portfolios1,
      "selectObj": selectObj,
    },
    success: function (result) {
      console.log("teamJoin()");
      console.log(result);
    }
  });
}

let selectPortfolioNumber1 = "";
let selectPortfolioText1 = "";
function fieldMemberDetailPortfolioBoxChange() {
  console.log("fieldMemberDetailPortfolioBoxChange()");
  selectPortfolioNumber1 = document.getElementById("xx-fieldMemberPortfolioSelectBox").options[document.getElementById("xx-fieldMemberPortfolioSelectBox").selectedIndex].value;
  selectPortfolioText1 = document.getElementById("xx-fieldMemberPortfolioSelectBox").options[document.getElementById("xx-fieldMemberPortfolioSelectBox").selectedIndex].text;
  let aList2 = "";
  aList2 += "<li style='margin-left: 1%; height: 30px;'>" + "<a href='/onemoa/mypage/firstportfolio?ptNo=" + selectPortfolioNumber1 + "'" + "onClick=\"window.open(this.href, '', 'width=1000px, height=1080px')\"; target=\"_blank\">" + selectPortfolioText1 + "</a>" + "&nbsp&nbsp&nbsp" + "<span class='portfolio2DeleteBtn'>삭제</span>" + "</li>";
  console.log(aList2);
  $("#innerPortfolio2").append(aList2);

  $(".portfolio2DeleteBtn").click(function (e) {
    e.preventDefault();
    $(this).parent().remove();
    $(this).remove();
  });
}

function clo5(){
    $('.modal7').hide();
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
      '<button name="minus" class="mi"><img src="/img/minus.png"></button>'+
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
      '<button name="minus" class="mi2"><img src="/img/minus.png"></button>'+
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
      // console.log(result)
      var listList = "";
      if (result.length > 0) {
        for (let i = 0; i < result.length; i++) {
          // console.log(result[i]);
          // console.log("팀목록 PK : " + result[i].tno);
          // console.log("팀장 회원번호 : " + result[i].reader.no);
          // console.log("팀장 닉네임 : " + result[i].reader.nickname);
          // console.log("팀장 프로필파일경로 : " + result[i].reader.profile);
          // listList += "<li class='teamJangDetail1'>" + "<a href='" + "#'" + "onclick='dis4()'" + "class='teamJangDetail2'" + "id='" + result[i].tno + "'" +  ">"
          listList += "<li onclick='dis4(this.id)'"  + "id='" + result[i].reader.no + "'" + ">"
              + "<img src='/onemoa/member/files/"
              + result[i].reader.profile + "'"
              + "onmouseover=\"this.src='../img/profile11.png'\""
              + "onmouseout=\"" + "this.src='/onemoa/member/files/"
              + result[i].reader.profile
              + "'"
              + "\" style=' width: 100%; height: auto; border-radius: 70%; overflow: hidden; '>"
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
      '<button name="minus2" class="mi2"><img src="/img/minus.png"></button>'+
      '</div>';

  var trHtml2 = $( "div[name=hhhhhh2]:last" ); //last를 사용하여 trStaff라는 명을 가진 마지막 태그 호출

  trHtml2.after(addStaffText2); //마지막 trStaff명 뒤에 붙인다.

});

// 팀원지원 포폴 삭제 버튼
$(document).on("click","button[name=minus2]",function(){
  var trHtml2 = $(this).parent();
  trHtml2.remove(); //tr 테그 삭제
});


// 페이지 필터 타입(대기업, 공공기관, 자영업자)
$(".orgType").click(function () {
  // 현재 noType 클래스의 value 속성값 가져온다(all, 개인전, 팀전)
  let no = $(".noType").attr("value");
  // 2차분류 대기업, 공공기관, 자영업자 버튼 클릭시 해당 value의 속성값을 가져온다.
  let ono = $(this).attr("value");
  console.log(ono);
  window.location.href="/onemoa/contest/contestTeam?no=" + no + "&ono=" + ono;
});

// 페이지 정렬(등록순, 마감임박순, 조회순, 상금순)
let createdSort = document.getElementById("sortCreatDate");
let endDateSort = document.getElementById("sortEndDate");
let viewCountSort = document.getElementById("sortViewCount");
let rewardSort = document.getElementById("sortReward");
let createdSortType = createdSort.getAttribute("data-type");
let endDateSortType = endDateSort.getAttribute("data-type");
let viewCountSortType = viewCountSort.getAttribute("data-type");
let rewardSortType = rewardSort.getAttribute("data-type");
let sortCdFlag = "등록순";
let sortEdFlag = "마감임박순";
let sortVwFlag = "조회순";
let sortRwFlag = "상금순";

if (createdSortType == "" || createdSortType == null) {
  createdSort.innerHTML = "등록순";
} else if (createdSortType == "desc") {
  createdSort.innerHTML = "등록순&#30";
} else {
  createdSort.innerHTML = "등록순&#31";
}

if (endDateSortType == "" || endDateSortType == null) {
  endDateSort.innerHTML = "마감임박순";
} else if (endDateSortType == "desc") {
  endDateSort.innerHTML = "마감임박순&#30";
} else {
  endDateSort.innerHTML = "마감임박순^";
}

if (viewCountSortType == "" || viewCountSortType == null) {
  viewCountSort.innerHTML = "조회순";
} else if (viewCountSortType == "desc") {
  viewCountSort.innerHTML = "조회순&#30";
} else {
  viewCountSort.innerHTML = "조회순&#31";
}

if (rewardSortType == "" || rewardSortType == null) {
  rewardSort.innerHTML = "상금순";
} else if (rewardSortType == "desc") {
  rewardSort.innerHTML = "상금순&#30";
} else {
  rewardSort.innerHTML = "상금순&#31";
}

document.querySelector("#sortCreatDate").onclick = (e) => {
  e.preventDefault();
  let sortCdFlag = "등록순";
  if (createdSortType == "" || createdSortType == null) {
    createdSortType = "desc";
    sortCdFlag += "V";
  } else if (createdSortType == "desc") {
    createdSortType = "asc";
    sortCdFlag += "^";
  } else {
    createdSortType = "";
    sortCdFlag += "";
  }
  e.target.setAttribute("data-type", createdSortType);
  e.target.innerHTML = sortCdFlag;

  let sortCd = ""
  if (createdSortType != "") {
    sortCd = "&sortCd=" + createdSortType;
  }

  noParam = $(".noType").attr("value");
  onoParam = $("#onoType").attr("value");
  if ($("#onoType").attr("value") == '') {
    window.location.href="/onemoa/contest/contestTeam?no=" + noParam + sortCd;
  } else {
    window.location.href="/onemoa/contest/contestTeam?no=" + noParam + "&ono=" + onoParam + sortCd;
  }
}

document.querySelector("#sortEndDate").onclick = (e) => {
  e.preventDefault();
  let sortEdFlag = "등록순";
  if (endDateSortType == "" || endDateSortType == null) {
    endDateSortType = "desc";
    sortEdFlag += "V";
  } else if (endDateSortType == "desc") {
    endDateSortType = "asc";
    sortEdFlag += "^";
  } else {
    endDateSortType = "";
    sortEdFlag += "";
  }
  e.target.setAttribute("data-type", endDateSortType);
  e.target.innerHTML = sortEdFlag;

  let sortEd = ""
  if (endDateSortType != "") {
    sortEd = "&sortEd=" + endDateSortType;
  }

  noParam = $(".noType").attr("value");
  onoParam = $("#onoType").attr("value");
  if ($("#onoType").attr("value") == '') {
    window.location.href="/onemoa/contest/contestTeam?no=" + noParam + sortEd;
  }  else {
    window.location.href="/onemoa/contest/contestTeam?no=" + noParam + "&ono=" + onoParam + sortEd;
  }
}

document.querySelector("#sortViewCount").onclick = (e) => {
  e.preventDefault();
  let sortVwFlag = "조회순";
  if (viewCountSortType == "" || viewCountSortType == null) {
    viewCountSortType = "desc";
    sortVwFlag += "V";
  } else if (viewCountSortType == "desc") {
    viewCountSortType = "asc";
    sortVwFlag += "^";
  } else {
    viewCountSortType = "";
    sortVwFlag += "";
  }
  e.target.setAttribute("data-type", viewCountSortType);
  e.target.innerHTML = sortVwFlag;

  let sortVw = ""
  if (viewCountSortType != "") {
    sortVw = "&sortVw=" + viewCountSortType;
  }

  noParam = $(".noType").attr("value");
  onoParam = $("#onoType").attr("value");
  if ($("#onoType").attr("value") == '') {
    window.location.href="/onemoa/contest/contestTeam?no=" + noParam + sortVw;
  } else {
    window.location.href="/onemoa/contest/contestTeam?no=" + noParam + "&ono=" + onoParam + sortVw;
  }
}

document.querySelector("#sortReward").onclick = (e) => {
  e.preventDefault();
  let sortRwFlag = "조회순";
  if (rewardSortType == "" || rewardSortType == null) {
    rewardSortType = "desc";
    sortRwFlag += "V";
  } else if (rewardSortType == "desc") {
    rewardSortType = "asc";
    sortRwFlag += "^";
  } else {
    rewardSortType = "";
    sortRwFlag += "";
  }
  e.target.setAttribute("data-type", rewardSortType);
  e.target.innerHTML = sortRwFlag;

  let rewardSort = ""
  if (rewardSortType != "") {
    rewardSort = "&sortRw=" + rewardSortType;
  }

  noParam = $(".noType").attr("value");
  onoParam = $("#onoType").attr("value");
  if ($("#onoType").attr("value") == '') {
    window.location.href="/onemoa/contest/contestTeam?no=" + noParam + rewardSort;
  } else {
    window.location.href="/onemoa/contest/contestTeam?no=" + noParam + "&ono=" + onoParam + rewardSort;
  }
}