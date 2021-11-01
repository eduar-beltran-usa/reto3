function getInformation() {
    $.ajax({
        url: 'http://168.138.246.53/api/Client/all',
        type: 'GET',
        dataType: 'JSON',
        success: function (answer) {
            console.log(answer);
            paintAnswer(answer);
        }
    });
}


function paintAnswer(items) {

    let myTable = '<table bordercolor="blue" width="100%" height="100%"">';
    for (i = 0; i < items.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + items[i].idClient + "<td>";
        myTable += "<td>" + items[i].name + "<td>";
        myTable += "<td>" + items[i].email + "<td>";
        myTable += "<td>" + items[i].password + "<td>";
        myTable += "<td>" + items[i].age + "<td>";
        myTable += "<td>" + JSON.stringify(items[i].messages) + "</td>";
        myTable += "<td>" + JSON.stringify(items[i].reservations) + "</td>";
        myTable += "<td> <button onclick='editElement(" + items[i].id + ")'>Editar</button>";
        myTable += "<td> <button onclick='deleteElement(" + items[i].id + ")'>Borrar</button>";
        myTable += "<tr>";
    }
    myTable += '</table>';
    $("#result").append(myTable);
}

function saveInformation() {
    let myData = {
        name: $("#name").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        age: $("#age").val()
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "http://168.138.246.53/api/Client/save",
        type: "POST",
        data: dataToSend,
        dataType: 'JSON',
        contentType: "application/json; charset=utf-8",
        statusCode: {
            201: function () {
                alert("guardado!");
                $("#idClient").val("");
                $("#name").val("");
                $("#email").val("");
                $("#password").val("");
                $("#age").val("");
                $("#result").empty();
                getInformation();
            }
        }
    });
}

function editElement(id) {
    $.ajax({
        url: "http://168.138.246.53/api/Client/" + id,
        data: {},
        type: "GET",
        dataType: 'JSON',
        contentType: 'application/json',
        success: function (answer) {
            console.log(answer);
            let myTable = "<table>";
            for (i = 0; i < answer.items.length; i++) {
                $("#id").val(answer.items[i].id),
                        $("#name").val(answer.items[i].name),
                        $("#email").val(answer.items[i].email),
                        $("#age").val(answer.items[i].age)
            }
        },
        error: function (xhr, status) {
            alert('Ha sucedido un problema: ' + status + JSON);
        }
    });
}

function editInformation() {
    let myData = {
        id: $("#id").val(),
        name: $("#name").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        age: $("#age").val()
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax(
            "http://168.138.246.53/api/Client/update",
            {
                data: dataToSend,
                type: "PUT",
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8',
                statusCode: {
                    201: function () {
                        $("#result").empty();
                        alert("Editado!");
                        $("#id").val("");
                        $("#name").val("");
                        $("#email").val("");
                        $("#age").val("");
                        getInformation();
                    }
                }
            });
}

function deleteElement(idClient) {
    let misDatos = {
        id: idClient
    };
    let dataToSend = JSON.stringify(misDatos);
    $.ajax(
            'http://168.138.246.53/api/Client/' + idClient,
            {
                data: dataToSend,
                type: 'DELETE',
                dataType: "JSON",
                contentType: "application/JSON; charset=utf-8",

                statusCode: {
                    204: function () {
                        alert("Item Eliminado!");
                        $("#result").empty();
                        getInformation();
                    }
                }

            });

}
