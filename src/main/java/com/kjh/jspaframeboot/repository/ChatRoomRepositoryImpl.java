package com.kjh.jspaframeboot.repository;

import com.kjh.jspaframeboot.domain.ChatRoom;
import com.kjh.jspaframeboot.domain.QChatRoom;
import com.kjh.jspaframeboot.request.ChatRoomSearch;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom{

    private final JPQLQueryFactory jpqlQueryFactory;

    @Override
    public List<ChatRoom> getList(ChatRoomSearch chatRoomSearch) {
        return jpqlQueryFactory.selectFrom(QChatRoom.chatRoom)
                .limit(chatRoomSearch.getSize())
                .offset(chatRoomSearch.getOffset())
                .orderBy(QChatRoom.chatRoom.id.desc())
                .fetch();
    }
}
