package com.lab.Assignment03.Controller;

import com.lab.Assignment03.Exception.Response;
import com.lab.Assignment03.Exception.RestExceptionAndSuccessHandle;
import com.lab.Assignment03.JwtAuthentication.JwtUtil;
import com.lab.Assignment03.Service.EmailService;
import com.lab.Assignment03.Service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class ForgotPasswordController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailService emailService;
    @Autowired
    private RestExceptionAndSuccessHandle restExceptionAndSuccessHandle;
//    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping("/forgot-password")
    public ResponseEntity<Response> forgotPassword(@RequestParam String email) {
        // Kiểm tra xem email có tồn tại trong hệ thống không
        if (!userService.isEmailExists(email)) {
            return restExceptionAndSuccessHandle.handleException("Email không tồn tại", HttpStatus.BAD_REQUEST);
        }

        // Tạo token đặt lại mật khẩu dựa trên email
        String resetToken = jwtUtil.generateToken(email);

        // Tạo đường link đặt lại mật khẩu
        String resetLink = "http://localhost:8080/api/auth/reset-password?token=" + resetToken;

        // Gửi email chứa liên kết đặt lại mật khẩu cho người dùng
        emailService.sendEmail(email, "Xác nhận đổi mật khẩu", "Vui lòng nhấp vào liên kết để thay đổi mật khẩu: " + resetLink);

        // Trả về phản hồi thông báo đã gửi email thành công
        return restExceptionAndSuccessHandle.handleSuccess("Một email xác nhận đã được gửi đến " + email, HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Response> resetPassword(@RequestParam String token, @RequestParam String newPassword, @RequestParam String confirmPassword) {
        // Kiểm tra xem mật khẩu mới và xác nhận mật khẩu có trùng khớp không
        if (!newPassword.equals(confirmPassword)) {
            return restExceptionAndSuccessHandle.handleException("Mật khẩu không trùng khớp", HttpStatus.BAD_REQUEST);
        }
        try {
            // Giải mã token để lấy email của người dùng
            String email = jwtUtil.extractUsername(token);

            // Kiểm tra xem token có hợp lệ không và email có tồn tại trong hệ thống không
            if (email == null || !userService.isEmailExists(email)) {
                return restExceptionAndSuccessHandle.handleException("Token không hợp lệ hoặc đã hết hạn", HttpStatus.BAD_REQUEST);
            }

            // Cập nhật mật khẩu mới cho người dùng
            boolean reset = userService.updatePassword(email, newPassword);
            if (reset) {
                return restExceptionAndSuccessHandle.handleSuccess("Mật khẩu đã được thay đổi thành công", HttpStatus.OK);
            } else {
                return restExceptionAndSuccessHandle.handleException("Không thể đặt lại mật khẩu", HttpStatus.BAD_REQUEST);
            }
        } catch (ExpiredJwtException e) {
            // Xử lý trường hợp token đã hết hạn
            return restExceptionAndSuccessHandle.handleException("Token đã hết hạn", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác như token không hợp lệ hoặc bị sửa đổi
            return restExceptionAndSuccessHandle.handleException("Token không hợp lệ", HttpStatus.BAD_REQUEST);
        }
    }
}
