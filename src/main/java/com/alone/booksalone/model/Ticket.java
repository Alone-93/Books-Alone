package com.alone.booksalone.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Ticket {
    private int id;
    private int user_id;
    private String ticket;
    private Date expired_at;//过期时间

}
