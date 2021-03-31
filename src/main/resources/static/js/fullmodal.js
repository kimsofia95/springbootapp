// наполнение edit модалки
$(document).delegate('.eBtn_', 'click', function (event) {
    event.preventDefault();
     console.log('было нажатие на edit');

    let href = $(this).attr('href');
    console.log(href);
    $.get(href, function (user, status) {
        console.log(user);
        $('.user_update #id').val(user.id);
        $('.user_update #first_name').val(user.firstName);
        $('.user_update #last_name').val(user.lastName);
        $('.user_update #password').val();
        $('.user_update #email').val(user.email);
        $('.user_update #roles').val(user.roles);
    });
    $('.user_update').modal();
});

// наполнение delete модалки
$(document).delegate('.dBtn_', 'click' ,function (event) {
    event.preventDefault();
    // console.log('было нажатие а del');
    let href = $(this).attr('href');

    $.get(href, function (user, status) {
        $('.deleteForm #id_delete').val(user.id);
        $('.deleteForm #username_delete').val(user.username);
        // $('.deleteForm #password_delete').val();
        $('.deleteForm #email_delete').val(user.email);
        $('.deleteForm #role_delete').val(user.roles);
    });
    $('.deleteForm #deleteModal').modal();
});