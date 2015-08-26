<!DOCTYPE html>
<html lang="en">
<head>
  <title>OthelloWebApp</title>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script>

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

    function createBoard() {

      var element = $('.board');

      for(i = 0; i < size; i++) {
        for(j = 0; j < size; j++) {
          $('<span class="row' + i + 'col' + j + '">' + Row[i][j] + '</span>').data('coordinates', i + " " + j).appendTo(element);
          $('.row' + i + 'col' + j).on("click", function() {
            alert($.data(this, 'coordinates'));
          });
        }
        $('<br><br>').appendTo(element);
      }
    }

    function updateBoard(coordinates){
      $('.row' + coordinates.row + 'col' + coordinates.col).html(coordinates.clr);
    }

    $(function() {
      createBoard();
      var object = {row: 7, col: 0, clr: "B"};
      updateBoard(object);
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
  <style>
    .board span {
      margin-right: 10px;
    }
  </style>
</head>
<body>
<div class="board"></div>
<br>
<div class="message"></div>
</body>
</html>