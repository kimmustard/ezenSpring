console.log(bnoVal);

async function postCommentToServer(cmtData) {
    try {
        const url = "/commnet/post";
        const config = {
            method: "post",
            headers: {
                'content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config);
        const result = await resp.text();   //isOk 값 반환예정
        return result;
    } catch (error) {
        console.log(err);
    }
}


document.getElementById('cmtPostBtn').addEventListener('click', () => {
    const cmtText = document.getElementById('cmtText').value;
    const cmtWriter = document.getElementById('cmtWriter').innerText; //span 태그 안에 값은 innerText로 가져와야한다.
    if (cmtText == "" || cmtText == null) {
        alert("댓글을 입력해주세요.");
        document.getElementById('cmtText').focus();
        return false;
    } else {
        let cmtData = {
            bno: bnoVal,
            writer: cmtWriter,
            content: cmtText
        };
        console.log(cmtData);
        postCommentToServer(cmtData).then(result => {
            console.log(result);
            //isOk 값 확인
            if (result == 1) {
                alert('댓글 등록 성공!');
                // 화면에 뿌리기
            }
        })
    }
});