package com.alone.booksalone.dao;


import com.alone.booksalone.model.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TicketDAO {
    @Insert({"INSERT INTO ticket(user_id,ticket,expired_at)VALUES(#{ticket.user_id},#{ticket.ticket},#{ticket.expired_at})"})
    int addTicket(@Param("ticket") Ticket ticket);
    @Delete({"DELETE FROM ticket where id=#{id}"})
    int deleteTicketById(int id);
    @Delete({"DELETE FROM ticket where ticket=#{t}"})
    int deleteTicket(String t);
    @Select({"SELECT id,user_id,ticket,expired_at FROM ticket"})
    List<Ticket> selectAll();//查询所有
    @Select({"SELECT  id,user_id,ticket,expired_at FROM ticket where ticket=#{t}"})
    Ticket selectByTicket(String t);
    @Select({"SELECT  id,user_id,ticket,expired_at FROM ticket where user_id=#{userId}"})
    Ticket selectByUserId(int userId);
}
