
function roleOfUser(roles) {
    let role = "";
    for (let temp of roles) {
        role += temp.name;
        if (roles.length > 1) {
            role += " ";
        }
    }
    return role;
}


async function navInfoAdmin() {
    const response = await fetch('http://localhost:8080/api/user')
    const authUser = await response.json()

    const infoUsername = document.getElementById("infoUsername")
    infoUsername.innerHTML = authUser.username

    const infoRoles = document.getElementById("infoRoles")
    infoRoles.innerHTML = authUser.roles.at(0).name
}


let res = navInfoAdmin()
console.log(res.catch() + ' - Данные о том кто зашел выведены')


const allUsersBody = document.getElementById('allUsersBody')
let result = ''
const url = 'http://localhost:8080/api/users'


const usersRows = (users) => {

    users.forEach(user => {
        result += `<tr id="row${user.id}">
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.age}</td>
                            <td>${user.username}</td>
                            <td>${roleOfUser(user.roles)}</td>
                            <td><a class="btnEdit btn btn-primary">Edit</a></td>
                            <td><a class="btnDelete btn btn-danger">Delete</a></td>
                       </tr>`
    })
    allUsersBody.innerHTML = result
}


async function getUsers() {
    try {
        const response = await fetch(url)
        const users = await response.json()
        await usersRows(users)
    } catch (e) {
        console.error(e)
    }
}

res = getUsers()
console.log(res + ' - Таблица пользователей выведена')

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        //closest() позволяет перемещаться по DOM, пока мы не получим элемент, соответствующий заданному селектору.
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

const urlRoles = 'http://localhost:8080/api/roles'
const selectEdit = document.getElementById('editRoles')
const selectDelete = document.getElementById('deleteRoles')
const selectNew = document.getElementById('newRoles')


async function getEditRoles() {
    const responseRoles = await fetch(urlRoles)
    const allRoles = await responseRoles.json()
    for (let i = 0; i < allRoles.length; i++) {
        let text = allRoles[i].name.replace("ROLE_", "");
        // получаем значение для элемента, stringify() для преобразования объектов в JSON
        let json = JSON.stringify(allRoles[i])
        // создаем новый элемента, где text будет помещен между ><, а json в option
        selectEdit.options[i] = new Option(text, json)
    }
}

async function getDeleteRoles() {
    const responseRoles = await fetch(urlRoles)
    const allRoles = await responseRoles.json()
    for (let i = 0; i < allRoles.length; i++) {
        let text = allRoles[i].name.replace("ROLE_", "");
        // получаем значение для элемента, stringify() для преобразования объектов в JSON
        let json = JSON.stringify(allRoles[i])
        // создаем новый элемента, где text будет помещен между ><, а json в option
        selectDelete.options[i] = new Option(text, json)
    }
}


async function getNewRoles() {
    const responseRoles = await fetch(urlRoles)
    const allRoles = await responseRoles.json()
    for (let i = 0; i < allRoles.length; i++) {
        let text = allRoles[i].name.replace("ROLE_", "");
        // получаем значение для элемента, stringify() для преобразования объектов в JSON
        let json = JSON.stringify(allRoles[i])
        // создаем новый элемента, где text будет помещен между ><, а json в option
        selectNew.options[i] = new Option(text, json)
    }
}

const editUser = document.getElementById('editUser')
const modalEditBootstrap = new bootstrap.Modal(editUser)
const editId = document.getElementById('editId')
const editFirstName = document.getElementById('editFirstName')
const editLastName = document.getElementById('editLastName')
const editAge = document.getElementById('editAge')
const editEmail = document.getElementById('editEmail')
const editPassword = document.getElementById('editPassword')

let idUser = 0


on(document, 'click', '.btnEdit', async e => {
    const fila = e.target.parentNode.parentNode
    idUser = fila.children[0].innerHTML
    const firstNameForm = fila.children[1].innerHTML
    const lastNameForm = fila.children[2].innerHTML
    const ageForm = fila.children[3].innerHTML
    const emailForm = fila.children[4].innerHTML
    const passwordForm = fila.children[5].innerHTML
    editId.value = idUser
    editFirstName.value = firstNameForm
    editLastName.value = lastNameForm
    editAge.value = ageForm
    editEmail.value = emailForm
    editPassword.value = passwordForm
    res = getEditRoles()
    console.log(res + ' - Модальное окно изменения вызвано')
    modalEditBootstrap.show()
})

//don't working
editUser.addEventListener('submit', async e => {
    e.preventDefault()
    const options = selectEdit.selectedOptions
    const values = Array.from(options).map(({value}) => value)
    const roleListJSON = '[' + values + ']'
    //для преобразования JSON обратно в объект
    const roleList = JSON.parse(roleListJSON);
    const editUser = {}
    editUser.id = idUser
    editUser.firstName = editFirstName.value
    editUser.lastName = editLastName.value
    editUser.age = editAge.value
    editUser.username = editEmail.value
    editUser.password = editPassword.value
    editUser.roles = roleList
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(editUser)
    })
    console.log(editUser)
    await response.json()
    //await location.reload()
    modalEditBootstrap.hide()
})

// const deleteUser = document.getElementById('deleteUser')
const deleteUser = document.getElementById('deleteUser')
const modalDeleteBootstrap = new bootstrap.Modal(deleteUser)
const deleteId = document.getElementById('deleteId')
const deleteFirstName = document.getElementById('deleteFirstName')
const deleteLastName = document.getElementById('deleteLastName')
const deleteAge = document.getElementById('deleteAge')
const deleteEmail = document.getElementById('deleteEmail')
const deletePassword = document.getElementById('deletePassword')


//MODAL DELETE WORKING
on(document, 'click', '.btnDelete', e => {
    const fila = e.target.parentNode.parentNode
    idUser = fila.children[0].innerHTML
    const firstNameForm = fila.children[1].innerHTML
    const lastNameForm = fila.children[2].innerHTML
    const ageForm = fila.children[3].innerHTML
    const emailForm = fila.children[4].innerHTML
    const passwordForm = fila.children[5].innerHTML
    deleteId.value = idUser
    deleteFirstName.value = firstNameForm
    deleteLastName.value = lastNameForm
    deleteAge.value = ageForm
    deleteEmail.value = emailForm
    deletePassword.value = passwordForm
    res = getDeleteRoles()
    console.log(res + ' - Модальное окно удаления вызвано')
    modalDeleteBootstrap.show()
})

//DELETE USER
deleteUser.addEventListener('submit', async e => {
    const fila = document.getElementById('row' + idUser)
    fila.parentElement.removeChild(fila)
    await fetch(url + "/" + idUser, {
        method: 'DELETE'
    })
})


const newUser = document.getElementById('newUser')
const newFirstName = document.getElementById('newFirstName')
const newLastName = document.getElementById('newLastName')
const newAge = document.getElementById('newAge')
const newEmail = document.getElementById('newEmail')
const newPassword = document.getElementById('newPassword')

res = getNewRoles()


//NEW USER
newUser.addEventListener('submit', async e => {
    e.preventDefault()
    const options = selectNew.selectedOptions
    const values = Array.from(options).map(({value}) => value)
    const roleListJSON = '[' + values + ']'
    const roleList = JSON.parse(roleListJSON)
    const newUser = {}
    newUser.firstName = newFirstName.value
    newUser.lastName = newLastName.value
    newUser.age = newAge.value
    newUser.username = newEmail.value
    newUser.password = newPassword.value
    newUser.roles = roleList
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(newUser)
    })
    await response.json()
    await location.reload()
})