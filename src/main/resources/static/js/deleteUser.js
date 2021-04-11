const buttonDelete = $("#btnDelete");
buttonDelete.click(
    function (event) {
        event.preventDefault();
        let form = document.getElementById('user_delete');
        let formData = new FormData(form);

        let response = fetch('http://localhost:8080/api/delete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;utf-8'
            },
            body: JSON.stringify({
                id: formData.get('id'),
            })
        }).then(() => location.reload());

        $('#deleteModal').modal('hide');
        $('.modal-backdrop').remove();
    }
)