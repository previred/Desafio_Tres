package com.example.previredTest.service;

import com.example.previredTest.dto.PeriodWithUfDTO;

import java.io.IOException;

public interface PreviredTestService {
    PeriodWithUfDTO getPeriodWithUfData() throws IOException;
}
