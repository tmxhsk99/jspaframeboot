/**
 * 로그인 검증 함수
 */
const loginValiate = () => {
    let id = $('#id');
    let pwd = $('#pwd');

    if(id.val() == "" || id.val() == null){
        alert("아이디를 입력하세요");
        id.focus();
        return false;
    }

    if (pwd.val() == "" || pwd.val() == null) {
        alert("비밀번호를 입력하세요");
        pwd.focus();
        return false;
    }



}


//DOM Load after script start
$(function (){
    loginValiate();

});
//DOM Load after script end
