package tasks.controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tasks.model.Task;
import tasks.persistance.ArrayTaskList;
import tasks.services.DateService;
import tasks.services.TasksService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NewEditControllerTest {

    private DateService dateService;


//@Tag("sladh")


    @BeforeEach
    void setUp() {
       dateService= new DateService(new TasksService(new ArrayTaskList()));
    }

    @Order(1)
    @Test
    void bvaTitleValid() {
        Task task = dateService.addTask("1",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertTrue(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(2)
    @DisplayName("bvaTitleInvalid")
    @Test
    void bvaTitleInvalid() {
        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertFalse(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(3)
    @Test
    void bvaTimeValid() {
        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertTrue(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(4)
    @Test
    void bvaTimeInvalid() {
        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.MAY,23).getTime(),
                false,null,null);
        assertFalse(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(5)
    @Test
    void ecpTitleValidMinimumSize() {
        Task task = dateService.addTask("1",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertTrue(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(6)
    @Test
    void ecpTitleInvalidUnderMinimumSize() {
        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertFalse(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(7)
    @Test
    void ecpTitleValidOverMinimumSize() {
        Task task = dateService.addTask("23",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertTrue(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(8)
    @Test
    void ecpTitleValidMaximumSize() {
        String testTitle="";
        for(int i=0;i<255;i++)
            testTitle+="a";
        Task task = dateService.addTask(testTitle,
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertTrue(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(9)
    @Test
    void ecpTitleValidUnderMaximumSize() {
        String testTitle="";
        for(int i=0;i<254;i++)
            testTitle+="a";
        Task task = dateService.addTask(testTitle,
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertTrue(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @ParameterizedTest(name = "Maximum length")
    @ValueSource(ints = { 256 })
    @Order(10)
    void ecpTitleInvalidOverMaximumSize(int test) {
        String testTitle="";
        for(int i=0;i<test;i++)
            testTitle+="a";
        Task task = dateService.addTask(testTitle,
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertFalse(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(11)
    @Test
    void ecpTimeInvalidMinimum() {

        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,1).getTime(),
                false,null,null);
        assertFalse(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(12)
    @Test
    void ecpTimeInvalidUnderMinimum() {
        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.MARCH,31).getTime(),
                false,null,null);
        assertFalse(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(13)
    @Test
    void ecpTimeValidOverMinimum() {

        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,2).getTime(),
                false,null,null);
        assertTrue(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(14)
    @Test
    void ecpTimeInvalidMaximum() {

        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,30).getTime(),
                false,null,null);
        assertFalse(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(15)
    @Test
    void ecpTimeValidUnderMaximum() {
        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,29).getTime(),
                false,null,null);
        assertTrue(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(16)
    @Test
    void ecpTimeInvalidOverMaximum() {

        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.MAY,1).getTime(),
                false,null,null);
        assertFalse(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Disabled
    void testDisable(){
        assert(1==1);
    }
}