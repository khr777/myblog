

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


//유튜브 플러그인 시작
function youtubePlugin() {
toastui.Editor.codeBlockManager.setReplacer("youtube", function (youtubeId) {
 // Indentify multiple code blocks
 const wrapperId = "yt" + Math.random().toString(36).substr(2, 10);

 // Avoid sanitizing iframe tag
 setTimeout(renderYoutube.bind(null, wrapperId, youtubeId), 0);

 return '<div id="' + wrapperId + '"></div>';
});
}

function renderYoutube(wrapperId, youtubeId) {
const el = document.querySelector('#' + wrapperId);

var UriParams = getUriParams(youtubeId);

var width = '100%';
var height = '100%';

if ( UriParams.width ) {
 width = UriParams.width;
}

if ( UriParams.height ) {
 height = UriParams.height;
}

var maxWidth = 500;

if ( UriParams['max-width'] ) {
 maxWidth = UriParams['max-width'];
}

var ratio = '16-9';

if ( UriParams['ratio'] ) {
 ratio = UriParams['ratio'];
}

var marginLeft = 'auto';

if ( UriParams['margin-left'] ) {
 marginLeft = UriParams['margin-left'];
}

var marginRight = 'auto';

if ( UriParams['margin-right'] ) {
 marginRight = UriParams['margin-right'];
}

if ( youtubeId.indexOf('?') !== -1 ) {
 var pos = youtubeId.indexOf('?');
 youtubeId = youtubeId.substr(0, pos);
}

el.innerHTML = '<div style="max-width:' + maxWidth + 'px; margin-left:' + marginLeft + '; margin-right:' + marginRight + ';" class="ratio-' + ratio + ' relative"><iframe class="abs-full" width="' + width + '" height="' + height + '" src="https://www.youtube.com/embed/' + youtubeId + '" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>';
}
//유튜브 플러그인 끝

//repl 플러그인 시작
function replPlugin() {
toastui.Editor.codeBlockManager.setReplacer("repl", function (replUri) {
 var postSharp = "";
 if ( replUri.indexOf('#') !== -1 ) {
   var pos = replUri.indexOf('#');
   postSharp = replUri.substr(pos);
   replUri = replUri.substr(0, pos);
 }

 if ( replUri.indexOf('?') === -1 ) {
   replUri += "?dummy=1";
 }

 replUri += "&lite=true";
 replUri += postSharp;

 // Indentify multiple code blocks
 const wrapperId = `yt${Math.random().toString(36).substr(2, 10)}`;

 // Avoid sanitizing iframe tag
 setTimeout(renderRepl.bind(null, wrapperId, replUri), 0);

 return "<div id=\"" + wrapperId + "\"></div>";
});
}

function renderRepl(wrapperId, replUri) {
const el = document.querySelector(`#${wrapperId}`);

var UriParams = getUriParams(replUri);

var height = 400;

if ( UriParams.height ) {
 height = UriParams.height;
}

el.innerHTML = '<iframe height="' + height + 'px" width="100%" src="' + replUri + '" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>';
}
//repl 플러그인 끝

//codepen 플러그인 시작
function codepenPlugin() {
toastui.Editor.codeBlockManager.setReplacer("codepen", function (codepenUri) {
 // Indentify multiple code blocks
 const wrapperId = `yt${Math.random().toString(36).substr(2, 10)}`;

 // Avoid sanitizing iframe tag
 setTimeout(renderCodepen.bind(null, wrapperId, codepenUri), 0);

 return '<div id="' + wrapperId + '"></div>';
});
}

function renderCodepen(wrapperId, codepenUri) {
const el = document.querySelector(`#${wrapperId}`);

var UriParams = getUriParams(codepenUri);

var height = 400;

if ( UriParams.height ) {
 height = UriParams.height;
}

var width = '100%';

if ( UriParams.width ) {
 width = UriParams.width;
}

if ( !isNaN(width) ) {
 width += 'px';
}

if ( codepenUri.indexOf('#') !== -1 ) {
 var pos = codepenUri.indexOf('#');
 codepenUri = codepenUri.substr(0, pos);
}

el.innerHTML = '<iframe height="' + height + '" style="width: ' + width + ';" scrolling="no" title="" src="' + codepenUri + '" frameborder="no" allowtransparency="true" allowfullscreen="true"></iframe>';
}
//repl 플러그인 끝

//lib 시작
String.prototype.replaceAll = function(org, dest) {
return this.split(org).join(dest);
}

function getUriParams(Uri) {
Uri = Uri.trim();
Uri = Uri.replaceAll('&amp;', '&');
if ( Uri.indexOf('#') !== -1 ) {
 var pos = Uri.indexOf('#');
 Uri = Uri.substr(0, pos);
}

var params = {};

Uri.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
return params;
}


function jq_attr($el, attrName, elseValue) {
		var value = $el.attr(attrName);

		if (value === undefined || value === "") {
			return elseValue;
		}

		return value;
	}



// lib 끝 

function getBodyFromXTemplate(selector) {
	return $(selector).html().trim().replace(/<!--REPLACE:script-->/gi, 'script');
} 




