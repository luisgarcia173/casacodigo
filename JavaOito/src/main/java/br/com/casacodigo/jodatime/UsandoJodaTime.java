package br.com.casacodigo.jodatime;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;

public class UsandoJodaTime {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		// Manipular data jeito antigo
		Calendar mesQueVem = Calendar.getInstance();
		mesQueVem.add(Calendar.MONTH, 1);
		
		// Com java 8: a classe LocalDate representa uma data sem horário nem timezone
		LocalDate mesQueVem8 = LocalDate.now().plusMonths(1);
		System.out.println(mesQueVem8);
		
		// Data e hora
		LocalDateTime agora = LocalDateTime.now();
		System.out.println(agora);
		
		// Somente hora
		LocalTime agoraHora = LocalTime.now();
		System.out.println(agoraHora);
		
		// Hoje ao meio dia
		LocalDateTime hojeAoMeioDia = LocalDate.now().atTime(12,0);
		System.out.println(hojeAoMeioDia);
		
		// Combinando datas e horarios
		LocalTime agora2 = LocalTime.now();
		LocalDate hoje = LocalDate.now();
		LocalDateTime dataEhora = hoje.atTime(agora2);
		System.out.println(dataEhora);
		
		// Usando time zone
		ZonedDateTime dataComHoraETimezone = dataEhora.atZone(ZoneId.of("America/Sao_Paulo"));
		System.out.println(dataComHoraETimezone);
		
		// Removendo time zone
		LocalDateTime semTimeZone = dataComHoraETimezone.toLocalDateTime();
		System.out.println(semTimeZone);
		
		// Usando factory method para criar objetos
		LocalDate date = LocalDate.of(2014, 12, 25);
		LocalDateTime dateTime = LocalDateTime.of(2014, 12, 25, 10, 30);
		
		// Comparando datas
		LocalDate amanha = LocalDate.now().plusDays(1);
		System.out.println("Hoje < Amanha: " + hoje.isBefore(amanha));
		System.out.println("Hoje > Amanha: " + hoje.isAfter(amanha));
		System.out.println("Hoje = Amanha: " + hoje.isEqual(amanha));
		
		// Comparando com TimeZone
		ZonedDateTime tokyo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
		ZonedDateTime saoPaulo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
		System.out.println("Tokyo = SP: " + tokyo.isEqual(saoPaulo));
		
		// Recuperando dia do mes atual
		System.out.println("hoje é dia: "+ MonthDay.now().getDayOfMonth());
		
		// Recuperando mes e ano a partir de uma data
		YearMonth ym = YearMonth.from(date);
		System.out.println(ym.getMonth() + " " + ym.getYear());
		
		// ENUM ao inves constante
		System.out.println(LocalDate.of(2014, 12, 25));
		System.out.println(LocalDate.of(2014, Month.DECEMBER, 25));
		
		// Exibindo primeiro trimestre
		System.out.println(Month.DECEMBER.firstMonthOfQuarter());
		System.out.println(Month.DECEMBER.plus(2));
		System.out.println(Month.DECEMBER.minus(1));
		
		// Formatando a saida 
		Locale pt = new Locale("pt");
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, pt));
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, pt));
		
		// Exibindo dia semana
		System.out.println(hoje.getDayOfWeek());
		System.out.println(hoje.getDayOfWeek().getDisplayName(TextStyle.FULL, pt));
		
		// Formatando data
		System.out.println(agora.format(DateTimeFormatter.ISO_LOCAL_TIME));
		System.out.println(agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		// Fazendo parse data String
		LocalDateTime agora3 = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String resultado = agora3.format(formatador);
		LocalDate agoraEmData = LocalDate.parse(resultado, formatador);
		
		// Datas invalidas c/ Calendar
		Calendar instante = Calendar.getInstance();
		instante.set(2014, Calendar.FEBRUARY, 30); // ajustou data sem avisar
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		System.out.println(dateFormat.format(instante.getTime()));
		
		// Datas invalidas c/ LocalDate
		try {
			LocalDate.of(2014, Month.FEBRUARY, 30);
		} catch (DateTimeException e) { System.err.println(e.getMessage()); }
		try {
			LocalDateTime horaInvalida = LocalDate.now().atTime(25, 0);
		} catch (DateTimeException e) { System.err.println(e.getMessage()); }
		
		// Diff datas c/ Calendar
		Calendar agora4 = Calendar.getInstance();
		Calendar outraData = Calendar.getInstance();
		outraData.set(1988, Calendar.JANUARY, 25);
		long diferenca = agora4.getTimeInMillis() - outraData.getTimeInMillis();
		long milissegundosDeUmDia = 1000 * 60 * 60 * 24;
		long dias = diferenca / milissegundosDeUmDia;
		
		// Diff datas c/ ChronoUnit
		LocalDate agora5 = LocalDate.now();
		LocalDate outraData2 = LocalDate.of(1989, Month.JANUARY, 25);
		// Exibe resultado
		long dias2 = ChronoUnit.DAYS.between(outraData2, agora5);
		long meses = ChronoUnit.MONTHS.between(outraData2, agora5);
		long anos = ChronoUnit.YEARS.between(outraData2, agora5);
		System.out.printf("%s dias, %s meses e %s anos", dias2, meses, anos);
		
		// Usando Period
		LocalDate agora6 = LocalDate.now();
		LocalDate outraData3 = LocalDate.of(1989, Month.JANUARY, 25);
		Period periodo = Period.between(outraData3, agora6);
		System.out.printf("\n%s dias, %s meses e %s anos", periodo.getDays(), periodo.getMonths(), periodo.getYears());
		
		// Usando Duration
		LocalDateTime agora7 = LocalDateTime.now();
		LocalDateTime daquiAUmaHora = LocalDateTime.now().plusHours(1);
		Duration duration = Duration.between(agora7, daquiAUmaHora);
		if (duration.isNegative()) {
			duration = duration.negated();
		}
		System.out.printf("\n%s horas, %s minutos e %s segundos", duration.toHours(), duration.toMinutes(), duration.getSeconds());
	}
	
}
