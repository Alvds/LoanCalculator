package com.alvydas;

import java.util.*;

public class AnnuityLoan  {

	private  double loanPayment;
	private  double loanBalance;
	private  double monthlyRate;
	private  double interestRate;
	private  int numberOfPayments;
	private double decimalInterestRate;
	private Date date;
	
	//Annuity loan
	double getLoanPayment() {
		loanPayment = ((loanBalance * monthlyRate)/
				(1 - Math.pow(1 + monthlyRate, -numberOfPayments)));
		return loanPayment;
	}

	
	double getMonthlyRate() {
		 monthlyRate = (interestRate/100)/12 ;
		 return monthlyRate;
	}
	

	public double getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(double loanBalance) {
		this.loanBalance = loanBalance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

	public int getNumberOfPayments() {
		return numberOfPayments;
	}

	public void setNumberOfPayments(int numberOfPayments) {
		this.numberOfPayments = numberOfPayments;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
