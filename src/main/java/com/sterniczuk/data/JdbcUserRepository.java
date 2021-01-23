package com.sterniczuk.data;

import com.sterniczuk.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate jdbc;

    public JdbcUserRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;

    }

    @Override
    public boolean insertNewUser(User user, String token) throws  Exception{


            user.setCreatedAt(new Date().toString());
            user.setIdStatus("0");
            jdbc.update("Insert into Users(NIP,eMail,password,companyName, REGON, address, idStatus, createdAt) values(?,?,?,?,?,?,?,?) ",
                    user.getNIP(),
                    user.getEMail(),
                    user.getPassword(),
                    user.getCompanyName(),
                    user.getREGON(),
                    user.getAddress(),
                    user.getIdStatus(),
                    user.getCreatedAt());
            String id = (String)jdbc.queryForObject("Select idUsers from Users where NIP=?",new Object[] {user.getNIP()},String.class);

            jdbc.update("insert into activateToken(idUsers, token)  VALUES (?,?)",
                    id, token);
    return true;
    }

    @Override
    public void activateNewUser(int id) {

        jdbc.update("Update Users set IdStatus='1' where IdUsers=?", id);
    }

    @Override
    public User getUser(String NIP) throws SQLException {


       return  jdbc.queryForObject("Select * from Users where NIP= ?",this::mapToUser,NIP);

    }



    @Override
    public int checkOneDataInDataBase(String table, String  attribute, String value){

        try{
            final String sql= "Select IdUsers, " +  attribute +  " from " +  table + " where " + attribute +" =?";
            Map<String,Object> map = new HashMap<>();
            map = jdbc.queryForMap(sql,value);
                if(map.get(attribute).toString().equals(value))
                    return Integer.parseInt(map.get("idUsers").toString());
                else
                    return -1;
        }catch (Exception e){
            log.info(e.getMessage());
            return -2;
        }
    }

    private User mapToUser(ResultSet rs,int rowNum) throws SQLException {


        User user =  new User();
             user.setId(rs.getLong("idUsers"));
            user.setCreatedAt(rs.getString("createdAt"));
            user.setNIP(rs.getString("NIP"));
            user.setCompanyName(rs.getString("companyName"));
            user.setREGON(rs.getString("REGON"));
            user.setAddress(rs.getString("address"));
            user.setIdStatus("1");
        return user;
    }
}
