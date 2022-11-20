function show() {
    document.querySelector(".background").className = "background show";
}

function close() {
    document.querySelector(".background").className = "background";
}

document.querySelector("#show").addEventListener("click", show);
document.querySelector("#close").addEventListener("click", close);


$("#review-add").click(function() {/*setTimeout(function() {*/
    // $('#review-add-form').submit(); // 자식창에서 submit()
    opener.location.reload(); // parent window => reload (list 새로고침)
    window.close(); // child window => close
});

$("#review-add1").click(function() {
    setTimeout(function() {
        $('#review-add-form1').submit();
        window.close();
    }, 1000);
    opener.parent.location.reload();
});

const drawStar = (target) => {
    document.querySelector(`.star span`).style.width = `${target.value * 10}%`;
    // string 으로 나감
}

const drawStar1 = (target) => {
    document.querySelector(`.star1 span`).style.width = `${target.value * 10}%`;
    // string 으로 나감
}

    //
    // // 탭 바꾸는 js
    // $('#myTabs a').click(function (e) {
//   e.preventDefault()
//   $(this).tab('show')
// })
//
// $('#myTabs a[href="#profile"]').tab('show') // Select tab by name
// $('#myTabs a:first').tab('show') // Select first tab
// $('#myTabs a:last').tab('show') // Select last tab
// $('#myTabs li:eq(2) a').tab('show') // Select third tab (0-indexed)