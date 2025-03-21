package com.lab.Assignment03.Controller;

import com.lab.Assignment03.CheckFee.CheckFeeBooking;
import com.lab.Assignment03.DTO.*;
import com.lab.Assignment03.Exception.RestExceptionAndSuccessHandle;
import com.lab.Assignment03.CheckDateAndTime.CheckDateAndTime;
import com.lab.Assignment03.CheckDateAndTime.FormatTime;
import com.lab.Assignment03.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patient")
@PreAuthorize("hasRole('PATIENT')")
public class PatientsController {
    @Autowired
    private RestExceptionAndSuccessHandle restExceptionAndSuccessHandle;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private CheckDateAndTime checkDateAndTime;
    @Autowired
    private FormatTime formatTime;
    @Autowired
    private CheckFeeBooking checkFeeBooking;
    @Autowired
    private DoctorUsersService doctorUsersService;
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private PostsService postsService;
    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private ClinicsService clinicsService;
    @Autowired
    private ScheduleService scheduleService;
    @PostMapping("/booking")
    public ResponseEntity<?> bookingDoctor(@RequestHeader("Authorization") String token, @RequestBody PostDTO postDTO){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findByEmail(username);
        DoctorUsersDTO doctorUsersDTO = doctorUsersService.getDoctorUserById(postDTO.getDoctorUsersDTO().getId());
        if(doctorUsersDTO == null){
            return restExceptionAndSuccessHandle.handleException("Không có bác sĩ này", HttpStatus.BAD_REQUEST);
        }
        PostDTO postDTO1 = postsService.getPostDTOByDoctorUsersIdAndWriterId(doctorUsersDTO.getId(), userDTO.getId());
        if(postDTO1 != null){
            return restExceptionAndSuccessHandle.handleException("Đã book bác sĩ này rồi", HttpStatus.BAD_REQUEST);
        }
        postDTO.setUserDTO(userDTO);
        ClinicsDTO clinicsDTO  = clinicsService.getClinicsDTOById(doctorUsersDTO.getClinicsDTO().getId());
        postDTO.setClinicsDTO(clinicsDTO);
        SpecializationDTO specializationDTO = specializationService.getSpecializationById(doctorUsersDTO.getSpecializationDTO().getId());
        postDTO.setSpecializationDTO(specializationDTO);
        postDTO.setDoctorUsersDTO(doctorUsersDTO);
        if(!checkDateAndTime.startCheckDate(postDTO)){
            return restExceptionAndSuccessHandle.handleException("Ngày đặt phải trước ngày bác sĩ khám", HttpStatus.BAD_REQUEST);
        }
        if (formatTime.isValidTime(postDTO.getTimeBooking())){
            if(!checkDateAndTime.startCheckTime(postDTO)){
                return restExceptionAndSuccessHandle.handleException("Giờ đă phải trước giờ bác sĩ khám", HttpStatus.BAD_REQUEST);
            }
        }
        if(!checkFeeBooking.checkFee(postDTO)){
            return restExceptionAndSuccessHandle.handleException("Giá phải lớn hơn hoặc bằng giá khám của bác sĩ", HttpStatus.BAD_REQUEST);
        }
        if(postsService.save(postDTO)){
            return restExceptionAndSuccessHandle.handleSuccess("Đặt lịch khám thành công", HttpStatus.OK);
        }
        return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/findarea") // tìm địa chỉ cơ sở khám bệnh
    public ResponseEntity<?> findArea(@RequestHeader("Authorization") String token, @RequestParam String area) {
        List<ClinicsDTO> list = clinicsService.getClinicsDTOByAddress(area);
        if (list.size() == 0 || list.isEmpty()) {
            return restExceptionAndSuccessHandle.handleException("Không tìm thấy địa chỉ: " + area, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findcategory") // tìm theo danh mục(tình hình bệnh lý)
    public ResponseEntity<?> findCategory(@RequestHeader("Authorization") String token, @RequestParam String category) {
        List<ClinicsDTO> list = clinicsService.getClinicsDTOByCategory(category);
        if (list.size() == 0 || list.isEmpty()) {
            return restExceptionAndSuccessHandle.handleException("Không tìm thấy danh mục: " + category, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findpricerange") // tìm theo mức giá của cơ sở y tế
    public ResponseEntity<?> findPriceRange(@RequestHeader("Authorization") String token,
                                            @RequestParam Long minExaminationFee,
                                            @RequestParam Long maxExaminationFee) {
        List<ClinicsDTO> list = clinicsService.getClinicsDTOByExaminationFee(minExaminationFee, maxExaminationFee);
        if (list.size() == 0 || list.isEmpty()) {
            return restExceptionAndSuccessHandle.handleException("Không tìm thấy giá trong khoảng từ "
                    + minExaminationFee + " tới " + maxExaminationFee, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/findhealthcarefacility") // tìm theo tên cơ sở y tế
    public ResponseEntity<?> findHealthcareFacility(@RequestHeader("Authorization") String token, @RequestParam String name){
        List<ClinicsDTO> list = clinicsService.getClinicsDTOByName(name);
        if(list.size() == 0 || list.isEmpty()){
            return restExceptionAndSuccessHandle.handleException("Không tìm thấy cơ sở y tế: " + name, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/search-doctors")
    public ResponseEntity<?> searchDoctor(@RequestHeader("Authorization") String token, @RequestParam String specializationName){
        List<DoctorUsersDTO> list = doctorUsersService.getUserDoctorBySpecializationName(specializationName);
        int i = 1;
        List<Map<String, Object>> responseList = new ArrayList<>();
        for(DoctorUsersDTO doctorUsersDTO : list){
            Map<String, Object> response = new HashMap<>();
            doctorUsersDTO.getSpecializationDTO().setClinicsDTO(null);
            response.put(String.format("DoctorUser %d", i), doctorUsersDTO);
            responseList.add(response);
            i++;
        }
        if(list.size() == 0 || list.isEmpty()){
            return restExceptionAndSuccessHandle.handleException("Không tìm thấy bác sĩ chuyên khoa: " + specializationName, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
    @GetMapping("/clinicsService/featured")
    public ResponseEntity<?> getFeaturedClinics(@RequestHeader("Authorization") String token) {
        try {
            List<ClinicsDTO> originalClinics  = clinicsService.getFeaturedClinics();
//            List<ClinicsDTO> clinicsDTOS = new ArrayList<>(); // Tạo danh sách mới
//            for(ClinicsDTO clinicsDTO : originalClinics){
//                ClinicsDTO clinicsDTO1 = clinicsService.getClinicsDTOById(clinicsDTO.getSpecializationDTO().getClinicsDTO().getId());
//                if(clinicsDTO1 != null){
//                    clinicsDTO.getSpecializationDTO().setClinicsDTO(null);
//                }
//                clinicsDTOS.add(clinicsDTO);
//            }
            List<Map<String, Object>> mapList = new ArrayList<>();
            int i = 1;
            for(ClinicsDTO clinicsDTO : originalClinics){
                Map<String, Object> map = new HashMap<>();
                clinicsDTO.setSpecializationDTO(null);
                map.put(String.format("Clinic %d", i), clinicsDTO);
                mapList.add(map);
                i++;
            }
            // Kiểm tra nếu dữ liệu là null hoặc trống
            if (originalClinics.size() == 0 || originalClinics.isEmpty()) {
                // Trả về một thông báo nếu không có dữ liệu
                return restExceptionAndSuccessHandle.handleException("Không tìm thấy phòng khám nổi bật", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(mapList, HttpStatus.OK);
        } catch (Exception e) {
            return restExceptionAndSuccessHandle.handleException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/specializations/featured")
    public ResponseEntity<?> getFeaturedSpecializations(@RequestHeader("Authorization") String token) {
        try {
            List<SpecializationDTO> specializationDTOList = specializationService.getFeaturedSpecializations();
            List<Map<String, Object>> mapList = new ArrayList<>();
            int i = 1;
            for(SpecializationDTO specializationDTO : specializationDTOList){
                Map<String, Object> map = new HashMap<>();
                specializationDTO.setClinicsDTO(null);
                map.put(String.format("Specialization %d", i), specializationDTO);
                mapList.add(map);
                i++;
            }
            // Kiểm tra nếu dữ liệu là null hoặc trống
            if (specializationDTOList.size() == 0 || specializationDTOList.isEmpty()) {
                // Trả về một thông báo nếu không có dữ liệu
                return new ResponseEntity<>("No featured specializations found", HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(mapList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
