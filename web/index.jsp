<!DOCTYPE html>
<html lang="en">
<head>
  <title>OthelloWebApp</title>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script>

    var size = 8;
    var Row = [];
    var playerTurn = true;
    var playerClr = "B";

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
          $('<span class="row' + i + 'col' + j + '">' + Row[i][j] + '</span>').data('row', i).data('col', j).appendTo(element);
          $('.row' + i + 'col' + j).on("click", function() {
            if(playerTurn) {
              playerTurn = false;
              $.post("validPlayerMove", {row: $.data(this, 'row'), col: $.data(this, 'col'), boardState: Row, clr: playerClr}, function (responseJson) {
                if (responseJson.valid) {
                  updateBoard(responseJson);
                  $.post("computerMove", {boardState: Row, clr: playerClr}, function (responseJson) {
                    updateBoard(responseJson);
                    playerTurn = true;
                  });
                }
              });
            }
          });
        }
        $('<br>').appendTo(element);
      }
    }

    function updateBoard(coordinates){
      $('.row' + coordinates.row + 'col' + coordinates.col).html(coordinates.clr);
    }

    $(function() {
      createBoard();
    });

  </script>
  <style>
    .board span {
      margin-right: 5px;
    }
  </style>
</head>
<body>
<div class="board"></div>
<br>
<div class="message"></div>
</body>
</html>