package fpt.aptech.KHR.Repository;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Reels;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface ReelsRepository extends JpaRepository<Reels, Integer> {
    @Query("SELECT p FROM Reels p WHERE p.id = :id ORDER BY p.id DESC")
    Reels findById(@PathVariable("id") int id);
    //
    @Query("SELECT p FROM Reels p WHERE p.idAccount = :idAccount")
    Reels findByAuthur(@PathVariable("idAccount") Account idAccount);
    
//    @Query("SELECT  p FROM Reels p GROUP BY idAccount")
//    List<Reels> findByAuthur(@PathVariable("idAccount") Account idAccount);

    
}