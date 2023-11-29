//c34e4ae894a6c864e4db84aeb5b56d78

window.Kakao.init("c34e4ae894a6c864e4db84aeb5b56d78");

function kakaologin(){
	window.Kakap.Auth.login({
		scope:'profile_nickname, account_email',
		success: function(authObj) {
			console.log(authObj);
			window.Kakao.API.request({
				url:'/v2/user/me',
				success : res => {
					const kakao_account = res.kakao_account;
					console.log(kakao_account);
				}
			});
		}
	});
}