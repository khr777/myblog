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

