package br.com.casacodigo.exercise.payments;

import java.math.BigDecimal;
import java.nio.file.Path;

public class Product {

	private String name;
	private Path path;
	private BigDecimal price;
	
	public Product() {}
	public Product(String name, Path path, BigDecimal price) {
		super();
		this.name = name;
		this.path = path;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
