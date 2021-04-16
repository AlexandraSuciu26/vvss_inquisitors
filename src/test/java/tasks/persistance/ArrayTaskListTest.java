package tasks.persistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import tasks.model.Task;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ArrayTaskListTest {
    ArrayTaskList arrayTaskList;
    private Task t,t1;
    @BeforeEach
    void setUp() {
        arrayTaskList=new ArrayTaskList();
        t=mock(Task.class);
        t1=mock(Task.class);
        Mockito.when(t.getTitle()).thenReturn("Ceva");
        Mockito.when(t1.getTitle()).thenReturn("Altceva");
    }

    @Test
    void add() {
        arrayTaskList.add(t);
        arrayTaskList.add(t1);
        assert(arrayTaskList.size()==2);
        assert(arrayTaskList.getTask(0).getTitle().equals("Ceva"));
        assert(arrayTaskList.getTask(1).getTitle().equals("Altceva"));
    }
    @Test
    void add1(){
        assert(arrayTaskList.size()==0);
        arrayTaskList.add(t1);
        assert(arrayTaskList.getTask(0).getTitle().equals("Altceva"));
    }
}