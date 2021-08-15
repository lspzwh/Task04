package com.example.demo2;

import com.example.demo2.Util.DBUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserId {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String username = "root";
    private static final String password = "123456";
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,username,password);
            stmt = conn.createStatement();
            create();
            insert(new User(1,"a"));
            insert(new User(1,"a"));
            insert(new User(2,"b"));
            User user=find(1);
            if(user!=null){
//                Map<Object,Object> map= new HashMap<>();
//                map.put("id",user.id);
//                map.put("name",user.name);

                System.out.println(user.name);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            stmt.close();
        }
    }
    public static void create() throws SQLException {
        String sql="CREATE TABLE IF NOT EXISTS user"+
                "(id int(10) not NULL," +
                "name varchar(20))";
        Connection connection= DBUtil.open();
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection);
        }
    }
    public static boolean insert(User user)throws SQLException{
        Connection connection=DBUtil.open();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from user");
        while (resultSet.next()){
            if(resultSet.getInt("id")==user.id)return false;
        }
        String sql="INSERT INTO user(id,name) VALUES(?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setObject(1,user.id);
        preparedStatement.setObject(2,user.name);
        preparedStatement.executeUpdate();
        DBUtil.close(connection);
        statement.close();
        preparedStatement.close();
        return true;
    }
    public static User find(int id)throws SQLException{
        Connection connection=DBUtil.open();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from user");
        while(resultSet.next()){
            if(resultSet.getInt("id")==id){
                return new User(id,resultSet.getString("name"));
            }
        }
        DBUtil.close(connection);
        statement.close();
        resultSet.close();
        return null;
    }
    static class User{
        int id;
        String name;
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
