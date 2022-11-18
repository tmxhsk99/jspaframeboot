package com.kjh.jspaframeboot.repository;

import com.kjh.jspaframeboot.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>,ChatRoomRepositoryCustom {

}
