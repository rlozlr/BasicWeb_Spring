console.log('boardComment.js in~!!');
console.log(bnoVal);
console.log(userEmail);

document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    const cmtText = document.getElementById('cmtText');
    if(cmtText.value == null || cmtText.value == '') {
        alert('댓글을 입력해주세요');
        cmtText.focus();
        return false;
    } else {
        let cmtData = {
            bno : bnoVal,
            writer : userEmail,
            content : cmtText.value
        };
        console.log(cmtData);
        // 댓글 등록
        postCommentToServer(cmtData).then(result =>{
            if(result == '1') {
                console.log('댓글 등록 성공~!!');
                cmtText.value = "";
            }
            // 화면에 뿌려
            spreadCommentList(cmtData.bno);
        })
    }
});

async function postCommentToServer(cmtData) {
    try {
       const url = "/comment/post";
       const config = {
        method : "post",
        headers : {
            'content-type' : 'application/json; charset=utf-8'
        },
        body : JSON.stringify(cmtData)
       };

       const resp = await fetch(url, config);
       const result = await resp.text();
       return result;

    } catch (error) {
        console.log(error);
    }
};

async function getCommentListFromServer(bno, page) {
    try {
        const resp = await fetch("/comment/"+bno+"/"+page);
        const result = await resp.json();
        return result;      
    } catch (error) {
        console.info(error);
    }
};

function spreadCommentList(bno, page=1) {
    getCommentListFromServer(bno, page).then(result=>{
        console.log(result.cmtList);
        const ul = document.getElementById('cmtListArea');
        if(result.cmtList.length > 0) {
            if(page == 1) {
                ul.innerHTML = '';
            }
            for(let cvo of result.cmtList) {
                let li = `<li class="list-group-item" data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
                li += `<div class="mb-3">`;
                li += `<div class="fw-bold">${cvo.writer} `;
                li += `<span class="badge rounded-pill text-bg-warning">${cvo.regAt}</span></div>`;
                li += `${cvo.content}`;
                li += `</div>`;
                if (cvo.writer == userEmail) {
                    li += `<button type="button" class="btn btn-sm btn-outline-success cmtModBtn" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                    li += `<button type="button" class="btn btn-sm btn-outline-danger cmtDelBtn">삭제</button>`;
                }
                li += `</li>`;
                ul.innerHTML += li;
            }   
            
            // 더보기 버튼
            let moreBtn = document.getElementById('moreBtn');
            console.log(moreBtn);
            if(result.pgvo.pageNo < result.endPage) {
                moreBtn.style.visibility = 'visible';
                moreBtn.dataset.page = page + 1;
            } else {
                moreBtn.style.visibility = 'hidden';
            }

        } else {
            if(page == 1) {
            let li = `<li class="list-group-item">댓글이 없습니다.</li>`;
            ul.innerHTML = li;
            }
        }
    })
};

document.addEventListener('click',(e)=>{
    console.log(e.target);
    if(e.target.id == 'moreBtn') {
        let page = parseInt(e.target.dataset.page);
        spreadCommentList(bnoVal, page);
    } else if(e.target.classList.contains('cmtModBtn')) {
        let li = e.target.closest('li');
        let cmtText = li.querySelector('.fw-bold').nextSibling;
        document.getElementById('cmtModText').value = cmtText.nodeValue;
        document.getElementById('cmtModBtn').setAttribute('data-cno', li.dataset.cno);
        document.getElementById('cmtModBtn').setAttribute("data-writer",li.dataset.writer);
    } else if (e.target.id == 'cmtModBtn') {
        let cmtModData ={
            cno: e.target.dataset.cno,
            writer: e.target.dataset.writer,
            content: document.getElementById('cmtModText').value
        }
        editCommentToServer(cmtModData).then(result =>{
            if(result == '1') {
                document.querySelector('.btn-close').click();
            } else {
                alert('댓글수정 실패');
                document.querySelector('.btn-close').click();
            }
            spreadCommentList(bnoVal);
        })
    } else if(e.target.classList.contains('cmtDelBtn')) {
        let li = e.target.closest('li');
        let cnoVal = li.dataset.cno;
        console.log(cnoVal);

        removeCommentFromServer(cnoVal).then(result =>{
            if(result == '1') {
                console.log('댓글삭제 성공');
                spreadCommentList(bnoVal);
            } else if (result == '0') {
                console.log('댓글삭제 실패');
            }
        })
    }
});

async function editCommentToServer(cmtModData) {
    try {
        const url = '/comment/edit';
        const config ={
            method : 'put',
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify(cmtModData)
        };
        const resp = await fetch(url, config);
        const result = resp.text();
        return result; 
    } catch (error) {
        console.log(error);
    }
};

async function removeCommentFromServer(cno) {
        try {
           const url = '/comment/remove/'+cno
           const config ={
            method : 'delete'
           };
           const resp = await fetch (url, config);
           const result = resp.text();
           return result; 
        } catch (error) {
            console.log(error)
        }
}