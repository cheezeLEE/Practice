/**
 * 회원가입 공통코드
 */
 
/**
 * 인증번호 전송 (이메일)
 * @param {String} address 이메일을 전송할 주소
 * @requires validBtn 인증번호 버튼, validDiv 인증번호 입력필드
 */
function emailSend(address){
	$.ajax({
		url: "/mailJoin2",
		type: "post",
		data: {"address": address},
		success: function(data){
			Timer(300);
			$("#validBtn").css("display", "none");
			$("#validDiv").css("display", "block");
			alert("인증번호를 전송했습니다.");
		}
	});	
}

let timer, address;

/**
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


/* [aes 256 인코딩, 디코딩에 필요한 전역 변수 선언] */
let aes256SecretKey = "0123456789abcdef0123456789abcdef"; // key 값 32 바이트
let aes256Iv = "0123456789abcdef"; //iv 16 바이트
let aes256EncodeData = "";
let aes256DecodeData = "";

/**
 * 인코딩 함수 aes128Encode 정의
 * @param {String} secretKey key값 32바이트
 * @param {String} Iv Initialization Vector 첫 블록을 암호화할 때 사용되는 값
 * @param {String} data 인코딩할 데이터
 */
function aes256Encode(secretKey, Iv, data){
	// [aes 인코딩 수행 실시 : cbc 모드]
	const cipher = CryptoJS.AES.encrypt(data, CryptoJS.enc.Utf8.parse(secretKey), {
		iv: CryptoJS.enc.Utf8.parse(Iv), // [Enter IV (Optional) 지정 방식]
		padding: CryptoJS.pad.Pkcs7,
		mode: CryptoJS.mode.CBC // [cbc 모드 선택]
	});
	// [인코딩 된 데이터 확인 실시]
	aes256EncodeData = cipher.toString();
};


/**
 * 디코딩 함수 aes256Decode 정의
 * @param {String} secretKey key값 32바이트
 * @param {String} Iv Initialization Vector 첫 블록을 암호화할 때 사용되는 값
 * @param {String} data 디코딩할 데이터
 */
function aes256Decode(secretKey, Iv, data){
	// [aes 디코딩 수행 실시 : cbc 모드]
	const cipher = CryptoJS.AES.decrypt(data, CryptoJS.enc.Utf8.parse(secretKey), {
		iv: CryptoJS.enc.Utf8.parse(Iv), // [Enter IV (Optional) 지정 방식]
		padding: CryptoJS.pad.Pkcs7,
		mode: CryptoJS.mode.CBC // [cbc 모드 선택]
	});
	// [인코딩 된 데이터 확인 실시]
	aes256DecodeData = cipher.toString(CryptoJS.enc.Utf8);    		
};
