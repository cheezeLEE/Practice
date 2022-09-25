<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>타이머</title>
</head>
<body>
  JavaScript Timer Test
  <br/><br/>
  <div>
    <span class="set-time"></span>
    <button type="button" id="resetBtn">초기화</button>
  </div>

  <script src="static/js/jquery3.6.1.js"></script>
  <script>
    let timer;

    $(function(){
      Timer(60);
    });

    /**
     *
     * @param {number} second 실행간격
     * @param {function(callback, number):void} startInterval
     */
    function startInterval(seconds, callback) {
      callback();
      return setInterval(callback, seconds * 1000);
    }

    /**
     * JavaScript Timer : 매 초마다 시간을 변경하고, 초기화시 처음부터 다시 시작하는 함수
     * @param {number} time 기준시간 (초)
     * @param {number} idx 몇번째 타이머 (0부터)  
     */ 
    function Timer(time){
      let timeSet = $(".set-time");
      time--;

      timer = startInterval(1,function(){
        min = parseInt(time/60);
        sec = time%60;
        if(sec >= 0 && sec < 10){
          timeSet.text(min + ":0" + sec);
        }else{
          timeSet.text(min + ":" + sec);
        }
        time--;

        if(time<0){
          clearInterval(timer);
          alert("시간초과");
        }
      });
    }

    $("#resetBtn").on("click", function(){
      clearInterval(timer);
      console.log("타이머 초기화");
      Timer(60);
    });

  </script>
</body>
</html>