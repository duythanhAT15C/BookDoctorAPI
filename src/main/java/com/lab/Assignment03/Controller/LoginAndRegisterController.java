package com.lab.Assignment03.Controller;

import com.lab.Assignment03.DTO.UserDTO;
import com.lab.Assignment03.Exception.Response;
import com.lab.Assignment03.Exception.RestExceptionAndSuccessHandle;
import com.lab.Assignment03.JwtAuthentication.JwtUtil;
import com.lab.Assignment03.Service.PatientsService;
import com.lab.Assignment03.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginAndRegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RestExceptionAndSuccessHandle restExceptionAndSuccessHandle;
    @Autowired
    private PatientsService patientsService;
    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody UserDTO userDTO){
        if(userService.isEmailExists(userDTO.getEmail())){
            return restExceptionAndSuccessHandle.handleException("Email đã tồn tại", HttpStatus.BAD_REQUEST);
        }
        if(userDTO.getRoleId() == 2){
            return restExceptionAndSuccessHandle.handleException("Không được đăng ký tài khoản bác sĩ", HttpStatus.BAD_REQUEST);
        }
        if(!userDTO.getPassword().equals(userDTO.getRePassword())){
            return restExceptionAndSuccessHandle.handleException("Mật khẩu không khớp", HttpStatus.BAD_REQUEST);
        }
        userService.save(userDTO);
        return restExceptionAndSuccessHandle.handleSuccess("Đăng ký thành công", HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody UserDTO userDTO, HttpSession session){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
            );
            if(!userService.ckeckIsValidUser(userDTO.getEmail())){
                return restExceptionAndSuccessHandle.handleException("Tài khoản đã bị khoá", HttpStatus.UNAUTHORIZED);
            }
            // Nếu xác thực thành công, tạo JWT (giả sử bạn có JwtService)
            String token = jwtUtil.generateToken(userDTO.getEmail());
            return restExceptionAndSuccessHandle.handleSuccess("token: " + token, HttpStatus.OK);

        } catch (AuthenticationException e) {
            return restExceptionAndSuccessHandle.handleException("Tên tài khoản hoặc mật khẩu không đúng", HttpStatus.UNAUTHORIZED);
        }
//        boolean isAuthenticated = userService.authenticate(userDTO.getEmail(), userDTO.getPassword());
//        if(isAuthenticated){
//            String token = jwtUtil.generateToken(userDTO.getEmail());
//            session.setAttribute("token", token);
//            return ResponseEntity.ok("token: " + token);
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tên tài khoản hoặc mật khẩu không đúng");
//        }
    }
}
