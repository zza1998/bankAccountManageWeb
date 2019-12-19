package com.zza.jpaa.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageVo {

    private String userId;

    private String msg;

    private String type;

    private List<String> users;
}
