package com.example.assignment2.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Account {
    int id;
    int user_id;
    int account_number;
    String username;
    String currency;
    double balance;
    String creation_date;

    public Account() {
    }

    public Account(int user_id, int account_number, String username, String currency, double balance, String creation_date) {
        this.user_id = user_id;
        this.account_number = account_number;
        this.username = username;
        this.currency = currency;
        this.balance = balance;
        this.creation_date = creation_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
    public int save() throws SQLException, ClassNotFoundException {
        Connection conn=DB.getInstance().getConnection();
        PreparedStatement ps=null;
        int recordCount=0;
        String insert="INSERT INTO `accounts`(`user_id`, `account_number`, `username`, `currency`, `balance`, `creation_date`) VALUES (?,?,?,?,?,?)";
        ps=conn.prepareStatement(insert);
        ps.setInt(1,this.getUser_id());
        ps.setInt(2,this.getAccount_number());
        ps.setString(3,this.getUsername());
        ps.setString(4,this.getCurrency());
        ps.setDouble(5,this.getBalance());
        ps.setString(6,this.getCreation_date());
       recordCount=ps.executeUpdate();
       if(ps!=null){
           ps.close();
       }
     conn.close();
       return recordCount;
    }
    public static  ArrayList<Account> getAllAccount() throws SQLException {
        Connection conn=DB.getInstance().getConnection();
        PreparedStatement ps=null;
        ArrayList<Account> accounts=new ArrayList<>();
        String selectAll="SELECT * FROM `accounts`";
        ps=conn.prepareStatement(selectAll);
        ResultSet re=ps.executeQuery();
        while (re.next()){
            Account account=new Account(re.getInt(2),re.getInt(3),re.getString(4),re.getString(5),re.getInt(6),re.getString(7));
            account.setId(re.getInt(1));
            accounts.add(account);
        }
        if(ps!=null){
            ps.close();
        }
        conn.close();
        return accounts;


    }

    public int delete() throws SQLException {
        Connection conn=DB.getInstance().getConnection();
        PreparedStatement ps=null;
        int count=0;
        String delete="DELETE FROM `accounts` WHERE id=? ";
        ps=conn.prepareStatement(delete);
        ps.setInt(1,this.getId());
        count=ps.executeUpdate();
        if(ps!=null){
            ps.close();
        }
        conn.close();
        return count;


    }
    public int update() throws SQLException {
        Connection conn=DB.getInstance().getConnection();
        PreparedStatement ps=null;
        int count=0;
        String edit="UPDATE `accounts` SET user_id=?,account_number=?,username=?,currency=?,balance=?,creation_date=? WHERE id=?";
        ps=conn.prepareStatement(edit);
        ps.setInt(1,this.getUser_id());
        ps.setInt(2,this.getAccount_number());
        ps.setString(3,this.getUsername());
        ps.setString(4,this.getCurrency());
        ps.setDouble(5,this.getBalance());
        ps.setString(6,this.getCreation_date());
        ps.setInt(7,this.getId());
        count=ps.executeUpdate();
        if(ps!=null){
            ps.close();
        }
        conn.close();
        return count;
    }
    public  static  Account getOneAccount(int accountNumber) throws SQLException {
        Connection conn=DB.getInstance().getConnection();
        PreparedStatement ps=null;
        String selectOne="SELECT * FROM `accounts` WHERE account_number=?";
        ps=conn.prepareStatement(selectOne);
        ps.setInt(1,accountNumber);
        ResultSet re=ps.executeQuery();
        Account account=null;
        while (re.next()){
             account=new Account(re.getInt(2),re.getInt(3),re.getString(4),re.getString(5),re.getInt(6),re.getString(7));
            account.setId(re.getInt(1));
        }
        if(ps!=null)
            ps.close();
        conn.close();
        return account;
    }
}
