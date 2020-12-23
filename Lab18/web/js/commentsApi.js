function getCommentForReference(id) {
    fetch(`${API_ENDPOINT}/comments?referenceId=${id}`).then(response => response.json()).then(response => {
        let data = `<table>
                    <tr><td>
                        <h1>--UWSR COMMENTS/${id}--</h1>
                        <button onclick="visibilityCommentsInsertForm(${id})">Comment</button>
                        <button onclick="clearContent(${id})">Close</button>
                        <div id="insertComment${id}"></div>
                    </td></tr>
                </table><br>`;

        if (response) {
            response.forEach(element => {
                console.log(element);
                let comments = `<table><tr><td>[${element.stamp}]`;
                if (role == 'admin' || element.sessionId === sessionId) {
                    comments += `<input type="button" value="delete" onclick="deleteComment(${element.id},${element.referenceId})">
                                 <input type="button" value="update" onclick="updateComment(${element.id},${element.referenceId})">
                                 <br>
                                 <textarea id="txt${element.id}">${element.comment}</textarea>`
                } else {
                    comments += `<br><textarea readonly id="txt${element.id}">${element.comment}</textarea>`
                }
                comments += `</td></tr></table><br>`
                data += comments;
            });
        }
        document.getElementById(id).innerHTML = data;
    });
}


function addComment(id) {
    const today = new Date();
    fetch(`${API_ENDPOINT}/comments`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            comment: document.getElementById('comment').value,
            referenceId: id,
            sessionId
        })
    }).then(() => {
        clearContent(id);
        getCommentForReference(id);
    })
}

function updateComment(id, referenceId) {
    fetch(`${API_ENDPOINT}/comments`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id,
            comment: document.getElementById('txt' + id).value
        })
    }).then(() => {
        getCommentForReference(referenceId);
    });
}

function deleteComment(id, referenceId) {
    fetch(`${API_ENDPOINT}/comments?id=${id}`, {
        method: 'DELETE'
    }).then(() => {
        getCommentForReference(referenceId);
    });
}
