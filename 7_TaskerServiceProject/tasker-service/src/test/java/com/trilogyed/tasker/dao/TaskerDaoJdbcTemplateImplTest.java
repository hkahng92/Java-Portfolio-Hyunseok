package com.trilogyed.tasker.dao;

import com.trilogyed.tasker.model.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskerDaoJdbcTemplateImplTest {

    @Autowired
    protected TaskerDao taskerDao;

    @Before
    public void setUp() throws Exception {
        List<Task> taskList = taskerDao.getAllTasks();
        taskList.stream()
                .forEach(task -> taskerDao.deleteTask(task.getId()));
    }

    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void createGetDeleteTask() {
        Task task = new Task();
        task.setDescription("u2 m1 summative");
        task.setCreateDate(LocalDate.of(2019,7,24));
        task.setDueDate(LocalDate.of(2019,7,31));
        task.setCategory("summative");

        task = taskerDao.createTask(task);

        Task task1 = taskerDao.getTask(task.getId());
        assertEquals(task,task1);

        taskerDao.deleteTask(task.getId());
        task1 = taskerDao.getTask(task.getId());
        assertNull(task1);
    }

    @Test
    public void getAllTasks() {
        Task task = new Task();
        task.setDescription("u2 m1 summative");
        task.setCreateDate(LocalDate.of(2019,7,24));
        task.setDueDate(LocalDate.of(2019,7,31));
        task.setCategory("summative");

        taskerDao.createTask(task);

        task = new Task();
        task.setDescription("u2 group project");
        task.setCreateDate(LocalDate.of(2019,7,24));
        task.setDueDate(LocalDate.of(2019,7,31));
        task.setCategory("project");

        taskerDao.createTask(task);

        List<Task> tasks = taskerDao.getAllTasks();
        assertEquals(2,tasks.size());
    }

    @Test
    public void getTasksByCategory() {
        Task task = new Task();
        task.setDescription("u2 m1 summative");
        task.setCreateDate(LocalDate.of(2019,7,24));
        task.setDueDate(LocalDate.of(2019,7,31));
        task.setCategory("summative");

        taskerDao.createTask(task);

        task = new Task();
        task.setDescription("u2 m2 summative");
        task.setCreateDate(LocalDate.of(2019,7,24));
        task.setDueDate(LocalDate.of(2021,7,31));
        task.setCategory("summative");

        taskerDao.createTask(task);

        List<Task> tasks = taskerDao.getTasksByCategory("summative");
        assertEquals(2,tasks.size());
    }

    @Test
    public void updateTask() {
        Task task = new Task();
        task.setDescription("u2 m1 summative");
        task.setCreateDate(LocalDate.of(2019,7,24));
        task.setDueDate(LocalDate.of(2019,7,31));
        task.setCategory("summative");

        task = taskerDao.createTask(task);

        task.setDescription("u2 group project");
        task.setCreateDate(LocalDate.of(2019,5,11));
        task.setDueDate(LocalDate.of(2019,5,31));
        task.setCategory("project");

        taskerDao.updateTask(task);

        Task task1 = taskerDao.getTask(task.getId());
        assertEquals(task,task1);
    }
}