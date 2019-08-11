package com.company.HyunseokKahngU1Capstone.dao;

import com.company.HyunseokKahngU1Capstone.model.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class SalesTaxRateDaoJdbcTemplateImpl implements SalesTaxRateDao{

    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_SALESTAXRATE_SQL =
            "select * from sales_tax_rate where state = ?";

    @Autowired
    public SalesTaxRateDaoJdbcTemplateImpl (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SalesTaxRate getSalesTaxRate(String state) {
        try{
            return jdbcTemplate.queryForObject(SELECT_SALESTAXRATE_SQL,this::mapRowToTax,state);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    private SalesTaxRate mapRowToTax(ResultSet rs, int rowNum) throws SQLException{
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getBigDecimal("rate"));
        return salesTaxRate;
    }
}
