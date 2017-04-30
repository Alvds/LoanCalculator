package com.alvydas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UserInterface extends JFrame {
 
	private JPanel contentPane;
	private JTextField date;
	private JTextField loan;
	private JTextField interestRate;
	private JTextField numberOfPayments;
	private JLabel lblEnterLoanAmount;
	private JLabel lblEnterInterestPayment;
	private JLabel lblEnterNumberOf;
	Date data;
	SimpleDateFormat dateformate = new SimpleDateFormat("yyyy.MM.dd");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface frame = new UserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		date = new JTextField();
		date.setBounds(222, 35, 145, 20);
		panel.add(date);
		date.setColumns(10);
		
		loan = new JTextField();
		loan.setBounds(222, 66, 145, 20);
		panel.add(loan);
		loan.setColumns(10);
		
		interestRate = new JTextField();
		interestRate.setBounds(222, 97, 145, 20);
		panel.add(interestRate);
		interestRate.setColumns(10);
		
		numberOfPayments = new JTextField();
		numberOfPayments.setBounds(222, 129, 145, 20);
		panel.add(numberOfPayments);
		numberOfPayments.setColumns(10);
		
		JLabel lblEnterDateIn = new JLabel("Enter date in format: YYYY.MM.DD");
		lblEnterDateIn.setBounds(10, 35, 223, 20);
		panel.add(lblEnterDateIn);
		
		JLabel lblWelcomeToLoan = new JLabel("Welcome to Loan Amortization Calculator!!!");
		lblWelcomeToLoan.setBounds(112, -1, 269, 25);
		panel.add(lblWelcomeToLoan);
		
		lblEnterLoanAmount = new JLabel("Enter loan amount");
		lblEnterLoanAmount.setBounds(83, 66, 150, 20);
		panel.add(lblEnterLoanAmount);
		
		lblEnterInterestPayment = new JLabel("Enter interest rate");
		lblEnterInterestPayment.setBounds(63, 97, 170, 20);
		panel.add(lblEnterInterestPayment);
		
		lblEnterNumberOf = new JLabel("Enter number of payments");
		lblEnterNumberOf.setBounds(43, 128, 188, 20);
		panel.add(lblEnterNumberOf);
		
		JButton btnCalculate = new JButton("CALCULATE");
		btnCalculate.setBounds(169, 206, 118, 23);
		panel.add(btnCalculate);
		btnCalculate.addActionListener(new CalculateButtonListener());
	}
	
	//listener
	public class CalculateButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent ev) {
			
			String loanBalance = loan.getText();
			String payments = numberOfPayments.getText();
			String interest = interestRate.getText();
			
			try {
				data = dateformate.parse(date.getText());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			double loanBal = Double.parseDouble(loanBalance);
			int payment = Integer.parseInt(payments);
			int interestp = Integer.parseInt(interest);
			
			AnnuityLoan inf = new AnnuityLoan();
			
			
			inf.setLoanBalance(loanBal);
			inf.setInterestRate(interestp);
			inf.setNumberOfPayments(payment);
			inf.setDate(data);
			
			OutputToExcell ex = new OutputToExcell();
			try {
				ex.writeExcell(inf.getLoanBalance(), inf.getNumberOfPayments(), inf.getMonthlyRate(), inf.getInterestRate(), inf.getLoanPayment(), inf.getDate());
			} catch (IOException e) {
				e.printStackTrace();
			}
			dispose();
			
		}
		
	}
}
