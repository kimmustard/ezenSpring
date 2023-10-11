console.log(bnoVal);

async function postCommentToServer(cmtData) {
    try {
        const url = "/comment/post";
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
        console.log(error);
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
                getCommentList(cmtData.bno);
            }
        })
    }
});


async function spreadCommentListFromServer(bno) {
    try {
        const resp = await fetch('/comment/' + bno);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}


function getCommentList(bno) {
    spreadCommentListFromServer(bno).then(result => {
        console.log(result);

        let div = document.getElementById('cmtListArea');
        div.innerHTML = "";
        for (let i = 0; i < result.length; i++) {
            let str = `<div id="cmtBody${i}">`;
            str += `<div>`;
            str += `<table>`;
            str += `<tr>`;
            str += `<th>번호</th>`;
            str += `<th>ID</th>`;
            str += `<th>작성날짜</th>`;
            str += `</tr>`;
            str += `<tr>`;
            str += `<th>${result[i].cno}</th>`;
            str += `<th>${result[i].writer}</th>`;
            str += `<th>${result[i].reg_date}</th>`;
            str += `</tr>`;
            str += `</div>`;
            str += `</table>`;
            str += `<div>`;
            str += `<input type="text" id="cmtText"  value="${result[i].content}">`;
            str += `<button type="button" data-cno=${result[i].cno} data-writer="${result[i].writer}" class="modBtn">수정</button>`;
            str += `<button type="button" data-cno=${result[i].cno} data-writer="${result[i].writer}" class="delBtn">삭제</button>`;
            str += `</div>`;
            str += `</div>`;
            str += `<br>`;
            div.innerHTML += str;
        }


    })
}


// 클릭했을때 담은 객체를 전달
async function editCommentToServer(cmtModData) {

    try {
        const url = '/comment/' + cmtModData.cno;
        const config = {
            method: 'put',
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtModData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }
}

async function removeCommentToServer(cno) {
    try {
        const url = '/comment/' + cno;
        const config = {
            method: 'delete'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }
}


document.addEventListener('click', (e) => {
    console.log(e);
    if (e.target.classList.contains('modBtn')) {
        //수정 작업
        console.log("수정 버튼 클릭");

        //내가 선택한 타겟과 가장 가까운 div찾기
        let div = e.target.closest('div');
        let cnoVal = e.target.dataset.cno;
        let wirterVal = e.target.dataset.writer;
        let textContent = div.querySelector('#cmtText').value;


        console.log(cnoVal);
        console.log(wirterVal);
        console.log(textContent);

        let cmtModData = {
            cno: cnoVal,
            bno: bnoVal,
            writer: wirterVal,
            content: textContent
        };
        console.log(cmtModData);

        //서버 연결
        editCommentToServer(cmtModData).then(result => {
            if (result == 1) {
                alert("댓글 수정 성공");
                getCommentList(bnoVal);
            } else {
                alert("댓글 수정 실패");
            }
        })



    } else if (e.target.classList.contains('delBtn')) {
        //삭제작업
        console.log("삭제버튼 클릭");
        let div = e.target.closest('div');
        let cno = e.target.dataset.cno;


        removeCommentToServer(cno).then(result => {
            if (result == 1) {
                alert("삭제 성공");
                getCommentList(bnoVal);
            } else {
                alert("삭제 실패");
            }
        })
    }
})