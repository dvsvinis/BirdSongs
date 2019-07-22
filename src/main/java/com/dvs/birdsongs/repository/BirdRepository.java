package com.dvs.birdsongs.repository;
import com.dvs.birdsongs.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirdRepository extends JpaRepository <Bird, Long> {

}
