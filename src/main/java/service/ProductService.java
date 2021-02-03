package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.Product;
import util.ProductDAO;

@Service(value = "pService")
public class ProductService {
	@Autowired
	private ProductDAO dao;

	public Product getArticleById(int articleId) {
		Product obj = dao.getArticleById(articleId);
		return obj;
	}

	public List<Product> getAllArticles() {
		return dao.getAllArticles();
	}

	public boolean addArticle(Product article) {
		if (dao.articleExists(article.getTitle(), article.getCategory())) {
			return false;
		} else {
			dao.addArticle(article);
			return true;
		}
		// dao.addArticle(article);
		// return true;
	}

	public void updateArticle(Product article) {
		dao.updateArticle(article);
	}

	public void deleteArticle(int articleId) {
		dao.deleteArticle(articleId);
	}
}
