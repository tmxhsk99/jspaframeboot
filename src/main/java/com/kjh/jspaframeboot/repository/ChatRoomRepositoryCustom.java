package com.kjh.jspaframeboot.repository;

import com.kjh.jspaframeboot.domain.ChatRoom;
import com.kjh.jspaframeboot.request.ChatRoomSearch;

import java.util.List;

public interface ChatRoomRepositoryCustom {
    List<ChatRoom> getList(ChatRoomSearch chaRoomSearch);
}
