package com.example.pointini.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventsDto   {

    private long id;
    private String titel;
    private Date start;
    private Date end;
    private String backgroundColor;
    private String textColor;

}
