function submitUpdate() {
    fetch("http://localhost:8080/api/users")
        .then(res => res.json())
        .then(data => renderUsers(data))
}

const form = document.querySelector('form');
document.getElementById("new_user").addEventListener('mousemove', submitUpdate);