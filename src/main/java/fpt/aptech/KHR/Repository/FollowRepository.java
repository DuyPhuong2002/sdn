/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Repository;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Follow;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    // Accoutn SND
    @Query("SELECT f FROM Follow f WHERE f.idAccountFl = :idAccountFl")
    List<Follow> findAllFolow(@PathVariable("idAccountFl") Account idAccountFl);
    
    @Query("SELECT f FROM Follow f WHERE f.idAccountFled = :idAccountFled")
    List<Follow> findAllFolowed(@PathVariable("idAccountFled") Account idAccountFled);
    
    @Query("SELECT f FROM Follow f WHERE f.idAccountFl = :idAccountFl")
    List<Follow> findAllFollow(@PathVariable("idAccountFl") Account idAccountFl);
    
    @Query("SELECT f FROM Follow f WHERE f.idAccountFl = :idAccountFl AND f.idAccountFled = :idAccountFled")
    Follow CheckExitFollow(@PathVariable("idAccountFl") Account idAccountFl,@PathVariable("idAccountFled") Account idAccountFled);
    
    @Query("SELECT f FROM Follow f WHERE f.idAccountFl = :idAccountFl AND f.idAccountFled = :idAccountFled")
    Follow FFollow(@PathVariable("idAccountFl") Account idAccountFl,@PathVariable("idAccountFled") Account idAccountFled);
    
}
