function showClickedImage(imageid) {
    var images = document.getElementById(imageid).src
    document.getElementById('clickedImage').src = images;
    
}

function toggleTextarea(qid) {
    var textareaContainer = document.getElementById('textareaContainer-' + qid);
    if (textareaContainer) {
        textareaContainer.style.display = textareaContainer.style.display === 'none' ? 'block' : 'none';
    } else {
        console.error('Element with ID textareaContainer-' + qid + ' not found.');
    }
}

  function toggleAnswer(qid) {
    var answerContainer = document.getElementById('answerContainer-' + qid);
    if (answerContainer.style.display === 'none') {
        answerContainer.style.display = 'block';
    } else {
        answerContainer.style.display = 'none';
    }
}

    
    