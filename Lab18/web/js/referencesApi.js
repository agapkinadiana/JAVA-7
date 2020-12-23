function getReferences() {
    let params = '';
    document.getElementById("references").innerHTML = '';
    if (document.getElementById("filterDescription")) {
        params = '?filter=' + document.getElementById("filterDescription").value;
    }

    fetch(`${API_ENDPOINT}/references` + params).then(response => {
        sessionId = response.headers.get('sessionId');
        return response.json();
    }).then(response => {
        if (response) {
            response.forEach(element => {
                let references = `<table><tr><td>`;
                if (role === "admin") {
                    console.log(element.id);
                    references += `<input type="button" value="delete" onclick="deleteContentVisibility(${element.id})">
                                 <input type="button" value="update" onclick="updateContentVisibility(${element.id},'${element.url}','${element.description}')">`;
                }
                references += `<input type="button" value="+${element.plus}" onclick="updateReference('${element.id}','plus', '${element.plus}')">
                             <input type="button" value="-${element.minus}" onclick="updateReference('${element.id}','minus', '${element.minus}')">
                             <input type="button" value="comments" onclick="getCommentForReference(${element.id})">
                             [${element.id}]
                             <a href="${element.url}">${element.description}</a>
                             </td></tr>
                             <tr><td id="${element.id}">
                             </td></tr>
                        </table><br>`;
                document.getElementById("references").innerHTML += references;
            });
        }
    });
}

function addReference() {
    if (document.getElementById('url').value && document.getElementById('description').value) {
        fetch(`${API_ENDPOINT}/references`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                url: document.getElementById('url').value,
                description: document.getElementById('description').value
            })
        }).then(() => {
            clearContent('form')
            getReferences();
        });
    } else {
        alert('Enter url and its description');
    }
}

function updateReference(id, recent, value) {
    let plus = -1;
    let minus = -1;
    let url, description;

    if (recent === 'plus') {
        plus = value;
    } else if (recent === 'minus') {
        minus = value;
    }

    if (document.getElementById('editUrl')) {
        url = document.getElementById('editUrl').value;
    }
    if (document.getElementById('editDescription')) {
        description = document.getElementById('editDescription').value;
    }

    fetch(`${API_ENDPOINT}/references`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id, url, description, plus, minus})
    }).then(getReferences);
}

function deleteReference(id) {
    fetch(`/${API_ENDPOINT}/references?id=${id}`, {
        method: 'DELETE'
    }).then(getReferences);
}
