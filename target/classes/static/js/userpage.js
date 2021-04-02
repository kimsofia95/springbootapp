const userList = document.getElementById('user_content')
const renderUser = (user => {
    let temp = "";
    temp += "<tr id=" + user.id + ">";
    temp += "<td>" + user.id + "</td>";
    temp += "<td>" + user.firstName + "</td>";
    temp += "<td>" + user.lastName + "</td>";
    temp += "<td>" + user.age + "</td>";
    temp += "<td>" + user.email + "</td>";
    temp += "<td>";
    for (let j = user.authorities.length - 1; j >= 0; j--) {
        temp += user.authorities[j].role.substr(5) + " ";
    }
    temp += "</td></tr>";

    userList.innerHTML = temp;
})

function show() {
// GET запрос и заполнение админ таблицы
    fetch("http://localhost:8080/api/user")
        .then(res => res.json())
        .then(data => renderUser(data))
}
show();