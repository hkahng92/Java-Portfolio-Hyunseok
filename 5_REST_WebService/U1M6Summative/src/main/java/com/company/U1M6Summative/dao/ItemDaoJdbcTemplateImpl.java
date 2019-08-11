package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItemDaoJdbcTemplateImpl implements ItemDao {

    //JDBC
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Prepared Statements
    private static final String INSERT_ITEM_SQL = "insert into item(name, description, daily_rate) values(?, ?, ?)";

    private static final String SELECT_ITEM_SQL = "select* from item where item_id = ?";

    private static final String SELECT_ALL_ITEMS_SQL = "select * from item";

    private static final String UPDATE_ITEM_SQL = "update item set name = ?, description = ?, daily_rate = ? where item_id = ?";

    private static final String DELETE_ITEM_SQL = "delete from item where item_id = ?";

    //Map
    private Item mapRowToItem(ResultSet result, int rowNum) throws SQLException {
        Item item = new Item();
        item.setItemId(result.getInt("item_id"));
        item.setName(result.getString("name"));
        item.setDescription(result.getString("description"));
        item.setDailyRate(result.getBigDecimal("daily_rate"));

        return item;
    }

    //Implementations
    @Override
    @Transactional
    public Item addItem(Item item) {

        jdbcTemplate.update(
                INSERT_ITEM_SQL,
                item.getName(),
                item.getDescription(),
                item.getDailyRate()
        );

        //Get the ID that was just created for the new item
        Integer id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        //Set the ID in the Item object that we created
        item.setItemId(id);

        //Return the Item
        return item;
    }

    @Override
    public Item getItem(Integer itemId) {

        try {

            return jdbcTemplate.queryForObject(SELECT_ITEM_SQL, this::mapRowToItem, itemId);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SELECT_ALL_ITEMS_SQL, this::mapRowToItem);
    }

    @Override
    public Item updateItem(Item item) {

        jdbcTemplate.update(
                UPDATE_ITEM_SQL,
                //Update the states/properties of the item
                item.getName(),
                item.getDescription(),
                item.getDailyRate(),

                //Update the item based on its ID
                item.getItemId()
        );
        return item;
    }

    @Override
    public boolean deleteItem(Integer itemId) {

        jdbcTemplate.update(DELETE_ITEM_SQL, itemId);
        return true;
    }
}
