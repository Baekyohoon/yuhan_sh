document.getElementById('signupButton').addEventListener('click', function() {
        window.location.href = '/signup2'; // 이동할 페이지 경로를 설정
    });
    


    

    
 window.Kakao.init("c34e4ae894a6c864e4db84aeb5b56d78");

  // 카카오 로그인 버튼 클릭 이벤트 핸들러
  document.getElementById('kakao-login-btn').addEventListener('click', function() {
    Kakao.Auth.login({
      success: function(authObj) {
        // 카카오 로그인 성공 시의 처리
        // authObj.access_token를 서버로 전송하여 서버에서 회원가입 처리
        sendTokenToServer(authObj.access_token);
      },
      fail: function(err) {
        // 카카오 로그인 실패 시의 처리
        console.log(err);
      },
    });
  });
  
  function kakakologin(){
	  window.Kakao.Auth.login({
		  scope:'profile_nickname, account_email',
		  success:function(authObj) {
			  console.log(authObj);
			  window.Kakao.API.request({
				  url:'/v2/user/me',
				  success: res =>{
					  const kakao_account = res.kakao_account;
					  console.log(kakao_account);
				  }
			  });
		  }
	  });
  }

