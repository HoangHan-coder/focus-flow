package dev.focusflow.entities;

import dev.focusflow.enums.TaskStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Nationalized
    @Column(nullable = false)
    private String  title;

    @Nationalized
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;


    @Column(nullable = false)
    private LocalDateTime deadline;

    private LocalDateTime completedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "task")
    private List<TaskItem> taskItems;

    public Task() {
    }

    public Task(String title, String description, TaskStatus taskStatus, LocalDateTime deadline, LocalDateTime completedAt, LocalDateTime createdAt, User user, List<TaskItem> taskItems) {
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.deadline = deadline;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
        this.user = user;
        this.taskItems = taskItems;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TaskItem> getTaskItems() {
        return taskItems;
    }

    public void setTaskItems(List<TaskItem> taskItems) {
        this.taskItems = taskItems;
    }

    public void addTaskItem(TaskItem taskItem) {
        if (this.taskItems == null) {
            this.taskItems = new ArrayList<>();
        }
        this.taskItems.add(taskItem);
    }

    public void removeTaskItem(TaskItem taskItem) {
        this.taskItems.remove(taskItem);
    }
}
