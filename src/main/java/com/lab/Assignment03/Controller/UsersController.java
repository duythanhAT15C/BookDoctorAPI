package com.lab.Assignment03.Controller;

import com.lab.Assignment03.DTO.*;
import com.lab.Assignment03.Exception.RestExceptionAndSuccessHandle;
import com.lab.Assignment03.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsersController {
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private RestExceptionAndSuccessHandle restExceptionAndSuccessHandle;
    @Autowired
    private ClinicsService clinicsService;
    @Autowired
    private UserService userService;
    @Autowired
    private DoctorUsersService doctorUsersService;
    @Autowired
    private ExtrainfosService extrainfosService;
    @Autowired
    private SpecializationService specializationService;

    @GetMapping("/info")
    public ResponseEntity<?> getInfoPatient(@RequestHeader("Authorization") String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findByEmail(username);
        Map<String, Object> response = new HashMap<>();
        if(userDTO.getRoleId() == 3){
            response.put("user", userDTO);
            PatientsDTO patientsDTO = patientsService.getPatientsDTOByPatientsId(userDTO.getId());
            if(patientsDTO != null){
                response.remove("user");
                response.put("patient", patientsDTO);
                ExtrainfosDTO extrainfosDTO = extrainfosService.getExtrainfosDTOByPatientId(patientsDTO.getId());
                if(extrainfosDTO != null){
                    response.put("extraInfo", extrainfosDTO);
                }
            }
        }
        else if(userDTO.getRoleId() == 2){
            DoctorUsersDTO doctorUsersDTO = doctorUsersService.getDoctorByDoctorUsersId(userDTO.getId());
            if (doctorUsersDTO != null){
                ClinicsDTO clinicsDTO = clinicsService.getClinicsDTOById(doctorUsersDTO.getClinicsDTO().getId());
                SpecializationDTO specializationDTO = specializationService.getSpecializationById(doctorUsersDTO.getSpecializationDTO().getId());
                if(specializationDTO.getClinicsDTO() != null){
                    doctorUsersDTO.getSpecializationDTO().setClinicsDTO(null);
                }
                response.put("doctorUser", doctorUsersDTO);
            }
        }
        else if(userDTO.getRoleId() == 1){
            response.put("user", userDTO);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update/patient-admin")
    public ResponseEntity<?> updatePatientAndAdmin(@RequestBody UserDTO userDTO, @RequestHeader("Authorization") String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO user = userService.findByEmail(username);
        boolean userDTOs = userService.update(user.getId(), userDTO);
        if (!userDTOs) {
            return restExceptionAndSuccessHandle.handleException("Không cập nhật được người dùng này", HttpStatus.NOT_FOUND);
        }
        return restExceptionAndSuccessHandle.handleSuccess("Cập nhật thành công", HttpStatus.OK);
    }

    @PostMapping("/update/doctor")
    public ResponseEntity<?> updateDoctor(@RequestBody DoctorUsersDTO doctorUsersDTO, @RequestHeader("Authorization") String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findByEmail(username);
        doctorUsersService.update(userDTO, doctorUsersDTO);
        return restExceptionAndSuccessHandle.handleSuccess("Cập nhật thành công", HttpStatus.OK);
    }
}
