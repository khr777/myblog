
function MobileSideBar__init() {
  $(".btn-toggle-mobile-side-bar, .mobile-side-bar-bg").click(MobileSideBar__toggle);
  $(".mobile-side-bar ul>li").click(function(e) {
    if ( $(this).hasClass('active') ) {
      $(this).removeClass('active');
    }
    else {
      $(this).addClass('active');
    }
    e.stopPropagation();
  });
 
}


$ (function () {
  $('.sbs-slider > .side-bars > div').click(function() {
    // this 클릭 당사자
    var $clickedBtn = $(this);
    // closest => 조상중에서 가장 가까운 엘리먼트 하나 찾아주세요.
    var $slider = $clickedBtn.closest('.sbs-slider');
    
    //.index() => 형제번호
    var isLeft = $clickedBtn.index() == 0;
    
    // find => 지역탐색
    // $currnet => 현재 active 된 녀석
    var $currnet = $slider.find('.slides > .active');
    // next => 다음 형제 가져와
    var $post = null;
    
    if ( isLeft ) {
        $post = $currnet.prev();
    }
    else {
        $post = $currnet.next();
    }
    
    if ( $post.length == 0 ) {
        if ( isLeft ) {
            $post = $slider.find('.slides > div:last-child');
        }
        else {
            $post = $slider.find('.slides > div:first-child');
        }
    }
    
    $currnet.removeClass('active');
    $post.addClass('active');
    
  });
 
});

 






function MobileSideBar__init() {
    var $btnToggleSideTopBar = $('.btn-toggle-mobile-top-bar');

    $btnToggleSideTopBar.click(function() {
        if ($(this).hasClass('active')) {
            $(this).removeClass('active');
            $('.mobile-side-bar').removeClass('active');
        }
        else {
            $(this).addClass('active');
            $('.mobile-side-bar').addClass('active');
        }
    });
}

$(function() {
    MobileSideBar__init();
});

