<!DOCTYPE html>
<html lang="en">
<head>
  <title>OthelloWebApp</title>
  <style>
    html {
      height: 100%;
    }
    body {
      height: 100%;
    }
  </style>
  <link rel="stylesheet" type="text/css" href="othelloStyles.css">
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="othello.js"></script>
</head>
<body>
<div id="othelloApp">
  <div class="container">
    <div id="othelloBoard">
      <div class="menu">
        <h3>Choose your color:</h3>
        <div class="choice-container">
          <div class="piece-container left">
            <div class="black-piece"></div>
          </div>
          <div class="piece-container right">
            <div class="white-piece"></div>
          </div>
        </div>
        <h3>Choose a difficulty:</h3>
        <div class="difficulty">
          <button class="easy active">Easy</button>
          <button class="medium">Medium</button>
          <button class="hard">Hard</button>
        </div>
      </div>
      <div class="notification default-notification"><a class="close">&#10005;</a><span></span></div>
    </div>
    <div id="othelloSettings">
      <div class="main">
        <button class="start-over">Start Over</button>
        <button class="skip-turn">Skip Turn</button>
      </div>
      <div class="difficulty">
        <button class="easy active">Easy</button>
        <button class="medium">Medium</button>
        <button class="hard">Hard</button>
      </div>
      <%--<div class="stats">--%>
      <%--<h3>Choose your color:</h3>--%>
      <%--<div class="choice-container">--%>
      <%--<div class="piece-container">--%>
      <%--<div class="black-piece"></div>--%>
      <%--</div>--%>
      <%--<div class="text"></div>--%>
      <%--</div>--%>
      <%--<div class="choice-container">--%>
      <%--<div class="piece-container">--%>
      <%--<div class="white-piece"></div>--%>
      <%--</div>--%>
      <%--<div class="text"></div>--%>
      <%--</div>--%>
      <%--</div>--%>
    </div>
    <div id="othelloInfo"><strong>i</strong></div>
  </div>
</div>
</body>
</html>