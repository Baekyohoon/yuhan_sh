let templateCount = 0;

function addTemplate(event) {
	event.preventDefault(); // 폼의 제출을 막음
	
    const templateContainer = document.getElementById('templateContainer');

    // 이미지 추가 버튼 생성
    const imageBox = document.createElement('label');
    const previewImage = document.createElement('img');
    previewImage.src = '/img/placeholder-image.jpg'; // 기본 이미지 설정
    previewImage.alt = '이미지 미리보기';
    const fileInput = document.createElement('input');
    fileInput.type = 'file';
    fileInput.accept = 'image/*';
    fileInput.name = 'file';
    fileInput.style.display = 'none';
    fileInput.onchange = () => addImage(fileInput, previewImage);

    imageBox.appendChild(previewImage);
    imageBox.appendChild(document.createTextNode(''));
    imageBox.appendChild(fileInput);

    // 템플릿을 담을 div 생성
    const templateDiv = document.createElement('div');
    templateDiv.className = 'template-container';
    templateDiv.id = `template${templateCount}`;
    templateDiv.appendChild(imageBox);

    // 템플릿 삭제 버튼 생성
    const deleteButton = document.createElement('button');
    deleteButton.innerHTML = '사진 삭제';
    deleteButton.onclick = () => removeTemplate(templateDiv);

    // 생성한 요소들을 템플릿 컨테이너에 추가
    templateDiv.appendChild(deleteButton);
    templateContainer.appendChild(templateDiv);

    templateCount++;
    
     // 여기서 previewImage.src를 설정
    previewImage.src = '/img/로고.png';
}

function removeTemplate(templateDiv) {
    const templateId = templateDiv.id;
    const templateIndex = parseInt(templateId.replace('template', ''));

    // 삭제된 템플릿 이후의 템플릿들의 id를 조정
    for (let i = templateIndex + 1; i < templateCount; i++) {
        const currentTemplate = document.getElementById(`template${i}`);
        if (currentTemplate) {
            currentTemplate.id = `template${i - 1}`;
        }
    }

    templateDiv.remove();
    templateCount--;
}

function addImage(fileInput, previewImage) {
    console.log('addImage function is called!');
    const file = fileInput.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            previewImage.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
}


class SizeCountBox {
    constructor(container, id) {
        this.container = container;
        this.id = id;
        this.createBox();
    }

    createBox() {
        const sizeBox = document.createElement('input');
        sizeBox.type = 'text';
        sizeBox.id = 'size'
        sizeBox.name = 'size'
        sizeBox.placeholder = '사이즈';

        const countBox = document.createElement('input');
        countBox.type = 'text';
        countBox.id = 'count'
        countBox.name = 'count'
        countBox.placeholder = '수량';

        const removeButton = document.createElement('button');
        removeButton.textContent = '삭제';
        removeButton.addEventListener('click', () => this.removeBox());

        const boxContainer = document.createElement('div');
        boxContainer.className = 'size-count-box';
        boxContainer.id = `sizeCountBox${this.id}`;
        boxContainer.appendChild(sizeBox);
        boxContainer.appendChild(countBox);
        boxContainer.appendChild(removeButton);

        this.container.appendChild(boxContainer);
    }

    removeBox() {
        const boxContainer = document.getElementById(`sizeCountBox${this.id}`);
        if (boxContainer) {
            boxContainer.remove();
        }
    }
    
   getData() {
    const sizeBox = this.container.querySelector('input[type="text"][placeholder="사이즈"]');
    const countBox = this.container.querySelector('input[type="text"][placeholder="수량"]');

    const sizeBoxValue = sizeBox ? sizeBox.value : '';
    const countBoxValue = countBox ? countBox.value : '';

    return {
        size: sizeBoxValue,
        count: countBoxValue
    };
}
}

function addSizeCountBox(event) {
    event.preventDefault(); // 폼의 제출을 막음

    const sizeCountContainer = document.getElementById('sizeCountContainer');
    const sizeCountBox = new SizeCountBox(sizeCountContainer, Date.now());
    
    const sizeCountData = sizeCountBox.getData();
    sendSizeCountDataToServer(sizeCountData);
}

function sendSizeCountDataToServer(data) {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/creategoods', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                console.log('Data sent successfully.');
            } else {
                console.error('Failed to send data.');
            }
        }
    };

    xhr.send(JSON.stringify(data));
}
