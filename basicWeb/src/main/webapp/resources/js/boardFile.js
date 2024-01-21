document.getElementById('trigger').addEventListener('click', ()=>{
    document.getElementById('files').click();
});

const regExp = new RegExp("\.(.exe|sh|bat|dll|jar|msi)$");
const maxSize = 1024 * 1024 * 20;

function fileValidation(fileName, fileSize) {
    if(regExp.test(fileName)) {
        return 0;
    } else if (fileSize > maxSize) {
        return 0;
    } else {
        return 1;
    }
};

document.addEventListener('change',(e)=>{
    console.log(e.target);
    if(e.target.id == 'files') {  
        const fileObj = document.getElementById('files').files;
        console.log(fileObj);

        document.getElementById('regBtn').disabled = false;

        let div = document.getElementById('fileZone');
        div.innerHTML = '';

        let isOk = 1;  
        let ul = `<ul class="list-group list-group-flush">`;
            for(let file of fileObj) {
                let vaildResult = fileValidation(file.name, file.size);
                isOk *= vaildResult;
                ul += `<li class="list-group-item">`;
                ul += `<div class="mb-3">`;
                ul += `${vaildResult ? '<div class="fw-bold"> 업로드 가능</div>' : '<div class="fw-bold text-danger">} 업로드 불가능 </div>'}`;
                ul += `${file.name}</div>`;
                ul += `<span class="badge rounded-pill text-bg-${vaildResult ? 'success':'danger'}">${file.size}Byte</span>`;
                ul += `</li>`;
            }
        ul += `</ul>`;
        div.innerHTML = ul;

        if(isOk == 0) {
            document.getElementById('regBtn').disabled = true;
        }
    }
});

document.addEventListener('click', (e)=>{
    if(e.target.classList.contains('file-x')) {
        let uuid = e.target.dataset.uuid;
        removeFileToServer(uuid).then(result =>{
            if(result == '1') {
                console.log("파일 삭제 성공");
                e.target.closest('li').remove();
            }
        })
    }
});

async function removeFileToServer(uuid) {
    try {
        const url = "/board/file/" + uuid;
        const config ={
            method : "delete"
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
};