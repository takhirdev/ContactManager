package service;

import models.Contact;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContactRepository {
    public void add_contact(Contact contact) {
        try {
            DatabaseUtil.createTable();
            Connection connection = DatabaseUtil.getConnect();
            String sql = "insert into contact (name, surname, phone) values (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setString(3, contact.getPhone());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                System.out.println("add contact successfully!");
            } else System.out.println("something went wrong");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void delete_contact(String phone) {
        try {
            Connection connection = DatabaseUtil.getConnect();
            String sql = " delete from contact where phone = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                System.out.println("delete contact successfully!");
            } else {
                System.out.println("something went wrong!!!!!");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> contact_list() {
        List<Contact> contactList = new LinkedList<>();
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnect();
            String sql = " select * from contact;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setPhone(resultSet.getString("phone"));
                contactList.add(contact);
            }
            return contactList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Contact> search_contact(String query) {
        List<Contact> contactList = new LinkedList<>();
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnect();
            String sql = "select * from contact where lower(name) like ? or lower(surname) like ? or phone like ?;";
            query = "%" + query.toLowerCase() + "%";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, query);
            preparedStatement.setString(2, query);
            preparedStatement.setString(3, query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setPhone(resultSet.getString("phone"));
                contactList.add(contact);
            }
            return contactList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Contact phoneIsHave(String phone)  {
        Connection connection = null;
        Contact contact = null;
        try {
            connection = DatabaseUtil.getConnect();
            String sql = "select * from contact where phone = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                contact = new Contact();
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setPhone(resultSet.getString("phone"));
            }
            return contact;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }return null;
    }
}
