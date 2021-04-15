package org.kosiuk.webApp.repositories;

import org.kosiuk.webApp.entity.Brand;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Integer> {

    Brand findByName(String name);

}
