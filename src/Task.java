import java.time.LocalDate;

public class Task {

    private String taskDescription;
    private LocalDate date;

    public String getTask() {
        return taskDescription;
    }

    public void setTask(String task, LocalDate date) {
        this.taskDescription = task;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Task(String task, LocalDate date) {
        this.taskDescription = task;
        this.date = date;
    }

    public Task() {
    }

    public String toString() {

        return date + "  Task: " + taskDescription;
    }

}
