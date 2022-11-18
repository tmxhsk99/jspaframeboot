<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-11-18
  Time: 오후 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room</title>
    <script src="/webjars/jquery/dist/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
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
        var sock = new SockJS("/stomp-chat");
        var client = Stomp.over(sock); //1. SockJS를 내부에 들고 있는 client를 내어준다.

        // onopen : connection 이 맺어졌을 때 의 callback
        sock.onopen = function () {
            // send : connection 으로 message를 전달
            // connection이 맺어진 후 가입(join) 메시지 전달.
            sock.send(JSON.stringify(
                {
                    chatRoomId: roomId,
                    type: 'JOIN',
                    writer: member
                }
                ));
            // onmessage : message를 받았을 때의 callback
            sock.onmessage = function (e){
                const content = JSON.parse(e.data);
                chatBox.append('<li>' + content.message + '(' + content.writer + ')</li>');
            }
        }

        sendBtn.click(function (){
            const message = messageInput.val();
            sock.send(JSON.stringify(
                {
                    chatRoomId: roomId,
                    type: 'CHAT',
                    message: message,
                    writer: member
                }
                ));
            messageInput.val('');
        })
    });
</script>
</body>
</html>
