package tasks.services;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Iterables;
import org.mockito.junit.jupiter.MockitoExtension;
import tasks.model.Task;
import tasks.persistance.ArrayTaskList;
import tasks.services.DateService;
import tasks.services.TasksService;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TasksOperationsTest {

    private DateService dateService;


    @BeforeEach
    void setUp() {
        Task task=new Task("1",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime());
        task.setActive(true);
        ArrayTaskList arrayTaskList= new ArrayTaskList();
        arrayTaskList.add(task);
        dateService= new DateService(new TasksService(arrayTaskList));
    }


    @Test
    void F02_TC01(){

        Iterable<Task> test= dateService.getService().filterTasks(new GregorianCalendar(2000, Calendar.APRIL,25).getTime(),
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime());
        assertTrue(((Collection<Task>)test).size()==0);

    }

    @Test
    void F01_TC02(){

        Iterable<Task> test= dateService.getService().filterTasks(new GregorianCalendar(2000, Calendar.APRIL,24).getTime(),
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime());
        assertTrue(((Collection<Task>)test).size()==0);
    }

    @Test
    void F02_TC03(){

        Iterable<Task> test= dateService.getService().filterTasks(new GregorianCalendar(2000, Calendar.APRIL,22).getTime(),
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime());
        assertFalse(((Collection<Task>)test).size()==0);
    }

    @Test
    void F02_TC04(){
        Iterable<Task> test= dateService.getService().filterTasks(new GregorianCalendar(2000, Calendar.APRIL,22).getTime(),
                new GregorianCalendar(2000, Calendar.APRIL,24).getTime());
        assertFalse(((Collection<Task>)test).size()==0);
    }

    @Test
    void F02_TC05(){
        dateService.setService(new TasksService(new ArrayTaskList()));
        Iterable<Task> test= dateService.getService().filterTasks(new GregorianCalendar(2000, Calendar.APRIL,22).getTime(),
                new GregorianCalendar(2000, Calendar.APRIL,24).getTime());
        assertTrue(((Collection<Task>)test).size()==0);
    }


}