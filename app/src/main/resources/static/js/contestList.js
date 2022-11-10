let noParam = "all";
let onoParam= "";
let sortParam = "";

// 첫번째 카테고리 검색
function selectNum() {
  // 셀렉트박스 옵션 선택한 값 가져오기
  noParam =  document.getElementById("noType").options[document.getElementById("noType").selectedIndex].value;
  window.location.href="/onemoa/admin/contest/contestList?" + "no=" + noParam;
}

// 두번째 카테고리 검색
function selectNum2() {
  // 셀렉트박스 선택되어있는 text 값 가져오기
  noParam = $("select[id=noType] option:selected").text();
  // 셀렉트박스 옵션 선택한 값 가져오기
  onoParam =  document.getElementById("onoType").options[document.getElementById("onoType").selectedIndex].value;
  window.location.href = "/onemoa/admin/contest/contestList?" + "no=" + noParam + "&ono=" + onoParam;
}

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

  noParam = $("select[id=noType] option:selected").text();
  onoParam = $("select[id=onoType] option:selected").text();
  if(onoParam == "기관명") {
    window.location.href = "/onemoa/admin/contest/contestList?" + "no=" +  noParam + sortCd;
  } else {
    window.location.href = "/onemoa/admin/contest/contestList?" + "no=" +  noParam + "&ono=" + onoParam + sortCd;
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

  noParam = $("select[id=noType] option:selected").text();
  onoParam = $("select[id=onoType] option:selected").text();
  if(onoParam == "기관명") {
    window.location.href = "/onemoa/admin/contest/contestList?" + "no=" +  noParam + sortEd;
  } else {
    window.location.href = "/onemoa/admin/contest/contestList?" + "no=" +  noParam + "&ono=" + onoParam + sortEd;
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

  noParam = $("select[id=noType] option:selected").text();
  onoParam = $("select[id=onoType] option:selected").text();
  if(onoParam == "기관명") {
    window.location.href = "/onemoa/admin/contest/contestList?" + "no=" +  noParam + sortVw;
  } else {
    window.location.href = "/onemoa/admin/contest/contestList?" + "no=" +  noParam + "&ono=" + onoParam + sortVw;
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

  noParam = $("select[id=noType] option:selected").text();
  onoParam = $("select[id=onoType] option:selected").text();
  if(onoParam == "기관명") {
    window.location.href = "/onemoa/admin/contest/contestList?" + "no=" +  noParam + rewardSort;
  } else {
    window.location.href = "/onemoa/admin/contest/contestList?" + "no=" +  noParam + "&ono=" + onoParam + rewardSort;
  }
}