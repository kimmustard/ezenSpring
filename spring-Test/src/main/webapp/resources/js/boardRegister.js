
// 이벤트로 버튼클릭 역할 바꾸기
document.getElementById('trigger').addEventListener('click', () => {
    document.getElementById('file').click();
})

// 정규표현식을 통해서 파일에 대한 형식제한
// 실행파일(exe) 막기, 이미지 파일만 체크, 파일 20MB 사이즈 보다 크면 업로드 불가

const regExp = new RegExp("\.(exe|sh|bat|msi|dll|jar)$");    //실행파일 막기
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif|bmp)$");   //이미지 파일 검증
const maxSize = 1024 * 1024 * 20; //최대 사이즈 설정


// 리턴 값 0과 1 구분 
function fileValidation(fileName, fileSize) {
    if (regExp.test(fileName)) {    // 실행 파일이면..?
        return 0;
    } else if (fileSize > maxSize) {    // 사이즈
        return 0;
    } else if (!regExpImg.test(fileName)) { // 이미지 검증
        return 0;
    } else {
        return 1;
    }
}

//첨부파일에 따라 파일이 등록 가능한지 체크하는 함수
document.addEventListener('change', (e) => {
    console.log(e.target);

    if (e.target.id == 'file') {
        document.getElementById('regBtn').disabled = false;
        const fileObject = document.getElementById('file').files;   //여러개의 파일이 배열로 들어가게 된다.
        console.log(fileObject);
        let div = document.getElementById('fileZone');
        div.innerHTML = "";
        let ul = `<ul>`;
        let isOk = 1;   //fileValidation 함수의 리턴 여부를 *로 체크 (하나라도 체크가 안되면(0이 되면) 연산이 곱하기라서 0으로 수렴)
        for (let file of fileObject) {
            let validResult = fileValidation(file.name, file.size);
            isOk *= validResult;    //모든 파일이 누적되어 *
            ul += `<li>`;
            //업로드 가능 여부 표시
            ul += `<div>${validResult ? '업로드 가능' : '업로드 불가능'}</div>`;
            ul += `${file.name}`;
            ul += ` <span class="badge text-bg-${validResult ? 'success' : 'danger'}">${file.size} Byte </span></li>`;
        }
        ul += `</ul>`;
        div.innerHTML = ul;
        if (isOk == 0) {    //첨부 불가능한 파일이 있다는 것을 의미
            document.getElementById('regBtn').disabled = true;  // 버튼 비활성화
        }
    }

})
/*
    ul -> li 여러개 생성
    li <div> 업로드 가능/불가능 </div> 
    파일이름 
    <span>파일 사이즈</span>

 */