package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.Task;
import tasks.persistance.ArrayTaskList;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.mock;

class TasksServiceTest {
    ArrayTaskList taskList;
    TasksService tasksService;
    Task t,t1;
    @BeforeEach
    void setUp() {
        t=mock(Task.class);
        t1=mock(Task.class);
        taskList=mock(ArrayTaskList.class);
        tasksService=new TasksService(taskList);
    }

    @Test
    void addTask() {
        Mockito.when(t.getEndTime()).thenReturn(null);
        Mockito.when(t.getTitle()).thenReturn("Ceva");
        Mockito.when(taskList.getTask(0)).thenReturn(t);
        Mockito.when((taskList.size())).thenReturn(1);
        tasksService.addTask("Ceva",new GregorianCalendar(2000, Calendar.APRIL, 23).getTime(),true,null,null);
        assert(taskList.size()==1);
        assert(taskList.getTask(0).getTitle().equals("Ceva"));
        assert(taskList.getTask(0).getEndTime() == null);
    }
    @Test
    void test1(){
        Mockito.when(t.getEndTime()).thenReturn(null);
        Mockito.when(t.getTitle()).thenReturn("Ceva");
        Mockito.when(t1.getTitle()).thenReturn("Altceva");
        Mockito.when(t1.getEndTime()).thenReturn(new GregorianCalendar(2000, Calendar.APRIL, 29).getTime());
        Mockito.when(taskList.getTask(1)).thenReturn(t1);
        Mockito.when(taskList.getTask(0)).thenReturn(t);
        Mockito.when((taskList.size())).thenReturn(2);
        tasksService.addTask("Ceva",new GregorianCalendar(2000, Calendar.APRIL, 23).getTime(),true,null,null);
        tasksService.addTask("Altceva",new GregorianCalendar(2000, Calendar.APRIL, 24).getTime(),true,new GregorianCalendar(2000, Calendar.APRIL, 29).getTime(),3);
        assert(taskList.getTask(0).getEndTime() == null);
        assert(taskList.getTask(1).getEndTime().equals(new GregorianCalendar(2000, Calendar.APRIL, 29).getTime()));
        assert (taskList.size()==2);
        assert (taskList.getTask(1).getTitle().equals("Altceva"));
        assert (taskList.getTask(0).getTitle().equals("Ceva"));
    }
}