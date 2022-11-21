package com.bitcamp.onemoaproject.vo.chat;

import com.bitcamp.onemoaproject.vo.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {

    private String roomId;
    private String roomName;
    private int roomNo;


    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        room.roomNo = 0;
        return room;
    }
}