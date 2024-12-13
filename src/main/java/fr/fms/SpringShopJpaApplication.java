package fr.fms;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Scanner;

@SpringBootApplication
public class SpringShopJpaApplication implements CommandLineRunner {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringShopJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Category smartphone = categoryRepository.save(new Category("Smartphone"));
//		Category tablet = categoryRepository.save(new Category("Tablet"));
//		Category pc = categoryRepository.save(new Category("PC"));
//		//articleRepository.save(new Article("S9","Samsung",250));
//		articleRepository.save(new Article("S10","Samsung",250,smartphone));
//		articleRepository.save(new Article("S9","Samsung",350,smartphone));
//		articleRepository.save(new Article("MI10","Xiaomi",100,smartphone));
//
//		articleRepository.save(new Article("GalaxyTab","Samsung",450,tablet));
//		articleRepository.save(new Article("Ipad","Apple",350,tablet));
//
//		articleRepository.save(new Article("R510","Asus",600,pc));



//		for (Article article : articleRepository.findByCategoryId(2L)){
//			System.out.println(article);
//		}
//		for (Article article : articleRepository.findByBrand("Samsung")){
//			System.out.println(article);
//		}
//		for (Article article : articleRepository.findByBrandAndPrice("Samsung",250)){
//			System.out.println(article);
//		}
//		for (Article article : articleRepository.searchArticles("sung",200)){
//			System.out.println(article);
//		}
//		for (Article article: articleRepository.findAll()){
//			System.out.println(article);
//		}
//		for (Article article: articleRepository.findAllArticles()){
//			System.out.println(article);
//		}
//		for (Article article: articleRepository.findByBrandContainsAndDescriptionContains("samsung","s")){
//			System.out.println(article);
//		}
		//articleRepository.deleteById((long) 3L);
		//1.5
		//articleRepository.updateById(2L,"une nouvelle description","Samsung",150);
		//1.6
//		for (Category category: categoryRepository.findCategoryByAsc()){
//			System.out.println(category);
//		}
//		for (Category category: categoryRepository.findCategoryByDesc()){
//			System.out.println(category);
//		}
//		for (Article article: articleRepository.findArticlesByPrice(200L)){
//			System.out.println(article);
//		}
		//1.7
		//articleRepository.updateDescriptionById(2L,"S8");
		Menu();
	}
	private static Scanner scan = new Scanner(System.in);
	public void MainMenu(){
		System.out.println("Bienvenu dans notre application de gestion d'articles ! vivement la couche web parce que...");
		System.out.println("1: Afficher tous les articles sans pagination");
		System.out.println("2: Afficher tous les articles avec pagination");
		System.out.println("****************");
		System.out.println("3: Ajouter un article");
		System.out.println("4: Afficher un article");
		System.out.println("5: Supprimer un article");
		System.out.println("6: Modifier un article");
		System.out.println("*************");
		System.out.println("7: Ajouter une categorie");
		System.out.println("8: Afficher une categorie");
		System.out.println("9: Supprimer une categorie");
		System.out.println("10: Mettre a jour une categorie");
		System.out.println("11: Afficher tous les articles d'une categorie");
		System.out.println("*************");
		System.out.println("12: Sortir du programme");
	}
	public void Menu(){
		MainMenu();
		int choice = 0;
		while(choice != 12) {
			while(!scan.hasNextInt())	scan.next();
			choice = scan.nextInt();
			switch(choice) {
				case 1 : displayArticles();
					break;
				case 2 : displayArticlesPagination();
					break;
				case 3 : addArticle();
				break;
				case 4: showArticle();
				break;
				case 5: deleteArticle();
				break;
				case 6: updateArticle();
				break;
				case 7: addCategory();
				break;
				case 8: showCategory();
				break;
				case 9: deleteCategory();
				break;
				case 10: updateCategory();
				break;
				case 12 : System.out.println("Au revoir");
					break;
				default: System.err.println("Veuillez réessayer");
			}
		}
	}

	private void updateCategory() {
		System.out.println("entrez l'id d'une catégorie pour modifier");
		while (!scan.hasNextLong()) scan.next();
		Long catId = scan.nextLong();
		if (categoryRepository.findById(catId).isPresent()){
			System.out.println("entrez le nouveau nom de la catégorie");
			while (!scan.hasNext()) scan.next();
			String name = scan.next();

			categoryRepository.updateById(catId,name);
			System.out.println("categorie modifier avec succès");
		}
		else {
			System.err.println("article non trouver");
		}
		MainMenu();
	}

	public void showCategory() {
		System.out.println("entrez un id d'un catégorie");
		while (!scan.hasNextLong()) scan.next();
		Long catId = scan.nextLong();
		System.out.println(categoryRepository.findById(catId));
	}

	public void deleteCategory() {
		System.out.println("entrez l'id d'une catégorie pour supprimer");
		while (!scan.hasNextLong()) scan.next();
		Long catId = scan.nextLong();
		if (categoryRepository.findById(catId).isPresent()){
			categoryRepository.deleteById(catId);
			System.out.println("catégorie avec l'id " + catId + " supprimer avec succès");
		}
		else {
			System.err.println("catégorie non trouver");
		}
		MainMenu();
	}

	public void addCategory() {
		System.out.println("entrez un nom pour la categorie");
		while (!scan.hasNext()) scan.next();
		String name = scan.next();

		categoryRepository.save(new Category(name));
		MainMenu();

	}

	public void updateArticle() {

		System.out.println("entrez l'id d'un article pour modifier");
		while (!scan.hasNextLong()) scan.next();
		Long articleId = scan.nextLong();

		if (articleRepository.findById(articleId).isPresent()){
			System.out.println("entrez une description pour un article");
			while (!scan.hasNext()) scan.next();
			String description = scan.next();

			System.out.println("entrez une marque pour un article");
			while (!scan.hasNext()) scan.next();
			String brand = scan.next();

			System.out.println("entrez un prix pour un article");
			while (!scan.hasNextDouble()) scan.next();
			double price = scan.nextDouble();

			articleRepository.updateById(articleId,description,brand,price);
			System.out.println("article modifier avec succès");
		}
		else {
			System.err.println("article non trouver");
		}
		MainMenu();
	}

	public void deleteArticle() {
		System.out.println("entrez l'id d'un article pour supprimer");
		while (!scan.hasNextLong()) scan.next();
		Long articleId = scan.nextLong();
		if (articleRepository.findById(articleId).isPresent()){
			articleRepository.deleteById(articleId);
			System.out.println("article avec l'id " + articleId + " supprimer avec succès");
		}
		else {
			System.err.println("article non trouver");
		}
		MainMenu();
	}

	public void showArticle() {
		System.out.println("entrez un id d'un article");
		while (!scan.hasNextLong()) scan.next();
		Long articleId = scan.nextLong();
		System.out.println(articleRepository.findById(articleId));
	}

	public void addArticle() {
		System.out.println("entrez une description pour un article");
		while (!scan.hasNext()) scan.next();
		String description = scan.next();
		scan.next();
		System.out.println("entrez une marque pour un article");
		while (!scan.hasNext()) scan.next();
		String brand = scan.next();

		System.out.println("entrez un prix pour un article");
		while (!scan.hasNextDouble()) scan.next();
		double price = scan.nextDouble();

		for (Category category: categoryRepository.findAll()){
			System.out.println(category);
		}
		System.out.println("entrez un id d'une categorie");
		while (!scan.hasNextLong()) scan.next();
		Long catId = scan.nextLong();
		articleRepository.save(new Article(description,brand,price,categoryRepository.findCatById(catId)));
		MainMenu();
	}

	public void displayArticlesPagination() {
		String choice = "next";
		int pageNumber = 0;
		int pageSize = 2;
		while(true) {
			if (choice.equalsIgnoreCase("exit")){
				MainMenu();
				break;
			}
			else if (choice.equalsIgnoreCase("prev")&& pageNumber > 1){
				pageNumber--;
			}
			else if (choice.equalsIgnoreCase("next")){
				pageNumber++;
			}
			else {
				System.err.println("Veuillez réessayer");
			}

			Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
			Page<Article> page = articleRepository.findAll(pageable);

			if (page.hasContent()) {
				System.out.println("Affichage des articles pour la page " + pageNumber + ":");
				for (Article article : page.getContent()) {
					System.out.println(article);
				}
				System.out.println("Page " + pageNumber + " of " + page.getTotalPages());
			} else {
				System.out.println("Aucun article trouvé pour la page " + pageNumber + ".");
			}
			System.out.println("Exit pour sortir de la pagination");
			if (!(pageNumber <=1)){
				System.out.println("PREV pour afficher la page precedente");
			}
			System.out.println("Next pour afficher la page suivante");
			while (!scan.hasNext()) scan.next();
			choice = scan.next();
		}
	}

	public void displayArticles() {
		for (Article article: articleRepository.findAll()){
			System.out.println(article);
		}
	}
}