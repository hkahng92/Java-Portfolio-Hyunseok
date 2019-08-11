package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskerServiceLayer {

    private TaskerDao taskerDao;

    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${adserverServiceName}")
    private String adserverServiceName;

    @Value("${serviceProtocol}")
    private String serviceProtocol;

    @Value("${servicePath}")
    private String servicePath;

    @Autowired
    public TaskerServiceLayer(TaskerDao taskerDao){
        this.taskerDao=taskerDao;
    }

    public TaskerServiceLayer(TaskerDao taskerDao, DiscoveryClient discoveryClient, RestTemplate restTemplate, String adserverServiceName, String serviceProtocol, String servicePath){
        this.taskerDao=taskerDao;
        this.discoveryClient=discoveryClient;
        this.restTemplate=restTemplate;
        this.adserverServiceName=adserverServiceName;
        this.serviceProtocol=serviceProtocol;
        this.servicePath=servicePath;
    }

    @RequestMapping(value="/ad",method= RequestMethod.GET)
    public String getAd(){
        List<ServiceInstance> instances = discoveryClient.getInstances(adserverServiceName);
        String adserverUri = serviceProtocol+instances.get(0).getHost()+":"+instances.get(0).getPort()+servicePath;
        String myAd = restTemplate.getForObject(adserverUri,String.class);

        return myAd;
    }

    public TaskViewModel fetchTask(int id) {
        Task task = taskerDao.getTask(id);
        if(task == null){
            return null;
        }

        TaskViewModel tvm = new TaskViewModel();

        tvm.setId(task.getId());
        tvm.setDescription(task.getDescription());
        tvm.setCreateDate(task.getCreateDate());
        tvm.setDueDate(task.getDueDate());
        tvm.setCategory(task.getCategory());

        // TODO - get ad from Adserver and put in tvm
        tvm.setAdvertisement(getAd());

        return tvm;
    }

    public List<TaskViewModel> fetchAllTasks() {
        List<Task> tasks = taskerDao.getAllTasks();
        List<TaskViewModel> tvms = new ArrayList<>();

        for(Task task : tasks){
            tvms.add(buildTaskViewModel(task));
        }

        return tvms;
    }

    public List<TaskViewModel> fetchTasksByCategory(String category) {
        List<Task> tasks = taskerDao.getTasksByCategory(category);
        List<TaskViewModel> tvms = new ArrayList<>();

        for(Task task : tasks){
            tvms.add(buildTaskViewModel(task));
        }

        return tvms;
    }

    public TaskViewModel newTask(TaskViewModel taskViewModel) {

        Task task = new Task();
        task.setDescription(taskViewModel.getDescription());
        task.setCreateDate(taskViewModel.getCreateDate());
        task.setDueDate(taskViewModel.getDueDate());
        task.setCategory(taskViewModel.getCategory());

        task = taskerDao.createTask(task);
        taskViewModel.setId(task.getId());

        // TODO - get ad from Adserver and put in taskViewModel
        taskViewModel.setAdvertisement(getAd());
        return taskViewModel;
    }

    public void deleteTask(int id) {
        taskerDao.deleteTask(id);
    }

    public TaskViewModel updateTask(TaskViewModel taskViewModel) {
        Task task = new Task();
        task.setId(taskViewModel.getId());
        task.setDescription(taskViewModel.getDescription());
        task.setCreateDate(taskViewModel.getCreateDate());
        task.setDueDate(taskViewModel.getDueDate());
        task.setCategory(taskViewModel.getCategory());

        taskerDao.updateTask(task);

        return buildTaskViewModel(task);
    }

    private TaskViewModel buildTaskViewModel(Task task){
        TaskViewModel taskViewModel = new TaskViewModel();
        taskViewModel.setId(task.getId());
        taskViewModel.setDescription(task.getDescription());
        taskViewModel.setCreateDate(task.getCreateDate());
        taskViewModel.setDueDate(task.getDueDate());
        taskViewModel.setCategory(task.getCategory());
        taskViewModel.setAdvertisement(getAd());

        return taskViewModel;
    }
}
