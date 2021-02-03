package util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository(value = "dao2")
public class ProductDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Product getArticleById(int articleId) {
		return entityManager.find(Product.class, articleId);
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllArticles() {
		String hql = "FROM Product as atcl ORDER BY atcl.articleId";
		return (List<Product>) entityManager.createQuery(hql).getResultList();
	}

	public void addArticle(Product article) {
		entityManager.merge(article);
	}

	public void updateArticle(Product article) {
		Product artcl = getArticleById(article.getArticleId());
		if (artcl == null) {
			throw new ServiceException("Invalid product id", "");
		}
		artcl.setTitle(article.getTitle());
		artcl.setCategory(article.getCategory());
		entityManager.flush();
	}

	public void deleteArticle(int articleId) {
		Product artcl = getArticleById(articleId);
		if (artcl == null) {
			throw new ServiceException("Invalid product id", "");
		}
		entityManager.remove(getArticleById(articleId));
	}

	public boolean articleExists(String title, String category) {
		String hql = "FROM Product as atcl WHERE atcl.title = :title and atcl.category = :category";
		int count = entityManager.createQuery(hql).setParameter("title", title).setParameter("category", category)
				.getResultList().size();
		return count > 0 ? true : false;
	}
}
