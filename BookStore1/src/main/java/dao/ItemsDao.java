package dao;

import model.Item;

import java.util.List;

public interface ItemsDao {
    void add(Item item);

    void update(Item item);

    void delete(Long id);

    Item findByPrimaryKey(Long id);

    List<Item> viewAllItems(int offset, int noOfRecords);

    List<Item> viewItemsByGenre(int offset, int noOfRecords, String genre);

    int size();
}
