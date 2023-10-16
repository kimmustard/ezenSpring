

document.addEventListener('click', (e) => {
    console.log(e);
    if (e.target.classList.contains('file-x')) {
        let uuid = e.target.dataset.uuid;

        console.log(uuid);

        removefileToServer(uuid).then(result => {
            if (result == 1) {
                alert("파일 삭제 성공");
                e.target.closest('li').remove();    //댓글 li태그 삭제
                location.reload();
            } else {
                alert("파일 삭제 실패");
            }
        })

    }



})

async function removefileToServer(uuid) {
    try {
        const url = '/board/file/' + uuid;
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

