async function addUser() {
    let select = document.getElementById('rolesSelectAdd');
    let form = document.getElementById('add_user');
    let formData = new FormData(form);

    let roleSet = [];
    let options = select && select.options;
    let opt;

    for (let i = 0, iLen = options.length; i < iLen; i++) {
        opt = options[i];

        if (opt.selected) {
            roleSet.push({"id": opt.value, "role": opt.text})
        }
    }

    let response = fetch('http://localhost:8080/api/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;utf-8'
        },
        body: JSON.stringify({
            firstName: formData.get('first_name1'),
            lastName: formData.get('last_name1'),
            age: formData.get('age1'),
            email: formData.get('email1'),
            password: formData.get('password1'),
            roles: roleSet
        })
    })
}

/*const form = document.querySelector('.add_user');
form.addEventListener('submit', addUser);*/