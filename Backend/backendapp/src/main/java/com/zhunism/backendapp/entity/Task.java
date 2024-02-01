package com.zhunism.backendapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name="tasks", schema = "todo_db")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int taskId;
    @Column(name="user_id")
    int userId;
    @Column(name="title")
    String title;
    @Column(name="priority")
    int priority;
    @Column(name="status")
    int status;
    @Column(name="due_date")
    LocalDate dueDate;
}
