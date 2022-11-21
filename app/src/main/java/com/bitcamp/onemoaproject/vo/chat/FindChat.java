package com.bitcamp.onemoaproject.vo.chat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FindChat {
    private int buyer;
    private int seller;
    private int ChatNo;

    FindChat() {
        this.buyer = 0;
        this.seller =0;
        this.ChatNo = 0;
    }
}
