package org.kosiuk.webApp.repositories;

import org.kosiuk.webApp.entity.Brand;
import org.kosiuk.webApp.entity.Car;
import org.kosiuk.webApp.entity.QualityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {

    Page<Car> findAll(Pageable pageable);

    Page<Car> findByInUsage(Boolean inUsage, Pageable pageable);

    Page<Car> findByInUsageAndQualityClass(Boolean inUsage, QualityClass qualityClass, Pageable pageable);

    Page<Car> findByInUsageAndBrand_Name(Boolean inUsage, String brandName, Pageable pageable);

    Page<Car> findByBrand_Name(String brandName, Pageable pageable);

    Page<Car> findByInUsageAndQualityClassAndBrand_Name(Boolean inUsage, QualityClass qualityClass, String brandName, Pageable pageable);

    Iterable<Car> findByInUsage(Boolean inUsage);

    @Transactional
    @Query(value = "SELECT id FROM car where brand_id = :brand_id", nativeQuery = true)
    @Modifying
    List<Integer> getCarsByBrandId(@Param("brand_id") Integer brand_id);

    @Transactional
    @Query(value = "DELETE FROM car where id = :id", nativeQuery = true)
    @Modifying
    void deleteCarNonCascade(@Param("id") Integer id);
}

