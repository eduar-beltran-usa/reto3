function getInformation() {
    $.ajax({
        url: 'http://168.138.246.53/api/Message/all',
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
        myTable += "<td>" + items[i].idMessage + "<td>";
        myTable += "<td>" + items[i].messageText + "<td>";
        myTable += "<td>" + JSON.stringify(items[i].motorbike.name) + "</td>";
        myTable += "<td>" + JSON.stringify(items[i].client.name) + "</td>";
        myTable += "<td> <button onclick='editElement(" + items[i].id + ")'>Editar</button>";
        myTable += "<td> <button onclick='deleteElement(" + items[i].id + ")'>Borrar</button>";
        myTable += "<tr>";
    }
    myTable += '</table>';
    $("#result").append(myTable);
}

function saveInformation() {
    let selectClient = $("#client").children(":selected").attr("value");
    let selectMoto = $("#motorbike").children(":selected").attr("value");
    let myData = {
        messagetext: $("#messagetext").val(),
        client: {idClient: 1},
        motorbike: {id: selectMoto}
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax(
            'http://168.138.246.53/api/Message/save',
            {
                type: "POST",
                data: dataToSend,
                dataType: 'JSON',
                contentType: "application/json; charset=utf-8",
                statusCode: {
                    201: function () {
                        alert("guardado!");
                        $("#messagetext").val("");
                        $("#client").val("");
                        $("#motorbike").val("");
                        $("#result").empty();
                        getInformation();
                    }
                },

                error: function (jqXHR, textStatus, errorThrown) {
                    alert("No se guardo correctamente");


                }
            });
}

function editElement(id) {
    $.ajax({
        url: "http://168.138.246.53/api/Message/" + id,
        data: {},
        type: "GET",
        dataType: 'JSON',
        contentType: 'application/json',
        success: function (answer) {
            console.log(answer);
            let myTable = "<table>";
            for (i = 0; i < answer.items.length; i++) {
                $("#id").val(answer.items[i].id),
                        $("#messagetext").val(answer.items[i].messagetext)
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
        messagetext: $("#messagetext").val(),
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax(
            "http://168.138.246.53/api/Message/update",
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
                        $("#messagetext").val("");
                        getInformation();
                    }
                }
            });
}

function deleteElement(idMessage) {
    let misDatos = {
        id: idMessage
    };
    let dataToSend = JSON.stringify(misDatos);
    $.ajax(
            'http://168.138.246.53/api/Message/' + idMessage,
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


function selectClient() {
    $.ajax({
        url: 'http://168.138.246.53/api/Client/all',
        type: 'GET',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",

        success: function (answer) {
            console.log(answer);
            $("#category").empty();
            mySelect = '<option id="" ></option>';
            for (i = 0; i < answer.length; i++) {
                mySelect += '<option value=' + answer[i].id + '>' + answer[i].name + ' ' + '</option>';
            }
            $("#client").append(mySelect);

        },
        error: function (xhr, status) {
            alert('ha sucedido un problema:' + status);
        }
    });

}

function selectMoto() {
    $.ajax({
        url: 'http://168.138.246.53/api/Motorbike/all',
        type: 'GET',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",

        success: function (answer) {
            console.log(answer);
            $("#category").empty();
            mySelect = '<option id="" ></option>';
            for (i = 0; i < answer.length; i++) {
                mySelect += '<option value=' + answer[i].id + '>' + answer[i].name + ' ' + '(' + answer[i].year + ')' + '</option>';
            }
            $("#motorbike").append(mySelect);

        },
        error: function (xhr, status) {
            alert('ha sucedido un problema:' + status);
        }
    });

}

