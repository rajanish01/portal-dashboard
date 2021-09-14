function login() {
    var username = $("#username1").val();
    var password = $("#password1").val();
    var data = {
        "username": username,
        "password": password
    }
    post("./authenticate", data);
}

function post(url, data) {
    $.ajax({
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function(response) {
            console.log(response);
            $("#loginModal").modal('toggle')
            success("User Logged In !")
            return response;
        },
        error: function(error) {
            console.log(formatError(error));
            danger(formatError(error));
        },
        processData: false,
        type: 'POST',
        url: url
    });
}

function get(url) {
    $.get(url, function() {
            alert("success");
        })
        .done(function() {
            alert("second success");
        })
        .fail(function() {
            alert("error");
        })
        .always(function() {
            alert("finished");
        });
}

function formatError(error) {
    var errorCode = error.status;
    var errorMessage = error.responseJSON.message;
    var detailError = error.responseJSON.details[0]

    return errorMessage + " : " + errorCode + " -> " + detailError;
}