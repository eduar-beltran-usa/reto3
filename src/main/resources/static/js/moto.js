function getInformation() {
    $.ajax({
        url: 'http://168.138.246.53/api/Motorbike/all',
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
        myTable += "<td>" + items[i].id + "<td>";
        myTable += "<td>" + items[i].name + "<td>";
        myTable += "<td>" + items[i].brand + "<td>";
        myTable += "<td>" + items[i].year + "<td>";
        myTable += "<td>" + items[i].description + "<td>";
        myTable += "<td>" + JSON.stringify(items[i].category.id) + "</td>";
        myTable += "<td>" + JSON.stringify(items[i].messages) + "</td>";
        myTable += "<td>" + JSON.stringify(items[i].reservations) + "</td>";
        myTable += "<td> <button onclick='editElement(" + items[i].id + ")'>Editar</button>";
        myTable += "<td> <button onclick='deleteElement(" + items[i].id + ")'>Borrar</button>";
        myTable += "<tr>";
    }
    myTable += '</table>';
    $("#result").html(myTable);
}

function saveInformation() {
    let selected = $("#category").children(":selected").attr("value");
    let myData = {
        name: $("#name").val(),
        brand: $("#brand").val(),
        year: $("#year").val(),
        description: $("#description").val(),
        category: {id: selected}
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax(
            'http://168.138.246.53/api/Motorbike/save',
            {
                type: "POST",
                data: dataToSend,
                dataType: 'JSON',
                contentType: "application/json; charset=utf-8",
                statusCode: {
                    201: function () {
                        alert("guardado!");
                        //$("#id").val("");
                        $("#name").val("");
                        $("#brand").val("");
                        $("#year").val("");
                        $("#description").val("");
                        $("#category").val("");
                        $("#result").empty();
                        getInformation();
                    }
                }
            });
}

function editElement(id) {
    $.ajax({
        url: "http://168.138.246.53/api/Motorbike/" + id,
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
                        $("#brand").val(answer.items[i].brand),
                        $("#model").val(answer.items[i].model),
                        $("#category_id").val(answer.items[i].category_id)
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
        brand: $("#brand").val(),
        model: $("#model").val(),
        category_id: $("#category_id").val()
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax(
            "http://168.138.246.53/api/Motorbike/update",
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
                        $("#brand").val("");
                        $("#model").val("");
                        $("#category_id").val("");
                        getInformation();
                    }
                }
            });
}

function deleteElement(idMoto) {
    let misDatos = {
        id: idMoto
    };
    let dataToSend = JSON.stringify(misDatos);
    $.ajax(
            'http://168.138.246.53/Motorbike/' + idMoto,
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

function paintSelect() {
    $.ajax({
        url: 'http://168.138.246.53/api/Category/all',
        type: 'GET',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",

        success: function (answer) {
            console.log(answer);
            $("#category").empty();
            mySelect = '<option id="" ></option>';
            for (i = 0; i < answer.length; i++) {
                mySelect += '<option value=' + answer[i].id + '>' + answer[i].name + ' ' + '(' + answer[i].description + ')' + '</option>';
            }
            $("#category").append(mySelect);

        },
        error: function (xhr, status) {
            alert('ha sucedido un problema:' + status);
        }
    });

}

