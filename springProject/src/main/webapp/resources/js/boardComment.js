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
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }
}

document.getElementById('cmtPostBtn').addEventListener('click', () => {
    const cmtText = document.getElementById('cmtText').value;
    const cmtWriter = document.getElementById('cmtWriter').innerText;
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
            if (result == 1) {
                alert('댓글 등록 성공!');
                getCommentList(cmtData.bno);
            }
        })
    }

})

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
            str += `<li class="list-group-item" id="cmtBody${i}">`;
            str += `<div>`;
            str += `<div> ${result[i].writer} </div>`;
            str += `${result[i].content}`;
            str += `</div>`;
            str += `<span class="badge rounded-pill text-bg-primary"> ${result[i].modAt} </span>`;
            str += `<div id="cmtBtnContainer">`;
            str += `<button type="button" class="modBtn btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
            str += `<button type="button" data-cno=${result[i].cno} data-writer="${result[i].writer}" class="delBtn btn btn-primary">삭제</button>`;
            str += `</div>`;
            str += `</li>`;
            str += `</div>`;
            div.innerHTML += str;
            document.getElementById('cmtBtnContainer').style.display = 'flex';
        }

    })

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
        console.log("수정 버튼 클릭");
    } else if (e.target.classList.contains('delBtn')) {
        let cno = e.target.dataset.cno;

        removeCommentToServer(cno).then(result => {
            if (result == 1) {
                alert("삭제 성공");
            } else {
                alert("삭제 실패");
            }
            getCommentList(bnoVal);
        })
    }
})