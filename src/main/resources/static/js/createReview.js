function setRating(rating) {
	document.getElementById('star').value = rating;
	const stars = document.querySelectorAll('.star');
	stars.forEach((star, index) => {
		star.classList.toggle('active', index < rating);
	});
}

function previewImage() {
            var preview = document.getElementById('preview');
            var fileInput = document.getElementById('image');

            // 이미지 파일이 선택되었을 때 미리보기를 업데이트
            fileInput.addEventListener('change', function() {
                var file = fileInput.files[0];

                if (file) {
                    var reader = new FileReader();

                    reader.onload = function(e) {
                        preview.src = e.target.result;
                        preview.style.display = 'block';
                    };

                    reader.readAsDataURL(file);
                } else {
                    // 파일이 선택되지 않았을 때 미리보기 숨기기
                    preview.src = '#';
                    preview.style.display = 'none';
                }
            });
        }