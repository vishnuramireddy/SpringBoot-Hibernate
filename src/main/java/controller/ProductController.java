package controller;
/*
 * package controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.util.UriComponentsBuilder;
 * 
 * import service.ProductService; import util.Product;
 * 
 * @Controller public class ProductController {
 * 
 * @Autowired private ProductService articleService;
 * 
 * // Get all products....
 * 
 * @RequestMapping(value = "products", produces = "application/json") public
 * ResponseEntity<List<Product>> getAllArticles() { List<Product> list =
 * articleService.getAllArticles(); return new
 * ResponseEntity<List<Product>>(list, HttpStatus.OK); }
 * 
 * // Get product by id
 * 
 * @RequestMapping(value = "product/{id}", produces = "application/json", method
 * = RequestMethod.GET) public ResponseEntity<Product>
 * getArticleById(@PathVariable("id") Integer id) { Product article =
 * articleService.getArticleById(id); return new
 * ResponseEntity<Product>(article, HttpStatus.OK); }
 * 
 * @RequestMapping(value = "product/{id}", produces = "application/json", method
 * = RequestMethod.DELETE) public ResponseEntity<Void>
 * deleteArticle(@PathVariable("id") Integer id) {
 * articleService.deleteArticle(id); return new
 * ResponseEntity<Void>(HttpStatus.NO_CONTENT); }
 * 
 * @RequestMapping(value = "product", consumes = "application/json", produces =
 * "application/json", method = RequestMethod.POST) public ResponseEntity<Void>
 * addArticle(@RequestBody Product article, UriComponentsBuilder builder) {
 * boolean flag = articleService.addArticle(article); if (!flag) { return new
 * ResponseEntity<Void>(HttpStatus.CONFLICT); } HttpHeaders headers = new
 * HttpHeaders();
 * headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.
 * getArticleId()).toUri()); return new ResponseEntity<Void>(headers,
 * HttpStatus.CREATED); }
 * 
 * @RequestMapping(value = "product", produces = "application/json", method =
 * RequestMethod.PUT) public ResponseEntity<Product> updateArticle(@RequestBody
 * Product article) { articleService.updateArticle(article); return new
 * ResponseEntity<Product>(article, HttpStatus.OK); }
 * 
 * }
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import service.ProductService;
import util.Product;
import util.ProductResponseInfo;
import util.ServiceException;

@Controller
//@RestController
public class ProductController {
	@Autowired
	private ProductService articleService;

	// @GetMapping(value="product/{id}" , produces = "application/json")
	@RequestMapping(value = "product/{id}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<ProductResponseInfo> getArticleById(@PathVariable("id") Integer id) {
		Product article = articleService.getArticleById(id);
		if (article == null) {
			ProductResponseInfo response = new ProductResponseInfo("Invalid product Id.");
			return new ResponseEntity<ProductResponseInfo>(response, HttpStatus.OK);
		}
		ProductResponseInfo response = new ProductResponseInfo("Success", article);
		return new ResponseEntity<ProductResponseInfo>(response, HttpStatus.OK);
	}

	// @GetMapping(value="products" , produces = "application/json")
	@RequestMapping(value = "products", produces = "application/json")
	// public ResponseEntity<List<Product>> getAllArticles() {
	public ResponseEntity<ProductResponseInfo> getAllArticles() {
		List<Product> list = articleService.getAllArticles();
		ProductResponseInfo response = new ProductResponseInfo("Success", list);
		return new ResponseEntity<ProductResponseInfo>(response, HttpStatus.OK);
	}

	// @PostMapping(value= "product" , consumes = "application/json" ,produces =
	// "application/json")
	@RequestMapping(value = "product", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> addArticle(@RequestBody Product article, UriComponentsBuilder builder) {
		boolean flag = articleService.addArticle(article);
		if (!flag) {
			return new ResponseEntity<String>("Duplicate Title", HttpStatus.BAD_REQUEST);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());
		return new ResponseEntity<String>("product created.", headers, HttpStatus.CREATED);
	}

	// @PutMapping(value = "product", produces = "application/json")
	@RequestMapping(value = "product", consumes = "application/json", produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<String> updateArticle(@RequestBody Product article) {
		try {
			articleService.updateArticle(article);
		} catch (ServiceException ex) {
			return new ResponseEntity<String>("Product update failed. Reason " + ex.getMsg(), HttpStatus.OK);
		}
		return new ResponseEntity<String>("Product update sucess.", HttpStatus.OK);
	}

	// @DeleteMapping(value= "product/{id}" , produces = "application/json")
	@RequestMapping(value = "product/{id}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteArticle(@PathVariable("id") Integer id) {
		try {
			articleService.deleteArticle(id);
		} catch (ServiceException ex) {
			return new ResponseEntity<String>("Product DELETE failed. Reason " + ex.getMsg(), HttpStatus.OK);
		}
		return new ResponseEntity<String>("Product DELETE sucess.", HttpStatus.OK);
	}
}
