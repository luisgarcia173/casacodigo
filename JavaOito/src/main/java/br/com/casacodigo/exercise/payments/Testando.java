package br.com.casacodigo.exercise.payments;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Testando {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Clientes
		Customer luisc = new Customer("Luis Carlos");
		Customer luisn = new Customer("Luis Nascimento");
		Customer luisg = new Customer("Luis Garcia");
		Customer luiscng = new Customer("Luis C N Garcia");
		
		// Produtos
		Product mAdele = new Product("Adele Completo", Paths.get("/music/adele.mp3"), new BigDecimal(59));
		Product mLegiao = new Product("Legiao Completo", Paths.get("/music/legiao.mp3"), new BigDecimal(59));
		Product mNirvana = new Product("Nirvana Completo", Paths.get("/music/nirvana.mp3"), new BigDecimal(59));
		Product fVelozes = new Product("Velozes 7", Paths.get("/movie/fast7.mov"), new BigDecimal(24.9));
		Product fVingadores = new Product("Os Vingadores", Paths.get("/movies/vingadores.mov"), new BigDecimal(24.9));
		Product fPitchPerfect = new Product("Pitch Perfect", Paths.get("/movie/pp.mov"), new BigDecimal(24.9));
		Product fTriploX = new Product("Triplo X", Paths.get("/movies/tx.mov"), new BigDecimal(24.9));
		Product iBrasil = new Product("Bandeira Brasil", Paths.get("/image/br-flag.png"), new BigDecimal(10));
		Product iItalia = new Product("Bandeira Italia", Paths.get("/image/it-flag.png"), new BigDecimal(10));
		Product iFranca = new Product("Bandeira Franca", Paths.get("/image/fr-flag.png"), new BigDecimal(10));
		
		// Pagamentos
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		LocalDateTime lastMonth = today.minusMonths(1);
		
		Payment payment1 = new Payment(Arrays.asList(mAdele, mLegiao), today, luisc);
		Payment payment2 = new Payment(Arrays.asList(fVelozes, fVingadores), yesterday, luisn);
		Payment payment3 = new Payment(Arrays.asList(mNirvana, fPitchPerfect), today, luisg);
		Payment payment4 = new Payment(Arrays.asList(fTriploX, iBrasil), today, luiscng);
		Payment payment5 = new Payment(Arrays.asList(iItalia, iFranca), lastMonth, luiscng);
		
		List<Payment> payments = Arrays.asList(payment1, payment2, payment3, payment4, payment5);
		
		// Ordenando pagamentos
		payments.stream()
			.sorted(Comparator.comparing(Payment::getDate)) // Ordenando por data
			.forEach(System.out::println);
		
		// Somando os pagamentos
		payment1.getProducts().stream()
			.map(Product::getPrice)
			.reduce(BigDecimal::add)
			.ifPresent(System.out::println);
		
		// Soma total c/ stream (total por cliente)
		Stream<BigDecimal> pricesStream = payments.stream()
			.map(p -> p.getProducts().stream()
				.map(Product::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add));
		
		// Soma total com reduce da stream (total geral)
		BigDecimal total = payments.stream()
			.map(p -> p.getProducts().stream()
				.map(Product::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add))
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(total);
		
		// Exibe preco por produto
		Stream<BigDecimal> priceOfEachProduct = payments.stream()
			.flatMap(p -> p.getProducts().stream().map(Product::getPrice));
		priceOfEachProduct.forEach(System.out::println);
		
		// Total do flatMap
		BigDecimal totalFlat = payments.stream()
			.flatMap(p -> p.getProducts().stream().map(Product::getPrice))
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(totalFlat);
		
		// Produtos mais vendidos
		Map<Product, Long> topProducts = payments.stream()
			.flatMap(p -> p.getProducts().stream())
			.collect(Collectors.groupingBy(Function.identity(),
				Collectors.counting()));
		// Exibindo de forma legivel
		topProducts.entrySet().stream()
			//.forEach(System.out::println);
			.max(Comparator.comparing(Map.Entry::getValue)) // Recuperando o com maior valor
			.ifPresent(System.out::println);
		
		// Valor gerado por produto
		Map<Product, BigDecimal> totalValuePerProduct = payments.stream()
			.flatMap(p -> p.getProducts().stream())
			.collect(Collectors.groupingBy(Function.identity(),
				Collectors.reducing(BigDecimal.ZERO, Product::getPrice, BigDecimal::add)));
		// Iterando Map
		totalValuePerProduct.entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry::getValue))
			.forEach(System.out::println);
		
		// Produtos por cliente
		Map<Customer, List<List<Product>>> customerToProductsList = payments.stream()
			.collect(Collectors.groupingBy(Payment::getCustomer,
				Collectors.mapping(Payment::getProducts, Collectors.toList())));
		Map<Customer, List<Product>> customerToProducts2steps = customerToProductsList.entrySet().stream()
			.collect(Collectors.toMap(Map.Entry::getKey,
				e -> e.getValue().stream()
					.flatMap(List::stream)
					.collect(Collectors.toList())));
		customerToProducts2steps.entrySet().stream()
			.sorted(Comparator.comparing(e -> e.getKey().getName()))
			.forEach(System.out::println);
		
		// Ou usando reducing
		Map<Customer, List<Product>> customerToProducts = payments.stream()
			.collect(Collectors.groupingBy(Payment::getCustomer,
				Collectors.reducing(Collections.emptyList(),
					Payment::getProducts,
					(l1, l2) -> { List<Product> l = new ArrayList<>();
						l.addAll(l1);
						l.addAll(l2);
						return l;} )));
		customerToProducts.entrySet().stream()
			.sorted(Comparator.comparing(e -> e.getKey().getName()))
			.forEach(System.out::println);
		
		// Cliente com maior gasto
		Function<Payment, BigDecimal> paymentToTotal = p -> p.getProducts().stream()
			.map(Product::getPrice)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		Map<Customer, BigDecimal> totalValuePerCustomer = payments.stream()
			.collect(Collectors.groupingBy(Payment::getCustomer,
				Collectors.reducing(BigDecimal.ZERO,
				paymentToTotal,
				BigDecimal::add)));
		totalValuePerCustomer.entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry::getValue))
			.forEach(System.out::println);
		
		// Pagamentos por mes
		Map<YearMonth, List<Payment>> paymentsPerMonth = payments.stream()
			.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate())));
		paymentsPerMonth.entrySet().stream()
			.forEach(System.out::println);
		
		// Total faturado Mes
		Map<YearMonth, BigDecimal> paymentsValuePerMonth = payments.stream()
			.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate()),
				Collectors.reducing(BigDecimal.ZERO, paymentToTotal, BigDecimal::add)));
		paymentsValuePerMonth.entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry::getValue))
			.forEach(System.out::println);
	}

}
