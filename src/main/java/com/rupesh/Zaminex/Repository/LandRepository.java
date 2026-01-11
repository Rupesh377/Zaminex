package com.rupesh.Zaminex.Repository;

import com.rupesh.Zaminex.Entity.Land;
import com.rupesh.Zaminex.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandRepository extends JpaRepository<Land , Long> , JpaSpecificationExecutor<Land> {

    @Query("SELECT L FROM Land L WHERE L.active = true")
    List<Land> findAllActive();

    //List<Land> findBySeller(User seller);

    List<Land> findBySeller_IdAndActiveTrue(Long sellerId);


}
