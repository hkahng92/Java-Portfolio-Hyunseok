package com.company.HyunseokKahngU1Capstone.dao;

import com.company.HyunseokKahngU1Capstone.model.ProcessingFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class ProcessingFeeDaoJdbcTemplateImpl implements ProcessingFeeDao{

    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_PROCESSINGFEE_SQL =
            "select * from processing_fee where product_type = ?";

    @Autowired
    public ProcessingFeeDaoJdbcTemplateImpl (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProcessingFee getProcessingFee(String productType) {
        try{
            return jdbcTemplate.queryForObject(SELECT_PROCESSINGFEE_SQL,this::mapRowToProcessingFee,productType);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    private ProcessingFee mapRowToProcessingFee(ResultSet rs, int rowNum) throws SQLException{
        ProcessingFee pf = new ProcessingFee();
        pf.setProductType(rs.getString("product_type"));
        pf.setFee(rs.getBigDecimal("fee"));
        return pf;
    }
}
