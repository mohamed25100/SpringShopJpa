package fr.fms.dao;

import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    //1.6
    @Query("select a from Category a order by a.name")
    public List<Category> findCategoryByAsc();
    @Query("select a from Category a order by a.name desc")
    public List<Category> findCategoryByDesc();
    @Query("select a from Category a")
    public List<Category> findAllCat();
    @Query("select a from Category a where a.id=:id")
    public Category findCatById(@Param("id")Long id);

    @Transactional
    @Modifying
    @Query("update Category a SET a.name =:name where a.id = :id")
    public void updateById(@Param("id") Long id,@Param("name") String name);
}