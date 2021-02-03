package util;

import java.util.ArrayList;
import java.util.List;

public class ProductResponseInfo {

	private String status;
	private List<Product> products = new ArrayList<>();

	public ProductResponseInfo(String status, List<Product> products) {
		super();
		this.status = status;
		this.products.addAll(products);
	}

	public ProductResponseInfo(String status) {
		super();
		this.status = status;
	}

	public ProductResponseInfo(String status, Product product) {
		super();
		this.status = status;
		this.products.add(product);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
