package com.example.previredTest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeriodWithUfDTO {
    private String inicio;
    private String fin;
    private List<UfDTO> UFs;
}
