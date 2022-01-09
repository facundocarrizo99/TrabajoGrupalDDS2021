    /* Google map
    ------------------------------------------------*/
    var map = '';
    var center;
    function initialize() {
    var mapOptions = {
    zoom: 13,
    center: new google.maps.LatLng(-23.013104,-43.394365),
    scrollwheel: false
};
    map = new google.maps.Map(document.getElementById('google-map'),  mapOptions);
    google.maps.event.addDomListener(map, 'idle', function() {
    calculateCenter();
});
    google.maps.event.addDomListener(window, 'resize', function() {
    map.setCenter(center);
});
}
    function calculateCenter() {
    center = map.getCenter();
}
    function loadGoogleMap(){
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyDVWt4rJfibfsEDvcuaChUaZRS5NXey1Cs&v=3.exp&sensor=false&' + 'callback=initialize';
    document.body.appendChild(script);
}
    function setCarousel() {
    if ($('.tm-article-carousel').hasClass('slick-initialized')) {
    $('.tm-article-carousel').slick('destroy');
}
    if($(window).width() < 438){
    // Slick carousel
    $('.tm-article-carousel').slick({
    infinite: false,
    dots: true,
    slidesToShow: 1,
    slidesToScroll: 1
});
}
    else {
    $('.tm-article-carousel').slick({
    infinite: false,
    dots: true,
    slidesToShow: 2,
    slidesToScroll: 1
});
}
}
    $(document).ready(function(){
    // Google Map
    loadGoogleMap();
    // Slick carousel
    setCarousel();
    $(window).resize(function() {
    setCarousel();
});
    // Update the current year in copyright
    $('.tm-current-year').text(new Date().getFullYear());
});
