const API_ENDPOINT = '/Lab18/api';

let role;
let sessionId;


function setRole() {
    let username, password;

    if (document.getElementById('username')) {
        username = document.getElementById('username').value;
    } else {
        username = 'client';
    }

    if (document.getElementById('password')) {
        password = document.getElementById('password').value;
    } else {
        password = 'client';
    }

    fetch(`${API_ENDPOINT}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username, password})
    }).then(response => response.json()).then(response => {
        if (response) {
            if (response === 'error') {
                alert("Username and Password are invalid");
                role = 'client';
            } else if (response === "SignIn") {
                document.getElementById("form").innerHTML = `
                        <div>Username:<input type="text" id="username"></div>
                        <div>Password:<input type="password" id="password"></div>
                        <div>
                            <button onclick="setRole()">Sign In</button>
                            <button onclick="clearContent('form')">Cancel</button>
                        </div>`;
            } else {
                role = response;
                clearContent('form');
            }
            visibilityButtons();
        }
    })
}

function SignOut() {
    const username = 'reset', password = 'reset';
    fetch(`${API_ENDPOINT}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username, password})
    }).then(response => response.json()).then(response => {
        if (response) {
            role = response;
            visibilityButtons();
        }
    })
}

function visibilityButtons() {
    let buttons = `<button onclick="visibilityFilterForm()">Filter</button>`;
    if (role === 'admin') {
        buttons += `<button onclick="visibilityInsertForm()">Insert</button>
                    <button onclick="SignOut()">Sign Out</button>`;
    }
    document.getElementById('controls').innerHTML = buttons;
    getReferences();
}

function visibilityInsertForm() {
    document.getElementById("form").innerHTML = `
            <div>Url:<input type="text" id="url"></div>
            <div>Description:<input type="text" id="description"></div>
            <div>
                <button onclick="addReference()">Add Reference</button>
                <button onclick="clearContent('form')">Cancel</button>
            </div>`;
}

function visibilityCommentsInsertForm(id) {
    let needId = "insertComment" + id;
    document.getElementById(needId).innerHTML = `
                <div>Comment:<input type="text" id="comment"></div>
                <div>
                    <button onclick="addComment(${id})">Add Comment</button>
                    <button onclick="clearContent('insertComment${id}')">Cancel</button>
                </div>`;
}

function visibilityFilterForm() {
    document.getElementById("form").innerHTML = `
                <div>Description:<input type="text" id="filterDescription"></div>
                <div>
                    <button onclick="getReferences()">Filter</button>
                    <button onclick="clearContent('form')">Cancel</button>
                </div>`;
}

function updateContentVisibility(id, url, description) {
    document.getElementById(id).innerHTML = `
            <table>
                <tr>
                    <td>
                        <div>Url:<input type="text" id="editUrl" value="${url}" ></div>
                        <div>Description:<input type="text" id="editDescription" value="${description}"></div>
                        <div>
                            <button onclick="updateReference(${id})">Update</button>
                            <button onclick="clearContent(${id})">Cancel</button>
                        </div>
                    </td>
                </tr>
            </table>`;
}

function deleteContentVisibility(id) {
    document.getElementById(id).innerHTML = `
                    <table>
                        <tr>
                            <td>
                                Are you sure you want to delete reference with id ${id}?
                                <button onclick="deleteReference(${id})">Delete</button>
                                <button onclick="clearContent(${id})">Cancel</button>
                            </td>
                        </tr>
                    </table>`;
}

function clearContent(id) {
    const element = document.getElementById(id);
    if (element) {
        element.innerHTML = '';
    }
}
