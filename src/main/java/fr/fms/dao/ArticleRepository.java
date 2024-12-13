package fr.fms.dao;

import fr.fms.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    public List<Article> findByBrand(String brand);
    public List<Article> findByBrandContains(String brand);
    public List<Article> findByBrandAndPrice(String brand, double price);
    @Query("select A from Article A where A.brand like %:x% and A.price > :y")
    public List<Article> searchArticles(@Param("x") String kw, @Param("y")double price);
    public List<Article> findByCategoryId(Long categoryId);
    public List<Article> findAll();
    @Query("select a from Article a")
    public List<Article> findAllArticles();
    public List<Article> findByBrandContainsAndDescriptionContains(String Brand, String Description);
    public void deleteById(Long id);

    //1.5
    @Transactional
    @Modifying
    @Query("update Article a SET a.description =:description,a.brand =:brand,a.price =:price  where a.id = :id")
    public void updateById(@Param("id") Long id,@Param("description") String description,@Param("brand") String brand,@Param("price") double price);

    @Transactional
    @Modifying
    @Query("update Article a SET a.description =:description where a.id = :id")
    public void updateDescriptionById(@Param("id") Long id,@Param("description") String description);

    public Optional<Article> findById(Long id);
}