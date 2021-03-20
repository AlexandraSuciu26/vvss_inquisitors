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
    void ecpTitleValid() {
        Task task = dateService.addTask("1",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertTrue(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(2)
    @DisplayName("ecpTitleInvalid")
    @Test
    void ecpTitleInvalidUnderMinimumSize() {
        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertFalse(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(3)
    @DisplayName("ecpTitleInvalid")
    @Test
    void ecpTitleInvalidOverMaximumSize() {
        String testTitle="";
        for(int i=0;i<300;i++)
            testTitle+="a";
        System.out.println(testTitle);
        Task task = dateService.addTask(testTitle,
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertFalse(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(4)
    @Test
    void ecpTimeValid() {
        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertTrue(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(5)
    @Test
    void ecpTimeInvalid() {
        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.MAY,23).getTime(),
                false,null,null);
        assertFalse(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(6)
    @Test
    void bvaTitleValidMinimumSize() {
        Task task = dateService.addTask("1",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertTrue(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(7)
    @Test
    void bvaTitleInvalidUnderMinimumSize() {
        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertFalse(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(8)
    @Test
    void bvaTitleValidOverMinimumSize() {
        Task task = dateService.addTask("23",
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertTrue(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(9)
    @Test
    void bvaTitleValidMaximumSize() {
        String testTitle="";
        for(int i=0;i<255;i++)
            testTitle+="a";
        Task task = dateService.addTask(testTitle,
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertTrue(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(10)
    @Test
    void bvaTitleValidUnderMaximumSize() {
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
    @Order(11)
    void bvaTitleInvalidOverMaximumSize(int test) {
        String testTitle="";
        for(int i=0;i<test;i++)
            testTitle+="a";
        Task task = dateService.addTask(testTitle,
                new GregorianCalendar(2000, Calendar.APRIL,23).getTime(),
                false,null,null);
        assertFalse(task.getTitle().length()>=1 && task.getTitle().length()<=255);
    }

    @Order(12)
    @Test
    void bvaTimeInvalidMinimum() {

        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,1).getTime(),
                false,null,null);
        assertFalse(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(13)
    @Test
    void bvaTimeInvalidUnderMinimum() {
        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.MARCH,31).getTime(),
                false,null,null);
        assertFalse(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(14)
    @Test
    void bvaTimeValidOverMinimum() {

        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,2).getTime(),
                false,null,null);
        assertTrue(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(15)
    @Test
    void bvaTimeInvalidMaximum() {

        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,30).getTime(),
                false,null,null);
        assertFalse(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(16)
    @Test
    void bvaTimeValidUnderMaximum() {
        Task task = dateService.addTask("",
                new GregorianCalendar(2000, Calendar.APRIL,29).getTime(),
                false,null,null);
        assertTrue(new GregorianCalendar(2000, Calendar.APRIL,1).getTime().before(task.getTime())
                && task.getTime().before(new GregorianCalendar(2000, Calendar.APRIL,30).getTime()));
    }

    @Order(17)
    @Test
    void bvaTimeInvalidOverMaximum() {

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