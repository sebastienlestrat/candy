package com.candy.repository;

import com.candy.model.CandyBox;
import org.springframework.data.repository.CrudRepository;

public interface CandyBoxRepository extends CrudRepository<CandyBox,Long > {
}