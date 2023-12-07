function setRating(rating) {
	document.getElementById('star').value = rating;
	const stars = document.querySelectorAll('.star');
	stars.forEach((star, index) => {
		star.classList.toggle('active', index < rating);
	});
}
