package com.lab.Assignment03.Service;

import com.itextpdf.text.DocumentException;
import com.lab.Assignment03.DTO.ExtrainfosDTO;
import com.lab.Assignment03.DTO.PatientsDTO;
import com.lab.Assignment03.Entity.Patients;
import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PdfEmailService {
    public String generatePdfAndSendEmail(ExtrainfosDTO extraInfosDTO) throws IOException, DocumentException, MessagingException;
    public void sendEmailWithPdf(String toEmail, String filePath, Patients patients) throws MessagingException;
    public void sendEmailWithAttachment(String to, String subject, String text, String filePath) throws MessagingException;
}
