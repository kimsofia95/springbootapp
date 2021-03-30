async function updateUser(event) {
    event.preventDefault()
    console.log(111);
    let formData = new FormData(event);
    let response = fetch('/admin/edit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(formData)
    }).then(res => console.log(res.json()))
    console.log(response);
    // $('#editUserModal').modal('hide');
}