var size = 8;
var Board = [];
var EmptySquares = [];
var playerTurn = false;
var selectedPlayerClr = "B";
var playerClr = "B";
var difficulty = 1;
var blackScr = 0;
var whiteScr = 0;
var scrTimeout = null;

var playerMovePromise = null;
var computerMovePromise = null;
var skipTurnPromise = null;

var Notification = (function() {
    "use strict";

    var elem,
        hideHandler,
        that = {};

    that.init = function(options) {
        elem = $(options.selector);
    };

    that.show = function(text, hideSelector, autoHide, on) {
        clearTimeout(hideHandler);
        elem.find("span").html(text);
        elem.delay(200).fadeIn();
        that.on = on;

        if (autoHide) {
            hideHandler = setTimeout(function () {
                elem.fadeOut();
                that.on = "off";
            }, 4200);
        }

        if(hideSelector) {
            $(hideSelector).on('click', function () {
                clearTimeout(hideHandler);
                elem.fadeOut();
                that.on = "off";
            })
        }
    };

    that.hide = function() {
        if(that.on && (that.on !== "off")){
            clearTimeout(hideHandler);
            elem.fadeOut();
            that.on = "off";
        }
    };

    return that;
}());

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

    var element = $('#othelloBoard');

    for(i = 0; i < size; i++) {
        for(j = 0; j < size; j++) {
            if((i+j)%2 === 0) {
                if (Board[i][j] === 'x' || Board[i][j] === '_') {
                    $('<div class="square even row' + i + 'col' + j + '"><div class="empty"></div></div>').data('row', i).data('col', j).appendTo(element);
                }
                else if (Board[i][j] === 'B') {
                    blackScr++;
                    $('<div class="square even row' + i + 'col' + j + '"><div class="black"></div></div>').data('row', i).data('col', j).appendTo(element);
                }
                else if (Board[i][j] === 'W') {
                    whiteScr++;
                    $('<div class="square even row' + i + 'col' + j + '"><div class="white"></div></div>').data('row', i).data('col', j).appendTo(element);
                }
            }
            else {
                if (Board[i][j] === 'x' || Board[i][j] === '_') {
                    $('<div class="square odd row' + i + 'col' + j + '"><div class="empty"></div></div>').data('row', i).data('col', j).appendTo(element);
                }
                else if (Board[i][j] === 'B') {
                    blackScr++;
                    $('<div class="square odd row' + i + 'col' + j + '"><div class="black"></div></div>').data('row', i).data('col', j).appendTo(element);
                }
                else if (Board[i][j] === 'W') {
                    whiteScr++;
                    $('<div class="square odd row' + i + 'col' + j + '"><div class="white"></div></div>').data('row', i).data('col', j).appendTo(element);
                }
            }

            $('.row' + i + 'col' + j).on("click", function() {
                if(playerTurn) {
                    playerTurn = false;
                    playerMovePromise = $.post("PlayerMove", {row: $.data(this, 'row'), col: $.data(this, 'col'), board: JSON.stringify(Board), emptySpaces: JSON.stringify(EmptySquares), clr: playerClr}, function (responsePlayer) {
                        if (responsePlayer.valid) {
                            updateBoard(responsePlayer.board, responsePlayer.emptySpaces);
                            computerTurn();
                        }
                        else{
                            playerTurn = true;
                        }
                    });
                }
            });
        }
    }
    $('#othelloSettings .choice-container .left .score .text').html(blackScr);
    $('#othelloSettings .choice-container .right .score .text').html(whiteScr);
}

function updateBoard(board, emptySpaces){
    blackScr = 0;
    whiteScr = 0;
    Board = board;
    EmptySquares = emptySpaces;
    for (i = 0; i < size; i++){
        for (j = 0; j < size; j++){
            if(Board[i][j] === 'x' || Board[i][j] === '_') {
                $('.row' + i + 'col' + j).html('<div class="empty"></div>');
            }
            else if (Board[i][j] === 'B') {
                blackScr++;
                $('.row' + i + 'col' + j).html('<div class="black"></div>');
            }
            else if (Board[i][j] === 'W') {
                whiteScr++;
                $('.row' + i + 'col' + j).html('<div class="white"></div>');
            }
        }
    }
    clearTimeout(scrTimeout);
    scrTimeout = setTimeout(function(){
        $('#othelloSettings .choice-container .left .score .text').html(blackScr);
        $('#othelloSettings .choice-container .right .score .text').html(whiteScr);
    }, 200);
}

function configureWidthHeight(){
    var w = $('#othelloApp').width();
    var h = $('#othelloApp').height();

    if (w < h) {
        $('#othelloBoard').css({'height': Math.floor(0.8 * w / 8)*8 + 'px'});
        $('#othelloBoard').css({'width': Math.floor(0.8 * w / 8)*8 + 'px'});
    }
    else{
        $('#othelloBoard').css({'height': Math.floor(0.8 * h / 8)*8 + 'px'});
        $('#othelloBoard').css({'width': Math.floor(0.8 * h / 8)*8 + 'px'});
    }
}

function startOver() {
    var NewBoard = [];
    var NewEmptySquares = [];

    NewBoard[0] = ["_", "_", "_", "_", "_", "_", "_", "_"];
    NewBoard[1] = ["_", "_", "_", "_", "_", "_", "_", "_"];
    NewBoard[2] = ["_", "_", "x", "x", "x", "x", "_", "_"];
    NewBoard[3] = ["_", "_", "x", "W", "B", "x", "_", "_"];
    NewBoard[4] = ["_", "_", "x", "B", "W", "x", "_", "_"];
    NewBoard[5] = ["_", "_", "x", "x", "x", "x", "_", "_"];
    NewBoard[6] = ["_", "_", "_", "_", "_", "_", "_", "_"];
    NewBoard[7] = ["_", "_", "_", "_", "_", "_", "_", "_"];

    NewEmptySquares[0] = {X: 2, Y: 2};
    NewEmptySquares[1] = {X: 2, Y: 3};
    NewEmptySquares[2] = {X: 2, Y: 4};
    NewEmptySquares[3] = {X: 2, Y: 5};
    NewEmptySquares[4] = {X: 3, Y: 5};
    NewEmptySquares[5] = {X: 4, Y: 5};
    NewEmptySquares[6] = {X: 5, Y: 5};
    NewEmptySquares[7] = {X: 5, Y: 4};
    NewEmptySquares[8] = {X: 5, Y: 3};
    NewEmptySquares[9] = {X: 5, Y: 2};
    NewEmptySquares[10] = {X: 4, Y: 2};
    NewEmptySquares[11] = {X: 3, Y: 2};

    updateBoard(NewBoard, NewEmptySquares);
}

function cancelRequests() {

    if(playerMovePromise) {
        playerMovePromise.abort();
        playerMovePromise = null;
    }

    if(computerMovePromise) {
        computerMovePromise.abort();
        computerMovePromise = null;
    }

    if(skipTurnPromise) {
        skipTurnPromise.abort();
        skipTurnPromise = null;
    }
}

function computerTurn() {
    switchTurn();
    computerMovePromise = $.post("ComputerMove", {board: JSON.stringify(Board), emptySpaces: JSON.stringify(EmptySquares), clr: playerClr, difficulty: difficulty}, function (responseComputer) {
        updateBoard(responseComputer.board, responseComputer.emptySpaces);
        playerTurn = true;
        switchTurn();
        if(responseComputer.gameOver){
            Notification.init({selector: '#othelloBoard .default-notification'});
            if((playerClr === "B" && blackScr > whiteScr) || (playerClr === "W" && whiteScr > blackScr)) {
                Notification.show("<strong>Congratulations, you win!</strong> ", '#othelloBoard .default-notification .close', false, "info");
            }
            else{
                Notification.show("<strong>Sorry, computer wins. Better luck next time!</strong> ", '#othelloBoard .default-notification .close', false, "info");
            }
        }
    });
}

function skipTurn(){
    if(playerTurn) {
        skipTurnPromise = $.post("SkipTurn", {
            board: JSON.stringify(Board),
            emptySpaces: JSON.stringify(EmptySquares),
            clr: playerClr
        }, function (responseSkipTurn) {
            if (responseSkipTurn.valid) {
                playerTurn = false;
                computerTurn();
            }
            else {
                Notification.init({selector: '#othelloBoard .default-notification'});
                Notification.show("You can only skip a turn when you have no valid move.", '#othelloBoard .default-notification .close', true, "skip-turn");
            }
        });
    }
}

function switchTurn() {
    if($('#othelloSettings .choice-container .right').hasClass('active')){
        $('#othelloSettings .choice-container .right').removeClass('active');
        $('#othelloSettings .choice-container .left').addClass('active');
    }
    else{
        $('#othelloSettings .choice-container .left').removeClass('active');
        $('#othelloSettings .choice-container .right').addClass('active');
    }
}

function menu(){
    $('#othelloBoard .menu .choice-container .left').on('click', function(){
        selectedPlayerClr = "B";
        $('#othelloBoard .menu .choice-container .right').removeClass('active');
        $('#othelloBoard .menu .choice-container .left').addClass('active');
    });
    $('#othelloBoard .menu .choice-container .right').on('click', function(){
        selectedPlayerClr = "W";
        $('#othelloBoard .menu .choice-container .left').removeClass('active');
        $('#othelloBoard .menu .choice-container .right').addClass('active');
    });

    $('#othelloBoard .menu .difficulty .easy').on('click', function() {
        difficulty = 1;
        $('#othelloSettings .difficulty .active').removeClass('active');
        $('#othelloSettings .difficulty .easy').addClass('active');
        $('#othelloBoard .menu .difficulty .active').removeClass('active');
        $('#othelloBoard .menu .difficulty .easy').addClass('active');
    });
    $('#othelloBoard .menu .difficulty .medium').on('click', function() {
        difficulty = 3;
        $('#othelloSettings .difficulty .active').removeClass('active');
        $('#othelloSettings .difficulty .medium').addClass('active');
        $('#othelloBoard .menu .difficulty .active').removeClass('active');
        $('#othelloBoard .menu .difficulty .medium').addClass('active');
    });
    $('#othelloBoard .menu .difficulty .hard').on('click', function() {
        difficulty = 6;
        $('#othelloSettings .difficulty .active').removeClass('active');
        $('#othelloSettings .difficulty .hard').addClass('active');
        $('#othelloBoard .menu .difficulty .active').removeClass('active');
        $('#othelloBoard .menu .difficulty .hard').addClass('active');
    });

    $('#othelloBoard .menu .start-game button').on('click', function(){
        playerClr = selectedPlayerClr;
        cancelRequests();
        startOver();
        $('#othelloSettings .choice-container .active').removeClass('active');
        $('#othelloSettings .choice-container .left').addClass('active');
        if (playerClr === "W"){
            $('#othelloSettings .choice-container .right').addClass('active');
            $('#othelloSettings .choice-container .right .player').html('You');
            $('#othelloSettings .choice-container .left .player').html('Computer');
            computerTurn();
        }
        else{
            $('#othelloSettings .choice-container .left').addClass('active');
            $('#othelloSettings .choice-container .left .player').html('You');
            $('#othelloSettings .choice-container .right .player').html('Computer');
            playerTurn = true;
        }
        $('#othelloBoard .menu').fadeOut();
        $('#othelloSettings').css('visibility','visible');
    });
    $('#othelloBoard .menu .close').on('click', function() {
        $('#othelloBoard .menu').fadeOut();
        $('#othelloSettings').css('visibility','visible');
    });
}

function settings() {
    $('#othelloSettings .difficulty .easy').on('click', function() {
        difficulty = 1;
        $('#othelloSettings .difficulty .active').removeClass('active');
        $('#othelloSettings .difficulty .easy').addClass('active');
    });
    $('#othelloSettings .difficulty .medium').on('click', function() {
        difficulty = 3;
        $('#othelloSettings .difficulty .active').removeClass('active');
        $('#othelloSettings .difficulty .medium').addClass('active');
    });
    $('#othelloSettings .difficulty .hard').on('click', function() {
        difficulty = 6;
        $('#othelloSettings .difficulty .active').removeClass('active');
        $('#othelloSettings .difficulty .hard').addClass('active');
    });

    $('#othelloSettings .main .start-over').on('click', function() {
        $('#othelloSettings').css('visibility','hidden');
        Notification.hide();
        $('#othelloBoard .menu .close').css('visibility','visible');
        $('#othelloBoard .menu').fadeIn();
    });

    $('#othelloSettings .skip-turn').on('click', function() {
        skipTurn();
    });
}

$(function() {
    $('#othelloBoard .notification').hide();
    $('#othelloSettings').css('visibility','hidden');

    menu();
    createBoard();
    settings();

    configureWidthHeight();
    $( window ).resize(function() {
        configureWidthHeight();
    });

    $('#othelloInfo').on('click', function() {
        if(Notification.on === "info"){
            Notification.hide();
        }
        else {
            Notification.init({selector: '#othelloBoard .default-notification'});
            Notification.show("This is a web-based Othello board game that works natively in all browsers and on all devices. The AI for the game is run on a custom Java servlet. It uses alpha beta pruning to decide a best move and looks ahead a configurable depth (Easy: depth = 1, Medium: depth = 3, Hard: depth = 6). The GUI is fully responsive and implemented entirely with Javascript and CSS. This was primarily created by Sam Lerman, who set up the backend, created this website and GUI, and made most of the game engine. The AI for the game was created by Sam Lerman, Daniel Harris, and Charlie Norvell collaboratively for a university project.", '#othelloBoard .default-notification .close', false, "info");
        }
    });
});