function makeMove(cell) {
    var row = cell.getAttribute("data-row");
    var col = cell.getAttribute("data-col");
    var message = document.getElementById("message");
    console.log(row);
    console.log(col);

    $.ajax({
        type: "POST",
        url: "/playerMove",
        data: {"row": row, "col":col, "message":message.innerHTML },
        dataType: "json",
        success: function(response){
            cell.innerHTML = 'X';
            console.log(response)
            message.innerHTML = response.displayMessage;

            var cpuColNum = response.cpuColNum;
            var cpuRowNum = response.cpuRowNum;

            var selector = 'td[data-row=' + '"' + cpuRowNum + '"' + ']' + '[data-col=' + '"' + cpuColNum + '"' + ']';
            console.log('selector string:'+selector);
            // access the element
            var element = document.querySelector(selector).innerHTML = 'O';
        },
        error: function(jqXHR){
            console.log("jqXHR: "+jqXHR);
            alert(jqXHR.responseText);
        }
    })
}

function resetGame(){
    location.reload(true);
}

function refreshPage(){

    $.ajax({
            type: "GET",
            url: "/resetPositions"
        })
    return "before refresh alert"
}