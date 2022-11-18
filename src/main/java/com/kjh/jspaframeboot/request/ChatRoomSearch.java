package com.kjh.jspaframeboot.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatRoomSearch {
    private static final int MAX_SIZE = 2000;

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer size = 10;

    public long getOffset(){
        return (long)(Math.max(1,page) - 1) * Math.min(size,MAX_SIZE);
    }

}
