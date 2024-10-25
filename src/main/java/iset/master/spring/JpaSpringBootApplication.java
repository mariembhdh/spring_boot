package iset.master.spring; 
 
import java.util.List; 
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.context.ApplicationContext; 
import iset.master.spring.model.Produit; 
import iset.master.spring.repository.ProduitRepository; 

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication 
public class JpaSpringBootApplication { 
 
 
 public static void main(String[] args) { 
  
// ------------référencer le contexte 
  ApplicationContext contexte= SpringApplication.run(JpaSpringBootApplication.class, args); 
// -------Récupérer une implémentation de l'interface "ProduitRepository" par injection de dépendance 
  ProduitRepository produitRepos =contexte.getBean(ProduitRepository.class); 
// ----------Insérer 3 produits 
  
 produitRepos.save(new Produit("Yaourt", 0.400, 20)); 
  produitRepos.save(new Produit("Farine", 1.200, 30)); 
 produitRepos.save(new Produit("Chocolat", 2000.0, 5)); 
// -------------Lister l'ensemble des produits 
 List<Produit> lp = produitRepos.findAll(); 
  System.out.println("******Liste des produits****"); 
  for (Produit p : lp) 
  { 
  System.out.print("Designation:"+ p.getDesignation()+"  , "); 
  System.out.println("Prix:"+ p.getPrix()); 
  } 
  System.out.println("-----------------------");
  
//----------Lister tous les produits dont la designation contient "h" 
System.out.println("******Liste des produits dont la désignation contient 'h'  ****"); 
List<Produit> lp2 = produitRepos.findByDesignation("h"); // appel methode  findByDesignation
for (Produit p : lp2)  
{ 
 System.out.print("Designation:"+ p.getDesignation()+"  , "); 
 System.out.println("Prix:"+ p.getPrix());  
} 
System.out.println("-----------------------"); 
  
  
  
  
  
  
//----------Lister tous les produits dont la designation contient "h" et prix > p
System.out.println("******Liste des produits dont la désignation = 'Yaourt' and prix > 0.2  ****"); 
List<Produit> lp3 = produitRepos.findByDesignationAndPrix("Yaourt",0.2);  // appel methode  findByDesignationAndPrix
for (Produit p : lp3)  
{ 
 System.out.print("Designation:"+ p.getDesignation()+"  , "); 
 System.out.println("Prix:"+ p.getPrix());  
}
System.out.println("-----------------------");

produitRepos.mettreAJourDesignation("jus", 1L);
System.out.println("Produit MAJ");
  
//Mette à jour la désignation (Farine → Pain) 
 produitRepos.mettreAJourDesignation("Pain", 2L); 
  //Afficher le produit modifié s'il est présent 
  Produit pm= produitRepos.findById(2L).get(); 
  if (pm!=null) 
  { 
       System.out.println("Désignation:"+pm.getDesignation()); 
  } 
  else 
  { 
   System.out.println("Produit non existant..."); 
  }
  
//Lister tous les produits ayant un prix >  1.5 en utilisant une méthode requête " 
System.out.println("******Liste des produits ayant un prix >  1.5 en utilisant une méthode requête  ****"); 
List<Produit> lp4 = produitRepos.findByPrixGreaterThan(1.5); 
for (Produit p : lp4) { 
System.out.print("Designation:" + p.getDesignation() + "  , "); 
System.out.println("Prix:" + p.getPrix()); 
} 
System.out.println("-----------------------");
  
// Convertir la chaîne de caractères en objet Date
 String dateString = "2024-01-01";
  
  // Créer un formatteur pour le format "dd/MM/yyyy"
  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  try {
      Date dateAchat = formatter.parse(dateString); 
      produitRepos.mettreAJourDateAchat(dateAchat);
      System.out.println("Produit MAJ");
      
  } catch (Exception e) {
      e.printStackTrace();  // Gérer l'exception de parsing
  }

  

System.out.println("******Liste des produits en ordre croissant selon le prix   ****"); 
List<Produit> lp5 = produitRepos.ListeProduitOrderByPrixAsc(); 
for (Produit p : lp5) { 
System.out.print("Designation:" + p.getDesignation() + "  , "); 
System.out.println("Prix:" + p.getPrix()); 
} 
System.out.println("-----------------------");
  
  
  
  //----------la liste des produits dont la date d’achat est supérieure à « 2020-03-15 » 
  System.out.println("******la liste des produits dont la date d’achat est supérieure à « 2020-03-15 »  ****");

  String dateString1 = "2020-03-15";
  SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
  try {
      Date dateAc = formatter.parse(dateString1); 
      List<Produit> lp6 = produitRepos.findByDate(dateAc); // appel methode  findByDesignation
for (Produit p : lp6)  
{ 
 System.out.print("Designation:"+ p.getDesignation()+"  , "); 
 System.out.print("Prix:"+ p.getPrix()+"  , ");  
 System.out.println("DAte:"+ p.getDateAchat());
} 
System.out.println("-----------------------");       
  } catch (Exception e) {
      e.printStackTrace();  // Gérer l'exception de parsing
  }
  

 


} 
 } 
 
 
 