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
        $('.user_update #age').val(user.age);
        $('.user_update #email').val(user.email);
        $('.user_update #password').val(user.password);
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
        $('.user_delete #id1').val(user.id);
        $('.user_delete #first_name1').val(user.firstName);
        $('.user_delete #last_name1').val(user.lastName);
        $('.user_delete #age1').val(user.age);
        $('.user_delete #email1').val(user.email);
        $('.user_delete #password1').val(user.password);
        $('.user_delete #roles').val(user.roles);
    });
    $('.user_delete').modal();
});