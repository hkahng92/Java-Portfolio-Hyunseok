package com.company.HyunseokKahngU1Capstone.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessingFeeDaoJdbcTemplateImplTest {

    @Autowired
    ProcessingFeeDao processingFeeDao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getProcessingFee() {
        BigDecimal processFee = new BigDecimal("1.98");
        assertEquals(processFee,processingFeeDao.getProcessingFee("T-Shirts").getFee());

    }
}