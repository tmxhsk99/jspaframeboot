package com.kjh.jspaframeboot.service;

import com.kjh.jspaframeboot.domain.ChatRoom;
import com.kjh.jspaframeboot.exception.ChatRoomNotFound;
import com.kjh.jspaframeboot.repository.ChatRoomRepository;
import com.kjh.jspaframeboot.request.ChatRoomCreate;
import com.kjh.jspaframeboot.request.ChatRoomSearch;
import com.kjh.jspaframeboot.response.ChatRoomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> getAllChatRooms() {
        List<ChatRoom> allRooms = chatRoomRepository.findAll();
        return allRooms;
    }

    public ChatRoomResponse getChatRoom(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id)
                .orElseThrow(() -> new ChatRoomNotFound());

        ChatRoomResponse response = ChatRoomResponse.builder()
                .id(chatRoom.getId())
                .maxChatRoomUser(chatRoom.getMaxChatRoomUser())
                .build();

        return response;
    }

    public Long establish(ChatRoomCreate chatRoomCreate) {
        ChatRoom chatRoom = ChatRoom.builder()
                .name(chatRoomCreate.getName())
                .maxChatRoomUser(chatRoomCreate.getMaxChatRoomUser())
                .build();
        return chatRoomRepository.save(chatRoom).getId();
    }

    public void delete(Long id){
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(() -> new ChatRoomNotFound());
        chatRoomRepository.delete(chatRoom);
    }

    public List<ChatRoomResponse> getList(ChatRoomSearch chatRoomSearch){
        return chatRoomRepository.getList(chatRoomSearch)
                .stream()
                .map(ChatRoomResponse::new)
                .collect(Collectors.toList());
    }

}
