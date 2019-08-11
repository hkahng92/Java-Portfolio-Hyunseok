package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.dao.TaskerDaoJdbcTemplateImpl;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskerServiceLayerTest {

    private TaskerDao taskerDao;
    private TaskerServiceLayer taskerServiceLayer;
    private RestTemplate restTemplate;
    private DiscoveryClient discoveryClient;

    @Value("${adserverServiceName}")
    private String adserverServiceName;

    @Value("${serviceProtocol}")
    private String serviceProtocol;

    @Value("${servicePath}")
    private String servicePath;

    @Before
    public void setUp() throws Exception {
        setUpRestTemplateMock();
        setUpDiscoveryClientMock();
        setUpTaskerDaoMock();
        taskerServiceLayer = new TaskerServiceLayer(taskerDao,discoveryClient,restTemplate,adserverServiceName,serviceProtocol,servicePath);
    }

    private void setUpTaskerDaoMock(){
        taskerDao = mock(TaskerDaoJdbcTemplateImpl.class);

        Task task = new Task();
        task.setId(1);
        task.setDescription("U2 Summative 1");
        task.setCreateDate(LocalDate.of(2019,5,1));
        task.setDueDate(LocalDate.of(2019,5,31));
        task.setCategory("summative");

        Task task1 = new Task();
        task1.setDescription("U2 Summative 1");
        task1.setCreateDate(LocalDate.of(2019,5,1));
        task1.setDueDate(LocalDate.of(2019,5,31));
        task1.setCategory("summative");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        Task task2 = new Task();
        task2.setId(1);
        task2.setDescription("U2 Group Project");
        task2.setCreateDate(LocalDate.of(2019,3,1));
        task2.setDueDate(LocalDate.of(2019,3,15));
        task2.setCategory("project");

        doReturn(task).when(taskerDao).createTask(task1);
        doReturn(task).when(taskerDao).getTask(1);
        doReturn(taskList).when(taskerDao).getTasksByCategory("summative");
        doReturn(task2).when(taskerDao).updateTask(task);
        doReturn(taskList).when(taskerDao).getAllTasks();
    }

    private void setUpDiscoveryClientMock(){
        discoveryClient = mock(DiscoveryClient.class);
        List<ServiceInstance> instances = new LinkedList<>();
        instances.add(new DefaultServiceInstance("","","localhost",6107,true));
        doReturn(instances).when(discoveryClient).getInstances("adserver-service");
    }
    private void setUpRestTemplateMock(){
        restTemplate= mock(RestTemplate.class);
        doReturn("Home Equity Loans @ 3.87% APR").when(restTemplate).getForObject("http://localhost:6107/ad",String.class);
    }

    @Test
    public void newFetchTask() {
        TaskViewModel taskViewModel = new TaskViewModel();
        taskViewModel.setDescription("U2 Summative 1");
        taskViewModel.setCreateDate(LocalDate.of(2019,5,1));
        taskViewModel.setDueDate(LocalDate.of(2019,5,31));
        taskViewModel.setCategory("summative");

        taskViewModel = taskerServiceLayer.newTask(taskViewModel);

        taskViewModel.setId(1);
        taskViewModel.setAdvertisement("Home Equity Loans @ 3.87% APR");

        TaskViewModel fromService = taskerServiceLayer.fetchTask(taskViewModel.getId());
        assertEquals(taskViewModel,fromService);

    }

    @Test
    public void fetchAllTasks() {
        TaskViewModel taskViewModel = new TaskViewModel();
        taskViewModel.setDescription("U2 Summative 1");
        taskViewModel.setCreateDate(LocalDate.of(2019,5,1));
        taskViewModel.setDueDate(LocalDate.of(2019,5,31));
        taskViewModel.setCategory("summative");

        taskViewModel = taskerServiceLayer.newTask(taskViewModel);

        taskViewModel.setId(1);
        taskViewModel.setAdvertisement("Home Equity Loans @ 3.87% APR");

        List<TaskViewModel> tasks = taskerServiceLayer.fetchAllTasks();
        assertEquals(tasks.size(),1);
        assertEquals(taskViewModel,tasks.get(0));
    }

    @Test
    public void fetchTasksByCategory() {
        TaskViewModel taskViewModel = new TaskViewModel();
        taskViewModel.setDescription("U2 Summative 1");
        taskViewModel.setCreateDate(LocalDate.of(2019,5,1));
        taskViewModel.setDueDate(LocalDate.of(2019,5,31));
        taskViewModel.setCategory("summative");

        taskViewModel = taskerServiceLayer.newTask(taskViewModel);

        taskViewModel.setId(1);
        taskViewModel.setAdvertisement("Home Equity Loans @ 3.87% APR");

        List<TaskViewModel> tasks = taskerServiceLayer.fetchTasksByCategory("summative");
        assertEquals(tasks.size(),1);
        assertEquals(taskViewModel,tasks.get(0));
    }


    @Test
    public void updateTask() {
        TaskViewModel taskViewModel = new TaskViewModel();
        taskViewModel.setId(1);
        taskViewModel.setDescription("U2 Group Project");
        taskViewModel.setCreateDate(LocalDate.of(2019,3,1));
        taskViewModel.setDueDate(LocalDate.of(2019,3,15));
        taskViewModel.setCategory("project");

        TaskViewModel fromService = taskerServiceLayer.updateTask(taskViewModel);

        taskViewModel.setId(1);
        taskViewModel.setAdvertisement("Home Equity Loans @ 3.87% APR");

        assertEquals(taskViewModel,fromService);
    }
}