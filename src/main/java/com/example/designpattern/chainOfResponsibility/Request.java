package com.example.designpattern.chainOfResponsibility;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {
    private Priority priority;
    private String responsibility;


    public enum Priority {
        High,
        Medium,
        Low
    }
}
