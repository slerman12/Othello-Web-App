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
    <div id="othelloBoard"></div>
    <div id="othelloSettings">
      <div class="main">
        <button class="start-over">Start Over</button>
        <button class="skip-turn">Skip Turn</button>
      </div>
      <div class="notification invalid-skip-notification"><span></span></div>
      <div class="difficulty">
        <button class="easy active">Easy</button>
        <button class="medium">Medium</button>
        <button class="hard">Hard</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>