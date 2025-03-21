package com.lab.Assignment03.Controller;

import com.lab.Assignment03.DTO.ClinicsDTO;
import com.lab.Assignment03.DTO.SpecializationDTO;
import com.lab.Assignment03.Exception.RestExceptionAndSuccessHandle;
import com.lab.Assignment03.Service.ClinicsService;
import com.lab.Assignment03.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClinicsController {
    @Autowired
    private ClinicsService clinicsService;
    @Autowired
    private RestExceptionAndSuccessHandle restExceptionAndSuccessHandle;
    // API để lấy các chuyên khoa nổi bật theo số lượt đặt lịch khám

}
