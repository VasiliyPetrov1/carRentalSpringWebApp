package org.kosiuk.webApp.carSearchStrategy;

import org.kosiuk.webApp.entity.Brand;
import org.kosiuk.webApp.entity.Car;
import org.kosiuk.webApp.entity.QualityClass;
import org.kosiuk.webApp.repositories.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class SearchByQualClassAndBrandStrategy implements CarSearchStrategy{

    @Override
    public Page<Car> execute(String qualClass, String brandName, Pageable pageable, CarRepository carRepository) {
        return carRepository.findByInUsageAndQualityClassAndBrand_Name(IS_IN_USAGE, QualityClass.valueOf(qualClass),
                brandName, pageable);
    }
}
