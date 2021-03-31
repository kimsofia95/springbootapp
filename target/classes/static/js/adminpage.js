const usersList = document.getElementById('all_users_content')
const renderUsers = (users => {
    if (users.length > 0) {
        var temp = "";
        for (let i = users.length - 1; i >= 0; i--) {
            temp += "<tr id=" + users[i].id + ">";
            temp += "<td>" + users[i].id + "</td>";
            temp += "<td>" + users[i].firstName + "</td>";
            temp += "<td>" + users[i].lastName + "</td>";
            temp += "<td>" + users[i].age + "</td>";
            temp += "<td>" + users[i].email + "</td>";
            temp += "<td>";
            for (let j = users[i].authorities.length - 1; j >= 0; j--) {
                 temp += users[i].authorities[j].role.substr(5) + " ";
            }
            temp += "</td>";

            temp += "<td>"
            temp +=
                '<a href="api/users/' + users[i].id + '" class="btn btn-info eBtn_" data-toggle="modal" data-target="#editModal">'
                + 'Edit</a></td>';
            temp += "<td>"
            temp +=
                " " +
                '<a href="api/users/' + users[i].id + '" class="btn btn-danger dBtn_" data-toggle="modal" data-target="#deleteModal">'
                + 'Delete</a></td></tr>';

        /*    <td>
                <button type="button" className="btn btn-success eBtn_" data-toggle="modal"
                        th:attr="data-target='#edit' + ${user_i.getId()}"
                        data-target="#staticBackdrop">Edit
                </button>
            </td>
            <td>
                <button type="button" className="btn btn-danger" data-toggle="modal"
                        th:attr="data-target='#delete' + ${user_i.getId()}"
                        data-target="#staticBackdrop">Delete
                </button>
            </td>*/
        }
       // users.forEach((user) => {
        //})
        // ----end for loop

        usersList.innerHTML = temp;
    }
})

function show() {
// GET запрос и заполнение админ таблицы
    fetch("http://localhost:8080/api/users")
        .then(res => res.json())
        .then(data => renderUsers(data))
}
show();