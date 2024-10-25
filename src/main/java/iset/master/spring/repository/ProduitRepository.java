package iset.master.spring.repository; 
import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import jakarta.transaction.Transactional;

import java.util.Date;

import org.springframework.data.repository.query.Param; 
import iset.master.spring.model.Produit; 

public interface ProduitRepository extends JpaRepository<Produit, Long>     { 
	
 @Query ("select p from Produit p where p.designation like %:x% ") 
 public List<Produit> findByDesignation(@Param ("x") String mc); // implementation de l'interface findByDesignation
 
 @Query ("select p from Produit p where p.designation =:x and p.prix >:prix ")
 public List<Produit> findByDesignationAndPrix(@Param ("x") String mc,@Param ("prix") double prix); // implementation de l'interface findByDesignationAndPrix
 
 @Query("update Produit p set p.designation =:designation where p.id = :id") 
 @Modifying 
 @Transactional 
 public int mettreAJourDesignation( @Param("designation")String designation, @Param("id") Long idProduit);

 List<Produit> findByPrixGreaterThan(double prixMin);

 
 
 @Query("update Produit p set p.dateAchat =:dateAchat") 
 @Modifying 
 @Transactional 
 public int mettreAJourDateAchat( @Param("dateAchat")Date dateAchat);

 
 @Query ("select p from Produit p order by p.prix asc")
 public List<Produit> ListeProduitOrderByPrixAsc(); // implementation de l'interface ListeProduitOrderByPrixAsc
 
 
 @Query ("select p from Produit p where p.dateAchat >:da ") 
 public List<Produit> findByDate(@Param ("da") Date dateAc); // implementation de l'interface findByDate
 
 
}