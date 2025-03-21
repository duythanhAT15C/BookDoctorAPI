package com.lab.Assignment03.ServiceImpl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.lab.Assignment03.Convert.ConvertToEntity;
import com.lab.Assignment03.DTO.ExtrainfosDTO;
import com.lab.Assignment03.DTO.PatientsDTO;
import com.lab.Assignment03.DTO.StatusesDTO;
import com.lab.Assignment03.Entity.Extrainfos;
import com.lab.Assignment03.Entity.Patients;
import com.lab.Assignment03.Entity.Statuses;
import com.lab.Assignment03.Repository.ExtrainfosRepository;
import com.lab.Assignment03.Service.PatientsService;
import com.lab.Assignment03.Service.PdfEmailService;
import com.lab.Assignment03.Service.StatusesService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class PdfEmailServiceImpl implements PdfEmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ExtrainfosRepository extrainfosRepository;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private StatusesService statusesService;
    @Override
    public String generatePdfAndSendEmail(ExtrainfosDTO extraInfosDTO) throws IOException, DocumentException, MessagingException {
        // Xác định thư mục "upload" trong src/main/resources/
        String uploadDir = Paths.get("src", "main", "resources", "upload").toString();
        Patients patients = patientsService.getPatients(extraInfosDTO.getPatientsDTO().getId());
        Statuses statuses = statusesService.getStatusesByPatientId(patients.getStatuses().getId());
        statuses.setName("SUCCESS");
        statuses.setUpdatedAt(LocalDateTime.now());
        statuses.setCreateAt(statuses.getCreateAt());
        patients.setStatuses(statuses);
        // Kiểm tra và tạo thư mục nếu chưa tồn tại
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Tạo tên file PDF theo ID bệnh nhân + thời gian
        String fileName = "BA_" + patients.getUsers().getName() + "_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".pdf";
        String filePath = Paths.get(uploadDir, fileName).toString();

        // Tạo file PDF
        // Tạo tài liệu PDF
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        // Cấu hình font chữ
        BaseFont baseFont = BaseFont.createFont("src/main/resources/fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font titleFont = new Font(baseFont, 16, Font.BOLD);
        Font headerFont = new Font(baseFont, 12, Font.BOLD);
        Font contentFont = new Font(baseFont, 12, Font.NORMAL);

        // Tiêu đề chính
        Paragraph title = new Paragraph("BỆNH ÁN BỆNH NHÂN", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph("\n"));

        // Thông tin bệnh nhân
        document.add(new Paragraph("THÔNG TIN BỆNH NHÂN", headerFont));
        document.add(new Paragraph("Họ và tên: " + patients.getUsers().getName(), contentFont));
        document.add(new Paragraph("Giới tính: " + patients.getUsers().getGender(), contentFont));
        document.add(new Paragraph("Địa chỉ: " + patients.getUsers().getAddress(), contentFont));
        document.add(new Paragraph("Số điện thoại: " + patients.getUsers().getPhone(), contentFont));
        document.add(new Paragraph("\n"));

        // Thông tin khám bệnh
        document.add(new Paragraph("THÔNG TIN KHÁM BỆNH", headerFont));
        document.add(new Paragraph("Lý do khám: " + extraInfosDTO.getReasonForVisit(), contentFont));
        document.add(new Paragraph("Tiền sử bệnh lý: " + extraInfosDTO.getHistoryBreath(), contentFont));
        document.add(new Paragraph("Triệu chứng: " + extraInfosDTO.getSymptoms(), contentFont));
        document.add(new Paragraph("Chẩn đoán: " + extraInfosDTO.getDiagnosis(), contentFont));
        document.add(new Paragraph("Hướng điều trị: " + extraInfosDTO.getTreatmentPlan(), contentFont));
        document.add(new Paragraph("\n"));

        // Kết quả xét nghiệm (nếu có)
        document.add(new Paragraph("KẾT QUẢ XÉT NGHIỆM", headerFont));
        document.add(new Paragraph(extraInfosDTO.getLabResults(), contentFont));
        document.add(new Paragraph("\n"));

        // Kết luận & Hướng dẫn điều trị
        document.add(new Paragraph("KẾT LUẬN VÀ HƯỚNG DẪN", headerFont));
        document.add(new Paragraph(extraInfosDTO.getDoctorAdvice(), contentFont));
        document.add(new Paragraph("\n"));

        // Thời gian tạo báo cáo
        // Định dạng thời gian theo kiểu dd/MM/yyyy HH:mm:ss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm", new Locale("vi", "VN"));
        // Format thời gian hiện tại
        String formattedDateTime = LocalDateTime.now().format(formatter);
        document.add(new Paragraph("Thời gian tạo: " + formattedDateTime, contentFont));
        document.add(new Paragraph("\n"));

        // Chữ ký bác sĩ
        document.add(new Paragraph("_________________________", contentFont));
        document.add(new Paragraph("Chữ ký bác sĩ", contentFont));

        document.close();
        // Lưu đường dẫn file PDF vào database
        Extrainfos extrainfos = extrainfosRepository.getExtrainfosByPatientId(patients.getId());
        extrainfos.setHistoryBreath(extraInfosDTO.getHistoryBreath());
        extrainfos.setPatients(patients);
        extrainfos.setOldForm(extrainfos.getSendForm());
        extrainfos.setSendForm(filePath);
        extrainfos.setMoreInfo(extraInfosDTO.getMoreInfo());
        extrainfos.setCreateAt(LocalDateTime.now());
        extrainfosRepository.save(extrainfos);

        // Gửi email có đính kèm file PDF
        sendEmailWithPdf(patients.getUsers().getEmail(), filePath, patients);

        return filePath;
    }

    @Override
    public void sendEmailWithPdf(String toEmail, String filePath, Patients patients) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("Thông tin bệnh án của bạn");
        helper.setText("Xin chào, đính kèm là file bệnh án của bạn.");

        // Tạo tên file đính kèm theo mã bệnh nhân
        String attachmentName = "BenhAn_" + patients.getUsers().getName() + ".pdf";
        FileSystemResource file = new FileSystemResource(new File(filePath));
        helper.addAttachment(attachmentName, file);

        mailSender.send(message);
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String text, String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        helper.addAttachment(new File(filePath).getName(), new File(filePath));

        mailSender.send(message);
    }
}
