package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {

		SpringApplication.run(HomebankingApplication.class, args);

	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository){
		return (args) -> {

			Client clientMelba = new Client("mmorel@outlook.com", "Melba", "Morel");
			Client clientMatt = new Client("matt@outlook.com", "Matt", "Smith");


			clientRepository.save(clientMelba);
			clientRepository.save(clientMatt);
			LocalDate currentDate = LocalDate.now();
			LocalDate tomorowDate = currentDate.plusDays(1);


			Account account1 = new Account("VIN001", 5000.0, currentDate, clientMelba);
			Account account2 = new Account("VIN002", 7500.0, tomorowDate, clientMelba);
			accountRepository.save(account1);
			accountRepository.save(account2);


			Account account3 = new Account("VIN004", 100000.0, currentDate, clientMatt);
			Account account4 = new Account("VIN005", 95000.0, tomorowDate.plusDays(7), clientMatt);

			accountRepository.save(account3);
			accountRepository.save(account4);

			Transaction transaction1 = new Transaction(TransactionType.CREDIT, 500.0, "Bono", tomorowDate.plusDays(2), account1);
			Transaction transaction2 = new Transaction(TransactionType.DEBIT, 200.0, "Impuesto", tomorowDate.plusDays(2), account1);
			Transaction transaction3 = new Transaction(TransactionType.CREDIT, 5000.0, "Bono", tomorowDate.plusDays(3), account2);
			Transaction transaction4 = new Transaction(TransactionType.DEBIT, 539.0, "Deduccion de ganancia", tomorowDate.plusDays(3), account2);
			Transaction transaction5 = new Transaction(TransactionType.CREDIT, 1900.0, "Venta realizada", tomorowDate.plusDays(4), account3);
			Transaction transaction6 = new Transaction(TransactionType.DEBIT, 190.0, "Aranceles", tomorowDate.plusDays(4), account3);
			Transaction transaction7 = new Transaction(TransactionType.DEBIT, 100.0, "Impuesto venta", tomorowDate.plusDays(4), account3);
			Transaction transaction8 = new Transaction(TransactionType.CREDIT, 100000.0, "Sueldo", tomorowDate.plusDays(5), account4);
			Transaction transaction9 = new Transaction(TransactionType.DEBIT, 25000.0, "Jubilacion", tomorowDate.plusDays(5), account4);

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);
			transactionRepository.save(transaction5);
			transactionRepository.save(transaction6);
			transactionRepository.save(transaction7);
			transactionRepository.save(transaction8);
			transactionRepository.save(transaction9);

			Loan loan1 = new Loan("Hipotecario", 500000.0, List.of(12, 24, 36, 48, 60));
			Loan loan2 = new Loan("Personal", 100000.0, List.of(6, 12, 24));
			Loan loan3 = new Loan("Automotriz", 300000.0, List.of(6, 12, 24, 36));

			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan(400000.0, 60, clientMelba, loan1);
			ClientLoan clientLoan2 = new ClientLoan(50000.0, 12, clientMelba, loan2);

			ClientLoan clientLoan3 = new ClientLoan(100000.0, 24, clientMatt, loan2);
			ClientLoan clientLoan4 = new ClientLoan(200000.0, 36, clientMatt, loan3);

			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);









		};
	}

}
