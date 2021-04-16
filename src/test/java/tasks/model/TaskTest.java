package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    public Task t;
    @BeforeEach
    void setUp() {
        t=new Task("Ceva",new GregorianCalendar(2000, Calendar.APRIL,23).getTime());
    }

    @Test
    void getTitle() {
        assert (t.getTitle().equals("Ceva"));
    }

    @Test
    void setTitle() {
        t.setTitle("Altceva");
        assert (t.getTitle().equals("Altceva"));
    }

    @Test
    void getTime() {
        assert (t.getTime().equals(new GregorianCalendar(2000, Calendar.APRIL, 23).getTime()));
    }

    @Test
    void setTime() {
        t.setTime(new GregorianCalendar(2100, Calendar.APRIL,23).getTime());
        assert(t.getTime().equals(new GregorianCalendar(2100, Calendar.APRIL,23).getTime()));
    }
}