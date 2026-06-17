package com.school.management.School.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.school.management.School.entity.FeePayment;
import com.school.management.School.entity.School;
import com.school.management.School.entity.Student;
import com.school.management.School.entity.StudentFees;
import com.school.management.School.repository.FeePaymentRepository;
import com.school.management.School.repository.SchoolRepository;
import com.school.management.School.repository.StudentFeesRepository;
import com.school.management.School.repository.StudentRepository;

@Service
public class ReceiptPdfService {

	@Autowired
	private FeePaymentRepository feePaymentRepository;

	@Autowired
	private StudentFeesRepository studentFeesRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SchoolRepository schoolRepository;

	public byte[] generateReceipt(Long schoolId, Long studentId, Long studentFeeId) {

		try {

			// =========================
			// FETCH STUDENT
			// =========================

			Student student = studentRepository.findById(studentId)
					.orElseThrow(() -> new RuntimeException("Student not found"));

			// =========================
			// FETCH STUDENT FEES
			// =========================

			StudentFees studentFees = studentFeesRepository.findById(studentFeeId)
					.orElseThrow(() -> new RuntimeException("Student fee not found"));

			School school = schoolRepository.findById(schoolId)
					.orElseThrow(() -> new RuntimeException("School not found"));

			// =========================
			// FETCH PAYMENT HISTORY
			// =========================

			List<FeePayment> payments = feePaymentRepository.findBySchoolIdAndStudentFeeId(schoolId, studentFeeId);

			// =========================
			// CREATE PDF
			// =========================

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			Document document = new Document();

			PdfWriter.getInstance(document, out);

			document.open();

			Color primaryBlue = new Color(37, 99, 235);

			Color lightBlue = new Color(239, 246, 255);

			Color green = new Color(220, 252, 231);

			Color red = new Color(254, 226, 226);

			// =========================
			// FONT
			// =========================

			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);

			Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

			// =========================
			// TITLE
			// =========================

			document.add(new Paragraph(" "));

			PdfPTable header = new PdfPTable(1);

			header.setWidthPercentage(100);

			PdfPCell schoolCell = new PdfPCell();

			schoolCell.setBackgroundColor(primaryBlue);

			schoolCell.setBorder(Rectangle.NO_BORDER);

			schoolCell.setPadding(15);

			Paragraph schoolName =
			        new Paragraph(
			                school.getSchoolName().toUpperCase(),
			                FontFactory.getFont(
			                        FontFactory.HELVETICA_BOLD,
			                        22,
			                        Color.WHITE
			                )
			        );

			schoolName.setAlignment(
			        Element.ALIGN_CENTER
			);

			schoolCell.addElement(schoolName);
			if (school.getCity() != null) {

			    Paragraph address =
			            new Paragraph(
			                    school.getAddress(),
			                    FontFactory.getFont(
			                            FontFactory.HELVETICA,
			                            10,
			                            Color.WHITE
			                    )
			            );

			    address.setAlignment(
			            Element.ALIGN_CENTER
			    );

			    schoolCell.addElement(address);
			}
			header.addCell(schoolCell);
			document.add(header);
			document.add(new Paragraph(" "));

			// =========================
			// STUDENT DETAILS
			// =========================

			PdfPTable studentTable = new PdfPTable(2);

			studentTable.setWidthPercentage(100);

			studentTable.addCell("Student Name");
			studentTable.addCell(student.getFullName());

			studentTable.addCell("Father Name");
			studentTable.addCell(student.getParentName());

			studentTable.addCell("Mobile");
			studentTable.addCell(student.getParentMobile());

			studentTable.addCell("Class");
			studentTable.addCell(student.getClassName());

			studentTable.addCell("Academic Year");
			studentTable.addCell(studentFees.getAcademicYear());

			document.add(studentTable);

			document.add(new Paragraph(" "));

			// =========================
			// FEE SUMMARY
			// =========================

			PdfPTable summary = new PdfPTable(3);

			summary.setWidthPercentage(100);

			PdfPCell total = new PdfPCell(new Phrase("TOTAL\n₹ " + String.valueOf(
				    studentFees.getTotalAmount().longValue()
					)));

			total.setBackgroundColor(lightBlue);

			summary.addCell(total);

			PdfPCell paid = new PdfPCell(new Phrase("PAID\n₹ " + String.valueOf(
				    studentFees.getPaidAmount().longValue()
					)));

			paid.setBackgroundColor(green);

			summary.addCell(paid);

			PdfPCell due = new PdfPCell(new Phrase("DUE\n₹ " + String.valueOf(
				    studentFees.getDueAmount().longValue()
					)));

			due.setBackgroundColor(red);

			summary.addCell(due);

			document.add(summary);

			document.add(new Paragraph(" "));

			// =========================
			// PAYMENT HISTORY TITLE
			// =========================

			Paragraph paymentTitle = new Paragraph("Payment History", titleFont);

			document.add(paymentTitle);

			document.add(new Paragraph(" "));

			// =========================
			// TABLE
			// =========================

			PdfPTable table = new PdfPTable(5);

			table.setWidthPercentage(100);

			String[] headers = { "Receipt No", "Date", "Amount", "Mode", "Remarks" };

			for (String h : headers) {

				PdfPCell cell = new PdfPCell(
						new Phrase(h, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.WHITE)));

				cell.setBackgroundColor(primaryBlue);

				table.addCell(cell);
			}

			for (FeePayment payment : payments) {

				table.addCell(payment.getReceiptNumber() != null ? payment.getReceiptNumber() : "-");

				table.addCell(payment.getPaymentDate() != null ? payment.getPaymentDate().toString() : "-");

				table.addCell("₹ " + String.valueOf(
						payment.getAmountPaid().longValue()));

				table.addCell(payment.getPaymentMode() != null ? payment.getPaymentMode() : "-");

				table.addCell(payment.getRemarks() != null ? payment.getRemarks() : "-");
			}

			document.add(table);

			document.add(new Paragraph(" "));
			document.add(new Paragraph("────────────────────────────────────────────────────────"));

			Paragraph footer =
			        new Paragraph(
			        		"Thank you for choosing our school.\n" +
			        		        "We appreciate your timely fee payment.\n\n" +
			        		        "Note:This is a computer generated receipt and does not require a signature."
			        );

			footer.setAlignment(
			        Element.ALIGN_LEFT
			);

			footer.setSpacingBefore(20);

			document.add(footer);

			// =========================
			// CLOSE
			// =========================

			document.close();

			return out.toByteArray();

		} catch (Exception e) {

			throw new RuntimeException("Error generating receipt PDF", e);
		}
	}

}
