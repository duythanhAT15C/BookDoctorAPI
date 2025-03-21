package com.lab.Assignment03.Controller;

import com.lab.Assignment03.DTO.ClinicsDTO;
import com.lab.Assignment03.DTO.DoctorUsersDTO;
import com.lab.Assignment03.DTO.SpecializationDTO;
import com.lab.Assignment03.Entity.Clinics;
import com.lab.Assignment03.Entity.Users;
import com.lab.Assignment03.Exception.RestExceptionAndSuccessHandle;
import com.lab.Assignment03.Service.ClinicsService;
import com.lab.Assignment03.Service.DoctorUsersService;
import com.lab.Assignment03.Service.SpecializationService;
import com.lab.Assignment03.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private DoctorUsersService doctorUsersService;
    @Autowired
    private RestExceptionAndSuccessHandle restExceptionAndSuccessHandle;
    @Autowired
    private ClinicsService clinicsService;
    @Autowired
    private SpecializationService specializationService;

    @PostMapping("/lockandunlock")
    public ResponseEntity<?> lockAccountPatient(@RequestHeader("Authorization") String token, @RequestParam int id, @RequestParam String description) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }
        if(userService.lockAndUnlockAccountPatients(id) == 0){
            return restExceptionAndSuccessHandle.handleSuccess("Lý do khoá tài khoản: " + description, HttpStatus.OK);
        }
        else if(userService.lockAndUnlockAccountPatients(id) == -1){
            return restExceptionAndSuccessHandle.handleException("Không được phép khoá/huỷ khoá tài khoản admin", HttpStatus.BAD_REQUEST);
        }
        else if(userService.lockAndUnlockAccountPatients(id) == -2){
            return restExceptionAndSuccessHandle.handleException("Không có tài khoản với id: " + id, HttpStatus.BAD_REQUEST);
        }
        else {
            return restExceptionAndSuccessHandle.handleSuccess("Huỷ khoá tài khoản thành công", HttpStatus.OK);
        }
    }
    @PostMapping("/add-doctor")
    public ResponseEntity<?> addDoctor(@RequestHeader("Authorization") String token, @RequestBody DoctorUsersDTO doctorUsersDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }
        if(userService.isEmailExists(doctorUsersDTO.getUserDTO().getEmail())){
            return restExceptionAndSuccessHandle.handleException("Email đã tồn tại", HttpStatus.BAD_REQUEST);
        }
        if(doctorUsersDTO.getUserDTO().getRoleId() == 1 || doctorUsersDTO.getUserDTO().getRoleId() == 3){
            return restExceptionAndSuccessHandle.handleException("Admin chỉ được tạo tài khoản bác sĩ", HttpStatus.BAD_REQUEST);
        }
        if(!doctorUsersDTO.getUserDTO().getPassword().equals(doctorUsersDTO.getUserDTO().getRePassword())){
            return restExceptionAndSuccessHandle.handleException("Mật khẩu không khớp", HttpStatus.BAD_REQUEST);
        }
        ClinicsDTO clinicsDTO = clinicsService.getClinicsDTOById(doctorUsersDTO.getClinicsDTO().getId());
        if(clinicsDTO == null){
            return restExceptionAndSuccessHandle.handleException("Phòng khám không tồn tại", HttpStatus.BAD_REQUEST);
        }
        SpecializationDTO specializationDTO = specializationService.getSpecializationById(doctorUsersDTO.getSpecializationDTO().getId());
        if(specializationDTO == null){
            return restExceptionAndSuccessHandle.handleException("Chuyên khoa không tồn tại", HttpStatus.BAD_REQUEST);
        }
        doctorUsersService.save(doctorUsersDTO);
        return restExceptionAndSuccessHandle.handleException("Tạo tài khoản bác sĩ thành công", HttpStatus.OK);
    }
    @PostMapping("/add-clinics")
    public ResponseEntity<?> addClinics(@RequestHeader("Authorization") String token, @RequestBody ClinicsDTO clinicsDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }
        if(clinicsService.save(clinicsDTO)){
            return restExceptionAndSuccessHandle.handleSuccess("Thêm phòng khám thành công", HttpStatus.OK);
        }
        return restExceptionAndSuccessHandle.handleException("Thêm phòng khám thất bại", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/add-specializations")
    public ResponseEntity<?> addSpecializations(@RequestHeader("Authorization") String token, @RequestBody SpecializationDTO specializationDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }
        ClinicsDTO clinicsDTO = clinicsService.getClinicsDTOById(specializationDTO.getClinicsDTO().getId());
        if(clinicsDTO == null){
            return restExceptionAndSuccessHandle.handleException("Không tồn tại phòng khám này", HttpStatus.BAD_REQUEST);
        }
        specializationDTO.setClinicsDTO(clinicsDTO);
        if(specializationService.save(specializationDTO)){
            return restExceptionAndSuccessHandle.handleSuccess("Thêm chuyên khoa thành công", HttpStatus.OK);
        }
        return restExceptionAndSuccessHandle.handleException("Thêm chuyên khoa thất bại", HttpStatus.BAD_REQUEST);
    }
}
