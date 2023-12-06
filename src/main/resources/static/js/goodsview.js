function showClickedImage(imageid) {
    var images = document.getElementById(imageid).src
    document.getElementById('clickedImage').src = images;
    
}