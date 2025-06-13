package com.kamalesh.server.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClickEventDto {
    private long count;
    private LocalDate clickDate;
}
