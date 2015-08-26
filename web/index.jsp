<!DOCTYPE html>
<html lang="en">
<head>
  <title>OthelloWebApp</title>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script>

    var size = 8;
    var Board = [];
    var EmptySquares = [];
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

    EmptySquares[0] = {X: 2, Y: 2};
    EmptySquares[1] = {X: 2, Y: 3};
    EmptySquares[2] = {X: 2, Y: 4};
    EmptySquares[3] = {X: 2, Y: 5};
    EmptySquares[4] = {X: 3, Y: 5};
    EmptySquares[5] = {X: 4, Y: 5};
    EmptySquares[6] = {X: 5, Y: 5};
    EmptySquares[7] = {X: 5, Y: 4};
    EmptySquares[8] = {X: 5, Y: 3};
    EmptySquares[9] = {X: 5, Y: 2};
    EmptySquares[10] = {X: 4, Y: 2};
    EmptySquares[11] = {X: 3, Y: 2};

    function createBoard() {

      var element = $('.board');

      for(i = 0; i < size; i++) {
        for(j = 0; j < size; j++) {
          $('<span class="row' + i + 'col' + j + '">' + Board[i][j] + '</span>').data('row', i).data('col', j).appendTo(element);
          $('.row' + i + 'col' + j).on("click", function() {
            if(playerTurn) {
              //playerTurn = false;
              $.post("PlayerMove", {row: $.data(this, 'row'), col: $.data(this, 'col'), board: JSON.stringify(Board), emptySpaces: JSON.stringify(EmptySquares), clr: playerClr}, function (responsePlayer) {
                if (responsePlayer.valid) {
                  updateBoard(responsePlayer.board, responsePlayer.emptySpaces);
                  $.post("ComputerMove", {board: JSON.stringify(Board), emptySpaces: JSON.stringify(EmptySquares), clr: playerClr}, function (responseComputer) {
                    updateBoard(responseComputer.board, responseComputer.emptySpaces);
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

    function updateBoard(board, emptySpaces){
      Board = board;
      EmptySquares = emptySpaces;
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