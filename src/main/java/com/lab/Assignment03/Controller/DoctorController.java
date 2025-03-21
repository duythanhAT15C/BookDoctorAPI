package com.lab.Assignment03.Controller;

import com.itextpdf.text.DocumentException;
import com.lab.Assignment03.Convert.ConvertToDTO;
import com.lab.Assignment03.DTO.*;
import com.lab.Assignment03.Entity.Comments;
import com.lab.Assignment03.Entity.DoctorUsers;
import com.lab.Assignment03.Entity.Patients;
import com.lab.Assignment03.Entity.Users;
import com.lab.Assignment03.Exception.RestExceptionAndSuccessHandle;
import com.lab.Assignment03.Service.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctor")
@PreAuthorize("hasRole('DOCTOR')")
public class DoctorController {
    @Autowired
    private UserService userService;
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private DoctorUsersService doctorUsersService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private RestExceptionAndSuccessHandle restExceptionAndSuccessHandle;
    @Autowired
    private PostsService postsService;
    @Autowired
    private ExtrainfosService extrainfosService;
    @Autowired
    private StatusesService statusesService;
    @Autowired
    private ConvertToDTO convertToDTO;
    @Autowired
    private PdfEmailService pdfEmailService;
    private final String TARGET_DIR = System.getProperty("user.dir") + "/src/main/resources/upload/";


    @GetMapping("/list-patients") // lấy danh sách bệnh nhân đăng ký khám
    public ResponseEntity<?> listPatients(@RequestHeader("Authorization") String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findByEmail(username);
        DoctorUsersDTO doctorUsersDTO = doctorUsersService.getDoctorByDoctorUsersId(userDTO.getId());
        List<PatientsDTO> getListPatients = patientsService.getPatientsByDoctorId(doctorUsersDTO.getId());
        List<Map<String, Object>> mapList = new ArrayList<>();
        int i = 1;
        for (PatientsDTO patientsDTO : getListPatients) {
            Map<String, Object> map = new HashMap<>();
            ExtrainfosDTO extrainfosDTO = extrainfosService.getExtrainfosDTOByPatientId(patientsDTO.getId());
            map.put(String.format("Patient %d", i), extrainfosDTO);
            mapList.add(map);
            i++;
        }
        if (getListPatients.size() == 0 || getListPatients.isEmpty()) {
            return restExceptionAndSuccessHandle.handleException("Không có bệnh nhân nào", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mapList, HttpStatus.OK);
    }

    @PostMapping("/add-schedules")
    public ResponseEntity<?> addSchedules(@RequestHeader("Authorization") String token, @RequestBody SchedulesDTO schedulesDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findByEmail(username);
        if (scheduleService.save(schedulesDTO, userDTO)) {
            return restExceptionAndSuccessHandle.handleSuccess("Đăng lịch khám thành công", HttpStatus.OK);
        }
        return restExceptionAndSuccessHandle.handleException("Không đăng được lịch khám", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/list-booking") // lấy danh sách bệnh nhân đang khám
    public ResponseEntity<?> listBooking(@RequestHeader("Authorization") String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findByEmail(username);

        List<PostDTO> list = postsService.getListPostsByDoctorUsersId(userDTO.getId());
        List<PatientsDTO> listPatientDTO = new ArrayList<>();
//        for(PostDTO postDTO : list){
//            PatientsDTO patientsDTOS = patientsService.getPatientsByDoctorIdAndPatientId(userDTO.getId(), comments.getPatientUsers().getId());
//            listPatientDTO.add(patientsDTOS);
//        }

        if (list.size() == 0 || list.isEmpty()) {
            return restExceptionAndSuccessHandle.handleException("Không có bệnh nhân nào", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestHeader("Authorization") String token, @RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!isAuthenticated(authentication)) {
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }

        String username = authentication.getName();
        UserDTO userDTO = userService.findByEmail(username);
        DoctorUsersDTO doctorUsersDTO = doctorUsersService.getDoctorByDoctorUsersId(userDTO.getId());

        PostDTO postDTO = postsService.getPostsByDoctorUsersIdAndPostsId(id, doctorUsersDTO.getId());
        if (postDTO == null) {
            return restExceptionAndSuccessHandle.handleException("Không có bệnh nhân nào đăng ký lịch khám", HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra bệnh nhân có thuộc bác sĩ này không
        PostDTO postDTO1 = postsService.getPostDTOByDoctorUsersIdAndWriterId(doctorUsersDTO.getId(), postDTO.getUserDTO().getId());
        if (postDTO1 == null) {
            return restExceptionAndSuccessHandle.handleException("Không tìm thấy bệnh nhân", HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra số lượng lịch đặt của bác sĩ
        if (scheduleService.maxBooking(doctorUsersDTO.getId(), 1) == -1) {
            return restExceptionAndSuccessHandle.handleException("Đã vượt quá số lượng đặt lịch", HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra trạng thái của bệnh nhân
        if (patientsService.isExistsPatient(doctorUsersDTO.getId(), postDTO.getUserDTO().getId())) {
            PatientsDTO patientsDTO = patientsService.getPatientsByDoctorIdAndPatientId(doctorUsersDTO.getId(), postDTO1.getUserDTO().getId());
            return processExistingPatient(patientsDTO, doctorUsersDTO, id);
        } else {
            return processNewPatient(postDTO, doctorUsersDTO, id);
        }
    }

    /**
     * Kiểm tra authentication hợp lệ không
     */
    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }

    /**
     * Xử lý trường hợp bệnh nhân đã tồn tại trong hệ thống
     */
    private ResponseEntity<?> processExistingPatient(PatientsDTO patientsDTO, DoctorUsersDTO doctorUsersDTO, int id) {
        StatusesDTO statusesDTO = statusesService.getStatusesDTOByPatientId(patientsDTO.getStatusesDTO().getId());
        PostDTO postDTO = postsService.getPostById(id); // Lấy thông tin bài đăng để có confirmByDoctor
        if ("SUCCESS".equals(statusesDTO.getName()) && postDTO == null) {
            statusesService.save(statusesDTO, 1);
            return restExceptionAndSuccessHandle.handleSuccess("Nhận lịch khám bệnh thành công", HttpStatus.OK);
        }
        statusesService.save(patientsDTO.getStatusesDTO(), 1);
        boolean setStatus = postsService.updateConfirmByDoctor(id, doctorUsersDTO.getId(), 1);
        if (setStatus) {
            if (postDTO == null) {
                return restExceptionAndSuccessHandle.handleException("Không tìm thấy bài đăng", HttpStatus.BAD_REQUEST);
            }
            scheduleService.updateSchedule(doctorUsersDTO.getId(), 1, postDTO.getConfirmByDoctor());
            return restExceptionAndSuccessHandle.handleSuccess("Nhận lịch khám bệnh thành công", HttpStatus.OK);
        } else {
            return restExceptionAndSuccessHandle.handleException("Đã nhận lịch khám của bệnh nhân này", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Xử lý trường hợp bệnh nhân mới
     */
    private ResponseEntity<?> processNewPatient(PostDTO postDTO, DoctorUsersDTO doctorUsersDTO, int id) {
        boolean setStatus = postsService.updateConfirmByDoctor(id, doctorUsersDTO.getId(), 1);
        if (!setStatus) {
            return restExceptionAndSuccessHandle.handleException("Không có bệnh nhân nào đăng ký ở đây", HttpStatus.BAD_REQUEST);
        }

        postDTO.setDoctorUsersDTO(doctorUsersDTO);
        if (!patientsService.save(postDTO, id)) {
            return restExceptionAndSuccessHandle.handleException("Đã nhận lịch khám bệnh này", HttpStatus.BAD_REQUEST);
        }

        // Lưu thông tin bổ sung
        PatientsDTO patientsDTO = patientsService.getPatientsDTOByPatientsId(postDTO.getUserDTO().getId());
        ExtrainfosDTO extrainfosDTO = new ExtrainfosDTO();
        extrainfosDTO.setPatientsDTO(patientsDTO);
        extrainfosService.save(extrainfosDTO);

        // Cập nhật số lượng lịch đặt
        if (scheduleService.updateSchedule(doctorUsersDTO.getId(), 1, postDTO.getConfirmByDoctor()) == -1) {
            return restExceptionAndSuccessHandle.handleException("Đã vượt quá số lượng đặt lịch", HttpStatus.BAD_REQUEST);
        }

        return restExceptionAndSuccessHandle.handleSuccess("Nhận lịch khám bệnh thành công", HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(@RequestHeader("Authorization") String token, @RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // lấy thông tin của bác sĩ
        UserDTO userDTO = userService.findByEmail(username);
        DoctorUsersDTO doctorUsersDTO = doctorUsersService.getDoctorByDoctorUsersId(userDTO.getId());
        // lấy post theo id và doctor id, nếu có tức là hàng trong bảng thuộc về bác sĩ và có thể nhận lịch khám
        // nếu không tức là hàng trong bảng không phải của bác sĩ nên không cho phép nhận lịch khám
        PostDTO postDTO = postsService.getPostsByDoctorUsersIdAndPostsId(id, doctorUsersDTO.getId()); // lấy comment theo id
        if (postDTO == null) {
            return restExceptionAndSuccessHandle.handleException("Không có bệnh nhân nào đăng ký lịch khám", HttpStatus.BAD_REQUEST);
        } else {
            int flag = 0; // Đặt trạng thái hủy lịch khám
            boolean setStatus = postsService.updateConfirmByDoctor(id, doctorUsersDTO.getId(), flag);
            if (setStatus) {
                // Lấy thông tin bệnh nhân
                PatientsDTO patientsDTO = patientsService.getPatientsDTOByPatientsId(postDTO.getUserDTO().getId());
                if (patientsDTO != null) {
                    // Cập nhật trạng thái của bệnh nhân thành hủy lịch
                    statusesService.save(patientsDTO.getStatusesDTO(), 0);
                }
                // Cập nhật số lượng đặt lịch trong bảng schedule
                if (scheduleService.updateSchedule(doctorUsersDTO.getId(), flag, postDTO.getConfirmByDoctor()) == -1) { // cập nhật sumbooking trong bảng schedule
                    return restExceptionAndSuccessHandle.handleException("Đã vượt quá số lượng đặt lịch", HttpStatus.BAD_REQUEST);
                }
                return restExceptionAndSuccessHandle.handleSuccess("Huỷ lịch khám bệnh thành công", HttpStatus.OK);
            }
            return restExceptionAndSuccessHandle.handleException("Đã huỷ lịch khám của bệnh nhân", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/extrainfos")
    public ResponseEntity<?> saveExtraInfo(@RequestHeader("Authorization") String token, @RequestBody ExtrainfosDTO extrainfosDTO) throws MessagingException, DocumentException, FileNotFoundException {
        try {
            // Lấy username từ token đã xác thực
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            // Lấy thông tin bệnh nhân dựa vào ID từ request
            PatientsDTO patientsDTO = patientsService.getPatientsDTO(extrainfosDTO.getPatientsDTO().getId());
            StatusesDTO statusesDTO = statusesService.getStatusesDTOByPatientId(patientsDTO.getId());

            // Kiểm tra trạng thái bệnh nhân, nếu đã khám hoặc đã hủy thì không thể gửi thông tin thêm
            if (statusesDTO.getName().equals("SUCCESS")) {
                return restExceptionAndSuccessHandle.handleException("Bệnh nhân đã khám xong và gửi thông tin về email", HttpStatus.BAD_REQUEST);
            }
            if(statusesDTO.getName().equals("CANCELLED")){
                return restExceptionAndSuccessHandle.handleException("Bệnh nhân đã bị huỷ lịch khám, không thể gửi email", HttpStatus.BAD_REQUEST);
            }

            // Lấy thông tin bác sĩ đang đăng nhập
            UserDTO userDTO = userService.findByEmail(username);
            DoctorUsersDTO doctorUsersDTO = doctorUsersService.getDoctorByDoctorUsersId(userDTO.getId());

            // Gán bác sĩ cho bệnh nhân
            patientsDTO.setDoctorUsersDTO(doctorUsersDTO);
            extrainfosDTO.setPatientsDTO(patientsDTO);

            // Lấy thông tin cuộc hẹn giữa bác sĩ và bệnh nhân
            PostDTO postDTO = postsService.getPostDTOByDoctorUsersIdAndWriterId(doctorUsersDTO.getId(), patientsDTO.getUserDTO().getId());

            // Cập nhật lịch trình (schedule) sau khi thêm thông tin
            scheduleService.updateSchedule(doctorUsersDTO.getId(), 0, postDTO.getConfirmByDoctor());

            // Tạo file PDF và gửi email cho bệnh nhân
            String filePath = pdfEmailService.generatePdfAndSendEmail(extrainfosDTO);

            // Xóa lịch hẹn sau khi gửi thông tin
            postsService.deletePosts(postDTO);
            return restExceptionAndSuccessHandle.handleSuccess("PDF đã được tạo và gửi email thành công! Đường dẫn: " + filePath, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return restExceptionAndSuccessHandle.handleSuccess("Không tạo hoặc không gửi được file pdf", HttpStatus.BAD_REQUEST);
        }
    }
}
