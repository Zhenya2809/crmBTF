package com.example.crmbtf.model;

import lombok.Data;

import java.util.HashSet;

@Data
public class DaySchedule {
    String date;
    HashSet<String> available;

}
