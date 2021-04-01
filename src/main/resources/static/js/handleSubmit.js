async function handleSubmit(event) {
    let href_ = event.target.href;
    event.preventDefault();
    let select = document.getElementById('rolesSelect')
    let formData = new FormData(event.target);

    let roleSet = [];
    let options = select && select.options;
    let opt;

    for (let i = 0, iLen = options.length; i < iLen; i++) {
        opt = options[i];

        if (opt.selected) {
            roleSet.push({"id": opt.value, "role": opt.text})
        }
    }

    let response = fetch('http://localhost:8080/admin/edit', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: formData.get('id'),
            firstName: formData.get('first_name'),
            lastName: formData.get('last_name'),
            age: formData.get('age'),
            email: formData.get('email'),
            password: formData.get('password'),
            roles: roleSet
        })
    })
    console.log(JSON.stringify({
        id: formData.get('id'),
        firstName: formData.get('first_name'),
        lastName: formData.get('last_name'),
        age: formData.get('age'),
        email: formData.get('email'),
        password: formData.get('password'),
        roles: roleSet
    }));
}

const form = document.querySelector('form');
form.addEventListener('submit', handleSubmit);