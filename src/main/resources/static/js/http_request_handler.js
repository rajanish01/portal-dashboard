
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
    success: function (response) {
      console.log(response);
      $('[data-toggle="tooltip"]').tooltip();
      return response;
    },
    error: function () {
      console.log("Login Failed !");
      alert("Login Failed !")
      return null;
    },
    processData: false,
    type: 'POST',
    url: url
  });
}

function get(url) {
  $.get(url, function () {
    alert("success");
  })
    .done(function () {
      alert("second success");
    })
    .fail(function () {
      alert("error");
    })
    .always(function () {
      alert("finished");
    }); l̥
}