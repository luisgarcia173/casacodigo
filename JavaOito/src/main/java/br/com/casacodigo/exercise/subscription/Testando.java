package br.com.casacodigo.exercise.subscription;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import br.com.casacodigo.exercise.payments.Customer;

public class Testando {

	public static void main(String[] args) {

		// Massa Testes =====================================================
		BigDecimal monthlyFee = new BigDecimal("99.90");
		
		Customer luisc = new Customer("Luis Carlos");
		Customer luisn = new Customer("Luis Nascimento");
		Customer luisg = new Customer("Luis Garcia");
		
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		
		Subscription s1 = new Subscription(monthlyFee, yesterday.minusMonths(5), luisc);
		Subscription s2 = new Subscription(monthlyFee, yesterday.minusMonths(8), today.minusMonths(1), luisn);
		Subscription s3 = new Subscription(monthlyFee, yesterday.minusMonths(5), today.minusMonths(2), luisg);
		
		List<Subscription> subscriptions = Arrays.asList(s1, s2, s3);
		// Massa Testes =====================================================
		
		// Meses de assinatura
		long meses = ChronoUnit.MONTHS.between(s2.getBegin(), LocalDateTime.now());
		
		// Meses pagos com assinatura ativa
		long mesesAtivos = ChronoUnit.MONTHS.between(s2.getBegin(), s2.getEnd().orElse(LocalDateTime.now()));
		System.out.println("Meses(" + meses + "): " + mesesAtivos + " - " + s2.getCustomer().getName());
		
		// Calcular receita
		BigDecimal totalPaid = subscriptions.stream()
			.map(Subscription::getTotalPaid)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(totalPaid);
		
	}

}
