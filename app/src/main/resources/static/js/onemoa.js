/* 메인페이지 후기 돌아가는 코드 */
var gall  = setInterval(galleryFun, 2000);
var inter = true;
var idx = 2;

function galleryFun(){

  $(".gallery ul").animate({
    "left":-300*idx+"px"
  },300);
  $(".g_item ul li").eq(idx-1).addClass("on").siblings().removeClass("on");
  idx++;
  if(idx> $(".gallery ul li").length-3){
    $(".gallery ul").animate({
      "left":0
    },0);
    idx=0;   
  }
}

$(".gallery , .g_item").hover(function(){
  if(inter==true){
    clearInterval(gall);
    inter=false;
  }
},function(){
  if(inter==false){
    gall  = setInterval(galleryFun, 2000);
    inter=true;
  }
});

$(".g_item ul li").on('click',function(){
  $(this).addClass("on").siblings().removeClass("on");
  idx = $(this).index()+1;
  $(".gallery ul").animate({
    "left":-300*idx+"px"
  },1000);
});


/* contest */

function team(){
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
    $('.modal5').show();
    $('.modal6').show();
  } else{
    $('.modal5').show();
    $('.modal6').hide();
    $('.modal7').hide();
  }
}
function clo5(){
  if ($('.modal6').css('display') == 'show'){
    $('.modal6').hide();
    $('.modal5').show();
  } else{
    $('.modal6').hide();
    $('.modal5').show();
  }
}

function dis6(){
  if ($('.modal7').css('display') == 'none'){
    $('.modal6').show();
    $('.modal7').show();
  } else{
    $('.modal7').hide();
  }
}
function clo6(){
  if ($('.modal7').css('display') == 'show'){
    $('.modal7').hide();
    $('.modal6').show();
  } else{
    $('.modal7').hide();
    $('.modal6').show();
  }
}

function dis7(){
  if ($('.modal8').css('display') == 'none'){
    $('.modal5').hide();
    $('.modal8').show();
  } else{
    $('.modal8').hide();
  }
}
function clo7(){
  if ($('.modal8').css('display') == 'show'){
    $('.modal8').hide();
    $('.modal5').show();
  } else{
    $('.modal8').hide();
    $('.modal5').show();
  }
}

function dis8(){
  if ($('.modal9').css('display') == 'none'){
    $('.modal8').show();
    $('.modal9').show();
  } else{
    $('.modal8').hide();
    $('.modal9').hide();
    $('.modal5').show();
  }
}
function clo8(){
  if ($('.modal9').css('display') == 'show'){
    $('.modal9').hide();
    $('.modal8').show();
  } else{
    $('.modal9').hide();
    $('.modal8').show();
  }
}
