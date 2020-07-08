<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>


<div class="sbs-slider">
	<div class="slides">
		<div style="background-image: url(../../resource/img/main-slide-1.jpg);"class="active"></div>
		<div style="background-image: url(../../resource/img/main-slide-2.jpg);"></div>
		<div style="background-image: url(../../resource/img/main-slide-3.jpg);"></div>
		<div style="background-image: url(../../resource/img/main-slide-4.jpg);"></div>
		<div style="background-image: url(../../resource/img/main-slide-5.jpg);"></div>
		<div style="background-image: url(../../resource/img/main-slide-6.jpg);"></div>
		<div style="background-image: url(../../resource/img/main-slide-7.jpg);"></div>
		
	</div>

	<div class="side-bars">
		<div>
			<span> <i class="fa fa-angle-left" aria-hidden="true"></i>
			</span>
		</div>
		<div>
			<span><i class="fa fa-angle-right" aria-hidden="true"></i></span>
		</div>
	</div>
</div>
<div class="main-bottom"></div>

<style>

/* 슬라이더 시작 */
html, body {
    height: 100%;
}
body, ul, li {
    margin:0;
    padding:0;
    list-style:none;
}

html, body {
    height:100%;
}

.sbs-slider {
	top:0;
    height:100%;   /**/
    position:relative;
    user-select:none;
    /*-------------*/
    
}



.sbs-slider .side-bars > div {
    position:absolute;
    top:0;
    left:0;
    /* ie 9 이상에서만 사용 가능 */
    width:calc(50% - 100px);
    height:100%;
    cursor:pointer;
    
}

.sbs-slider .side-bars > div   {
	/*top:0;
	left:0;
	right:0;
	bottom:0;*/
	background-size:cover;
	background-position:center;
}





.sbs-slider .side-bars > div:last-child {
    left:auto;
    right:0;
}

.sbs-slider .side-bars > div > span {
    position:absolute;
    top:50%;
    transform:translateY(-50%);
    left:inherit;
    right:inherit;
    font-size:5rem;
    transition: transform 0.2s;
    margin:0 10px;
}

.sbs-slider .side-bars > div:active > span {
    transform:translateY(-40%);
}

.sbs-slider > .slides > div {
    position:absolute;
    top:0;
    left:0;
    width:100%;
    height:100%;
    /* 완전투명하게 만든다. */
    opacity:0;
    transition:opacity 0.5s;
    background-repeat:no-repeat;
    background-size:cover;
    background-position:center;
}

.sbs-slider > .slides > div.active {
    opacity:1;
}


@media (max-width:800px) {
	.sbs-slider {
	
    height:100%;
    position:relative;
    user-select:none;
}
	
		
	
	.sbs-slider .side-bars  i {
		
		font-size:2rem;
	}
	
	
	
}

/* 슬라이더 끝 */

</style>

<%@ include file="/jsp/part/foot.jspf"%>