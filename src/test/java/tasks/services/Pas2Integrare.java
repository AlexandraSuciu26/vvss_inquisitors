package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.Task;
import tasks.persistance.ArrayTaskList;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.mock;

public class Pas2Integrare {
    ArrayTaskList arrayTaskList;
    Task t,t1;
    TasksService tasksService;
    @BeforeEach
    void setUp(){
        arrayTaskList=new ArrayTaskList();
        tasksService=new TasksService(arrayTaskList);
        t1=mock(Task.class);
        t=mock(Task.class);
        Mockito.when(t.getEndTime()).thenReturn(null);
        Mockito.when(t.getTitle()).thenReturn("Ceva");
        Mockito.when(t1.getTitle()).thenReturn("Altceva");
        Mockito.when(t1.getEndTime()).thenReturn(new GregorianCalendar(2000, Calendar.APRIL, 29).getTime());
    }
    @Test
    void T1(){
        tasksService.addTask(t);
        assert(tasksService.tasks.getTask(0).getTitle().equals("Ceva"));
    }
    @Test
    void T2(){
        tasksService.addTask(t1);
        tasksService.addTask(t);
        assert (tasksService.tasks.size()==2);
        assert (tasksService.tasks.getTask(0).getTitle().equals("Altceva"));
        assert(tasksService.tasks.getTask(1).getTitle().equals("Ceva"));
    }
}
