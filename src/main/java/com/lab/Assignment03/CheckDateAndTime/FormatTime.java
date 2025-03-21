package com.lab.Assignment03.CheckDateAndTime;

import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
@Component
public class FormatTime {
    // Các định dạng giờ hỗ trợ
    private static final List<String> TIME_FORMATS = Arrays.asList(
            "HH:mm", "hh:mm a", "HH:mm:ss", "hh:mm:ss a" // Các định dạng giờ
    );

    // Kiểm tra xem chuỗi giờ có hợp lệ hay không
    public static boolean isValidTime(String timeStr) {
        // Duyệt qua tất cả các định dạng để thử phân tích chuỗi giờ
        for (String format : TIME_FORMATS) {
            try {
                // Tạo DateTimeFormatter với định dạng
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                // Thử phân tích chuỗi giờ
                LocalTime time = LocalTime.parse(timeStr, formatter);
                return true;
            } catch (DateTimeParseException e) {
                // Nếu không thể phân tích với định dạng này, tiếp tục thử với định dạng khác
            }
        }
        return false; // Nếu không phân tích được giờ theo bất kỳ định dạng nào
    }

    // Phân tích chuỗi giờ thành LocalTime
    public static LocalTime parseTime(String timeStr) {
        for (String format : TIME_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                return LocalTime.parse(timeStr, formatter);
            } catch (DateTimeParseException e) {
                // Tiếp tục thử các định dạng khác nếu không thành công
            }
        }
        throw new IllegalArgumentException("Giờ không hợp lệ");
    }
}
