package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.persistance.ArrayTaskList;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Pas3Integrare {
    ArrayTaskList arrayTaskList;
    Task t,t1;
    TasksService tasksService;
    @BeforeEach
    void setUp(){
        arrayTaskList=new ArrayTaskList();
        tasksService=new TasksService(arrayTaskList);
        t=new Task("Ceva",new GregorianCalendar(2000, Calendar.APRIL,23).getTime());
        t1=new Task("Altceva",new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),new GregorianCalendar(2000, Calendar.APRIL,25).getTime(),3);
    }
    @Test
    void T1(){
        tasksService.addTask(t.getTitle(),t.getTime(),true,null,null);
        tasksService.addTask(t1.getTitle(),t1.getStartTime(),true,t1.getEndTime(),3);
        assert(tasksService.tasks.size()==2);
        assert (tasksService.tasks.getTask(0).getTitle().equals(t.getTitle()));
        assert (tasksService.tasks.getTask(1).getTitle().equals(t1.getTitle()));
    }

    @Test
    void T2(){
        tasksService.addTask(t1.getTitle(),t1.getStartTime(),true,t1.getEndTime(),3);
        assert(tasksService.tasks.size()==1);
        assert (tasksService.tasks.getTask(0).getTitle().equals(t1.getTitle()));
        assert (tasksService.tasks.getTask(0).getEndTime().equals(t1.getEndTime()));
        assert (tasksService.tasks.getTask(0).getStartTime().equals(t1.getStartTime()));
        assert (tasksService.tasks.getTask(0).getRepeatInterval()==t1.getRepeatInterval());
    }
}
