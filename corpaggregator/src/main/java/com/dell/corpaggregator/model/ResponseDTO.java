package com.dell.corpaggregator.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ResponseDTO {
    private String message;
    private LocalDateTime timestamp;

}
