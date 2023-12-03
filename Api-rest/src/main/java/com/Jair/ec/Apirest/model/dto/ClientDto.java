package com.Jair.ec.Apirest.model.dto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder
public class ClientDto {
    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private Date registerDate;
}
