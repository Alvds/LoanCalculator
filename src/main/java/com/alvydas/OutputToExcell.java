package com.alvydas;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;
import org.apache.poi.ss.util.*;
import org.apache.poi.ss.usermodel.*;

public class OutputToExcell {

	 
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	DecimalFormat formatter = new DecimalFormat("0.00");
	

	//Creating excell file
		public  void writeExcell( double loanBalance, int numberOfPayments, double monthlyRate, double interestRate, double loanPayment, Date date ) throws IOException {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("ExcellSheet");
			//Heading
			Row heading = sheet.createRow(0);
			heading.createCell(0).setCellValue("Payment number");
			heading.createCell(1).setCellValue("Date");
			heading.createCell(2).setCellValue("Remaining amount");
			heading.createCell(3).setCellValue("Principal payment");
			heading.createCell(4).setCellValue("Interest payment");
			heading.createCell(5).setCellValue("Total payment");
			heading.createCell(6).setCellValue("Interest rate");
			for (int i = 0; i < 7; i++){
				sheet.autoSizeColumn(i);
				CellStyle styleHeading = workbook.createCellStyle();
				HSSFFont font = workbook.createFont();
				font.setBold(true);
				font.setFontName(HSSFFont.FONT_ARIAL);
				styleHeading.setFont(font);
				styleHeading.setVerticalAlignment(CellStyle.ALIGN_CENTER);
				styleHeading.setAlignment(CellStyle.ALIGN_CENTER);
				heading.getCell(i).setCellStyle(styleHeading);
			}
			
			Calendar cal = setDateToCalendar(date);
			double interestPayment = 0;
			double principalPayment = 0;
			double changedLoan = 0;
			for (int r = 1; r < numberOfPayments + 1; r++){
			
				interestPayment = loanBalance * monthlyRate;
				principalPayment = loanPayment - interestPayment;
				
			Row row = sheet.createRow(r); 
			//Payment Numbers
			Cell paymentNr = row.createCell(0);
			paymentNr.setCellValue(r);
			CellStyle paymentStyle = workbook.createCellStyle();
			paymentStyle.setAlignment(CellStyle.ALIGN_CENTER);
			paymentStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
			paymentNr.setCellStyle(paymentStyle);
			//Date cell
			Cell dateCell = row.createCell(1);
			dateCell.setCellValue(sdf.format(cal.getTime()));
			CellStyle dateStyle = workbook.createCellStyle();
			dateStyle.setAlignment(CellStyle.ALIGN_CENTER);
			dateStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
			dateCell.setCellStyle(dateStyle);
			//increase month
			cal = increaseByMonth(cal);
			//Balance
			Cell balanceCell = row.createCell(2);
			balanceCell.setCellValue(formatter.format(loanBalance));
			CellStyle balanceStyle = workbook.createCellStyle();
			balanceStyle.setAlignment(CellStyle.ALIGN_CENTER);
			balanceStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
			balanceCell.setCellStyle(balanceStyle);
			//Principal payment
			Cell principal = row.createCell(3);
			principal.setCellValue(formatter.format(principalPayment));
			CellStyle principalStyle = workbook.createCellStyle();
			principalStyle.setAlignment(CellStyle.ALIGN_CENTER);
			principalStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
			principal.setCellStyle(principalStyle);
			//Interest payment
			Cell interest = row.createCell(4);
			interest.setCellValue(formatter.format(interestPayment));
			CellStyle interestStyle = workbook.createCellStyle();
			interestStyle.setAlignment(CellStyle.ALIGN_CENTER);
			interestStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
			interestStyle.setIndention(CellStyle.ALIGN_CENTER);
			interest.setCellStyle(interestStyle);
			//Total payment
			Cell totalCell = row.createCell(5);
			totalCell.setCellValue(formatter.format(loanPayment));
			CellStyle totalStyle = workbook.createCellStyle();
			totalStyle.setAlignment(CellStyle.ALIGN_CENTER);
			totalStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
			totalCell.setCellStyle(totalStyle);
			//Interest rate
			Cell interestRateCell = row.createCell(6);
			interestRateCell.setCellValue(formatter.format(interestRate));
			CellStyle interestRateStyle = workbook.createCellStyle();
			interestRateStyle.setAlignment(CellStyle.ALIGN_CENTER);
			interestRateStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
			interestRateCell.setCellStyle(interestRateStyle);
			sheet.autoSizeColumn(r);
			
			changedLoan = loanBalance - principalPayment ;
			loanBalance = changedLoan;
			
			}
			
			String path = System.getProperty("user.home") + "/Desktop";
			// Save to Excell file
		     FileOutputStream out = new FileOutputStream (new File("Paskola.xls"));
			 workbook.write(out);
			 out.close();
			 workbook.close();
			 JOptionPane.showMessageDialog(null, "Excell file was created successfully", null, JOptionPane.PLAIN_MESSAGE);
		}
		
		//calendar iterator
		public  Calendar increaseByMonth(Calendar cal) {
			int month = cal.get(Calendar.MONTH);
			if (month == 11) {
				cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
				cal.set(Calendar.MONTH, Calendar.JANUARY);
			} else {
				cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
			}

			return cal;
		}
		//converter
		public  Calendar setDateToCalendar(Date data) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(data);
			return cal;
		}
}

