let index = {
  init: function () {
    $("#btn-save").on("click", () => { // function() {}대신에 () => {}를 사용한 이유는 this 바인딩을 위함
      this.save();
    });
    $("#btn-update").on("click", () => {
      this.update();
    });
  },
  
  save: function () {
    // alert('userl save함수 호출됨');
    let data = {
      username: $("#username").val(),
      password: $("#password").val(),
      email: $("#email").val(),
    };

    //console.log(data);

    // ajax 호출 시 default가 비동기 호출
    // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
    $.ajax({
      // 회원가입 수행 요청 성공 시 done 호출, 실패 시 fail 호출
      type: "POST",
      url: "/auth/joinProc",
      data: JSON.stringify(data), // http body데이터
      contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
      dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(String)인데 생긴게 json이라면 => javascript오브젝트로 변경
    }).done(function (resp) {
      if(resp.status === 500){
        alert("사용중인 아이디입니다.");
      }else{
        alert("회원가입이 완료되었습니다.");
        location.href = "/";
      }



    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },

  update: function() {
    let data = {
        id: $("#id").val(),
        username : $("#username").val(),
        password: $("#password").val(),
        email: $("#email").val()
    };
    $.ajax({
        type:"PUT",
        url:"/user",
        data: JSON.stringify(data),
        contentType:"application/json; charSet=utf-8",
        dataType:"json"

    }).done(function(resp){
        alert("회원수정이 완료되었습니다.");
        location.href = "/";

    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}
}

index.init();