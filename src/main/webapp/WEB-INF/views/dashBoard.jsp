<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-11-17
  Time: 오후 4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room</title>
    <script src="/webjars/jquery/dist/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js" integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
<h1>${room.name} ${room.id}</h1>
<div class="content" data-room-id="${room.id}" data-member="${member}">
    <ul class="chat_box">
    </ul>
    <input name="message">
    <button class="send">보내기</button>
</div>
<script>
    $(function () {
        let chatBox = $('.chat_box');
        let messageInput = $('input[name="message"]');
        let sendBtn = $('.send');
        let roomId = $('.content').data('room-id');
        let member = $('.content').data('member');

        // handshack
        let sock = new SockJS("/dashboard-data");
        // 1. SockJS를 내부에 들고 있는 client를 내어준다.
        let client = Stomp.over(sock);

        // 2. connection이 맺어지면 실행된다.
        client.connect({}, function () {
            // 3. send(path, header, message)로 메시지를 보낼 수 있다.
            client.send('/publish/chat/join',{},JSON.stringify({chatRoomId: roomId, writer: member}));
            // 4. subscribe(path, callback)로 메시지를 받을 수 있다. callback 첫번째 파라미터의 body로 메시지의 내용이 들어온다.
            client.subscribe('/subscribe/chat/room/' + roomId,function(chat){
                let content = JSON.parse(chat.body);
                chatBox.append('<li>' + content.message + '(' + content.writer + ')</li>');
            })
        })

        sendBtn.click(function (){
            const message = messageInput.val();
            client.send('/publish/chat/message',{}, JSON.stringify({chatRoomId: roomId, message: message, writer: member}));
            messageInput.val('');
        })
    });
</script>
</body>
</html>
