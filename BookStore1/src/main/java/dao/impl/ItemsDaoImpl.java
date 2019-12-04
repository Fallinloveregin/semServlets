package dao.impl;

import dao.ItemsDao;
import dao.factory.JDBCTemplate;
import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ItemsDaoImpl extends JDBCTemplate implements ItemsDao {

    public void add(Item item) {

        try {
            String querystring = "INSERT INTO items(name, description, price, picture, genre) VALUES (?, ?,?, ?,?)";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setString(1, item.getName());
            ptmt.setString(2, item.getDescription());
            ptmt.setFloat(3, item.getPrice());
            ptmt.setString(4, item.getPicture());
            ptmt.setString(5, item.getGenre());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void update(Item item) {
        try {
            String querystring = "UPDATE items SET name = ?,description = ?, price = ?, picture = ? , genre = ? WHERE item_id = ?";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);

            ptmt.setString(1, item.getName());
            ptmt.setString(2, item.getDescription());
            ptmt.setFloat(3, item.getPrice());
            ptmt.setString(4, item.getPicture());
            ptmt.setString(5, item.getGenre());
            ptmt.setLong(6, item.getId());
            ptmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void delete(Long id) {
        try {
            String querystring = "DELETE FROM items WHERE item_id =?";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setLong(1, id);
            ptmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public Item findByPrimaryKey(Long id) {
        Item item = null;
        try {
            String querystring = "SELECT * FROM items WHERE item_id = ?";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setLong(1, id);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                item = new Item();
                addFieldsToItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return item;
    }

    @Override
    public List<Item> viewAllItems(int offset, int noOfRecords) {
        String query = "select * from items order by price limit " + noOfRecords + " offset " + offset;
        List<Item> list = new ArrayList<>();
        Item item;
        try {
            con = getConnection();
            ptmt = con.prepareStatement(query);
            rs = ptmt.executeQuery();

            while (rs.next()) {
                item = new Item();
                addFieldsToItem(item);
                list.add(item);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    @Override
    public List<Item> viewItemsByGenre(int offset, int noOfRecords, String genre) {
        String query = "select * from items WHERE genre like ? order by price limit " + noOfRecords + " offset " + offset + "";
        List<Item> list = new ArrayList<>();
        Item item;
        try {
            con = getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setString(1, genre);
            rs = ptmt.executeQuery();

            while (rs.next()) {
                item = new Item();
                addFieldsToItem(item);
                list.add(item);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    @Override
    public int size() {
        int size = 0;
        String query = "SELECT COUNT(*) FROM items";
        try {
            con = getConnection();
            ptmt = con.prepareStatement(query);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                size = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    private void addFieldsToItem(Item item) throws SQLException {
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(rs.getFloat("price"));
        item.setPicture(rs.getString("picture"));
        item.setGenre(rs.getString("genre"));
        item.setId(rs.getLong("item_id"));
    }
}
