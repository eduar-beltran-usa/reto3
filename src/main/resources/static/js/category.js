function getInformation() {
    $.ajax({
        url: 'http://168.138.246.53/api/Category/all',
        type: 'GET',
        dataType: 'JSON',
        success: function (answer) {
            console.log(answer);
            paintAnswer(answer);
        }
    });
}


function paintAnswer(items) {

    $("result").empty();
    let myTable = '<table bordercolor="blue" width="100%" height="100%"">';
    for (i = 0; i < items.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + items[i].id + "<td>";
        myTable += "<td>" + items[i].name + "<td>";
        myTable += "<td>" + items[i].description + "<td>";
        myTable += "<td>" + JSON.stringify(items[i].motorbikes) + "</td>";
        myTable += "<td> <button onclick='editElement(" + items[i].id + ")'>Editar</button>";
        myTable += "<td> <button onclick='deleteElement(" + items[i].id + ")'>Borrar</button>";
        myTable += "<tr>";
    }
    myTable += '</table>';
    $("#result").html(myTable);
}

function saveInformation() {
    let myData = {
        name: $("#name").val(),
        description: $("#description").val()
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax(
            'http://168.138.246.53/api/Category/save',
            {
                type: "POST",
                data: dataToSend,
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                statusCode: {
                    201: function () {
                        alert("guardado!");
                        //$("#id").val("");
                        $("#name").val("");
                        $("#description").val("");
                        $("#result").empty();
                        getInformation();
                    }
                }
            });
}

function editElement(id) {
    $.ajax({
        url: "http://168.138.246.53/api/Category/" + id,
        data: {},
        type: "GET",
        dataType: 'JSON',
        contentType: 'application/json',
        success: function (answer) {
            console.log(answer);
            let myTable = "<table>";
                $("#id").val(answer[i].id)
                $("#name").val(answer[i].name)
                $("#description").val(answer[i].description)
            
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
        description: $("#description").val()
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax(
            "http://168.138.246.53/api/Category/update",
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
                        $("#description").val("");
                        getInformation();
                    }
                }
            });
}

function deleteElement(id) {
    let misDatos = {
        id: id
    };
    let dataToSend = JSON.stringify(misDatos);
    $.ajax(
            'http://168.138.246.53/api/Category/' + id,
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

