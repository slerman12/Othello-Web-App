<!DOCTYPE html>
<html lang="en">
<head>
  <title>OthelloWebApp</title>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script>

    var size = 8;
    var Board = [];
    var playerTurn = true;
    var playerClr = "B";

    Board[0] = ["_", "_", "_", "_", "_", "_", "_", "_"];
    Board[1] = ["_", "_", "_", "_", "_", "_", "_", "_"];
    Board[2] = ["_", "_", "x", "x", "x", "x", "_", "_"];
    Board[3] = ["_", "_", "x", "W", "B", "x", "_", "_"];
    Board[4] = ["_", "_", "x", "B", "W", "x", "_", "_"];
    Board[5] = ["_", "_", "x", "x", "x", "x", "_", "_"];
    Board[6] = ["_", "_", "_", "_", "_", "_", "_", "_"];
    Board[7] = ["_", "_", "_", "_", "_", "_", "_", "_"];

    function createBoard() {

      var element = $('.board');

      for(i = 0; i < size; i++) {
        for(j = 0; j < size; j++) {
          $('<span class="row' + i + 'col' + j + '">' + Board[i][j] + '</span>').data('row', i).data('col', j).appendTo(element);
          $('.row' + i + 'col' + j).on("click", function() {
            if(playerTurn) {
              //playerTurn = false;
              $.post("PlayerMove", {row: $.data(this, 'row'), col: $.data(this, 'col'), board: JSON.stringify(Board), clr: playerClr}, function (responseJson) {
                if (responseJson.valid) {
                  updateBoard(responseJson.board);
                  $.post("ComputerMove", {board: JSON.stringify(Board), clr: playerClr}, function (responseJson) {
                    //updateBoard(responseJson.board);
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

    function updateBoard(board){
      Board = board;
      for (i = 0; i < size; i++){
        for (j = 0; j < size; j++){
          $('.row' + i + 'col' + j).html(board[i][j]);
        }
      }
    }

    $(function() {
      createBoard();
    });

  </script>
  <style>
    .board span {
      float: left;
      width: 20px;
      text-align: center;
    }
  </style>
</head>
<body>
<div class="board"></div>
<br>
<div class="message"></div>
</body>
</html>