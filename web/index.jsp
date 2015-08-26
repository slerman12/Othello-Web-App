<!DOCTYPE html>
<html lang="en">
<head>
  <title>OthelloWebApp</title>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script>
    //    $(document).ready(function() {
    //      for (var i = 0; i < 8; i++){
    //        for (var j = 0; j < 8; j++){
    //          $(".board").append('<span class="col' + i + 'row' + j + '\"\>X\</span>');
    //          $('.col' + i + 'row' + j).on("click", function() {
    //            alert(i + " " + j);
    //          });
    //        }
    //        $(".board").append("<br><br>");
    //      }
    //    });

    var size = 8;
    var Row = [];

    Row[0] = ["X", "X", "X", "X", "X", "X", "X", "X"];
    Row[1] = ["X", "X", "X", "X", "X", "X", "X", "X"];
    Row[2] = ["X", "X", "X", "X", "X", "X", "X", "X"];
    Row[3] = ["X", "X", "X", "B", "W", "X", "X", "X"];
    Row[4] = ["X", "X", "X", "W", "B", "X", "X", "X"];
    Row[5] = ["X", "X", "X", "X", "X", "X", "X", "X"];
    Row[6] = ["X", "X", "X", "X", "X", "X", "X", "X"];
    Row[7] = ["X", "X", "X", "X", "X", "X", "X", "X"];

    function UpdateBoard() {

      var element = $('.board');
      //element.empty();

      for(i = 0; i < size; i++) {
        for(j = 0; j < size; j++) {
          $('<span class="col' + i + 'row' + j + '">' + Row[i][j] + '</span>').data('id', i + " " + j).appendTo(element);
          $('.col' + i + 'row' + j).on("click", function() {
            alert($.data(this, 'id'));
          });
        }
        $('<br><br>').appendTo(element);
      }
    }

    $(function() {
      UpdateBoard();
    });


//    $(document).on("click", ".board span", function() {  // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
//      $.get("someservlet", function(responseJson) {    // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
//        var $ul = $("<ul>").appendTo($("#somediv")); // Create HTML <ul> element and append it to HTML DOM element with ID "somediv".
//        $.each(responseJson, function(index, item) { // Iterate over the JSON array.
//          $("<li>").text(item).appendTo($ul);      // Create HTML <li> element, set its text content with currently iterated item and append it to the <ul>.
//        });
//      });
//    });
  </script>
  <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="board">
  <%--<p class="row0"><span class="col0">X</span><span class="col1">X</span><span class="col2">X</span><span class="col3">X</span><span class="col4">X</span><span class="col5">X</span><span class="col6">X</span><span class="col7">X</span></p>--%>
  <%--<p class="row1"><span class="col0">X</span><span class="col1">X</span><span class="col2">X</span><span class="col3">X</span><span class="col4">X</span><span class="col5">X</span><span class="col6">X</span><span class="col7">X</span></p>--%>
  <%--<p class="row2"><span class="col0">X</span><span class="col1">X</span><span class="col2">X</span><span class="col3">X</span><span class="col4">X</span><span class="col5">X</span><span class="col6">X</span><span class="col7">X</span></p>--%>
  <%--<p class="row3"><span class="col0">X</span><span class="col1">X</span><span class="col2">X</span><span class="col3">W</span><span class="col4">B</span><span class="col5">X</span><span class="col6">X</span><span class="col7">X</span></p>--%>
  <%--<p class="row4"><span class="col0">X</span><span class="col1">X</span><span class="col2">X</span><span class="col3">B</span><span class="col4">W</span><span class="col5">X</span><span class="col6">X</span><span class="col7">X</span></p>--%>
  <%--<p class="row5"><span class="col0">X</span><span class="col1">X</span><span class="col2">X</span><span class="col3">X</span><span class="col4">X</span><span class="col5">X</span><span class="col6">X</span><span class="col7">X</span></p>--%>
  <%--<p class="row6"><span class="col0">X</span><span class="col1">X</span><span class="col2">X</span><span class="col3">X</span><span class="col4">X</span><span class="col5">X</span><span class="col6">X</span><span class="col7">X</span></p>--%>
  <%--<p class="row7"><span class="col0">X</span><span class="col1">X</span><span class="col2">X</span><span class="col3">X</span><span class="col4">X</span><span class="col5">X</span><span class="col6">X</span><span class="col7">X</span></p>--%>
</div>
<br>
<div class="message"></div>
</body>
</html>