package org.example.productcatalogservice_feb2025.repos;

import org.example.productcatalogservice_feb2025.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

   Page<Product> findByNameEquals(String name, Pageable pageable);

   Product save(Product product);

   Optional<Product> findById(Long id);

   void deleteById(Long id);

   List<Product> findAll();

   //List<Product> findProductOrderByPriceDesc();

   List<Product> findProductByOrderByPrice();

//   List<Product> findProductByPriceBetweenAndCategory

   @Query("select p.name from Product p where p.id=?1")
   String findProductNameById(Long id);


   @Query("select c.name from Category  c join Product  p on p.category.id=c.id where p.id=:pid")
   String findCategoryNameFromProductId(Long pid);

}


//fn(String name,Integer i)  -> fn("anurag",1); Pos Assoc
// fn(id:1,name:"anurag") Named Assoc