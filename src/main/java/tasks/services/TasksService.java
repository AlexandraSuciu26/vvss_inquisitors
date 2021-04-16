package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasks.persistance.ArrayTaskList;
import tasks.model.Task;

import java.util.Date;

public class TasksService {

    public ArrayTaskList tasks;

    public TasksService(ArrayTaskList tasks){
        this.tasks = tasks;
    }


    public ObservableList<Task> getObservableList(){
        return FXCollections.observableArrayList(tasks.getAll());
    }
    public String getIntervalInHours(Task task){
        int seconds = task.getRepeatInterval();
        int minutes = seconds / DateService.SECONDS_IN_MINUTE;
        int hours = minutes / DateService.MINUTES_IN_HOUR;
        minutes = minutes % DateService.MINUTES_IN_HOUR;
        return formTimeUnit(hours) + ":" + formTimeUnit(minutes);
    }
    public String formTimeUnit(int timeUnit){
        StringBuilder sb = new StringBuilder();
        if (timeUnit < 10) sb.append("0");
        if (timeUnit == 0) sb.append("0");
        else {
            sb.append(timeUnit);
        }
        return sb.toString();
    }


    public int parseFromStringToSeconds(String stringTime){//hh:MM
        String[] units = stringTime.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        int result = (hours * DateService.MINUTES_IN_HOUR + minutes) * DateService.SECONDS_IN_MINUTE;
        return result;
    }
    public void addTask(Task t){
        tasks.add(t);
    }
    public Task addTask(String newTitle, Date newStartDate, Boolean isActive, Date newEndDate, Integer newInterval) {
        Task result;
        if (newEndDate == null && newInterval == null) {
            result = new Task(newTitle, newStartDate);
        } else {
            result = new Task(newTitle, newStartDate, newEndDate, newInterval);
        }

        result.setActive(isActive);
        tasks.add(result);
        return result;
    }

    public Iterable<Task> filterTasks(Date start, Date end){
        TasksOperations tasksOps = new TasksOperations(getObservableList());
        Iterable<Task> filtered = tasksOps.incoming(start,end);


        return filtered;
    }
}
