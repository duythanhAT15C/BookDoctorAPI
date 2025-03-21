package com.lab.Assignment03.Controller;

import com.lab.Assignment03.Exception.RestExceptionAndSuccessHandle;
import com.lab.Assignment03.Exception.UserException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private RestExceptionAndSuccessHandle restExceptionAndSuccessHandle;

    // Xử lý AccessDeniedException khi người dùng không có quyền truy cập
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
        return restExceptionAndSuccessHandle.handleException("Bạn không có quyền truy cập vào tài nguyên này.", HttpStatus.FORBIDDEN);
    }

    // Xử lý lỗi khi tham số đầu vào không đúng kiểu dữ liệu mong đợi
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String errorMessage = "Giá trị không hợp lệ cho tham số '" + ex.getName() + "': " + ex.getValue() +
                ". Loại dữ liệu mong đợi: " + ex.getRequiredType().getSimpleName();
        return restExceptionAndSuccessHandle.handleException(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // Xử lý lỗi khi thiếu header Authorization trong request
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<?> handleMissingHeader(MissingRequestHeaderException ex) {
        return restExceptionAndSuccessHandle.handleException("Thiếu header 'Authorization'", HttpStatus.BAD_REQUEST);
    }

    // Xử lý lỗi khi gặp IllegalArgumentException (ví dụ: truyền giá trị không hợp lệ)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return restExceptionAndSuccessHandle.handleException("Giờ không hợp lệ", HttpStatus.BAD_REQUEST);
    }
}
