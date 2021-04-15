package org.kosiuk.webApp.carSearchStrategy;

import org.kosiuk.webApp.entity.Brand;
import org.kosiuk.webApp.entity.Car;
import org.kosiuk.webApp.repositories.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class SerchByBrandStrategy implements CarSearchStrategy {

    @Override
    public Page<Car> execute(String qualClass, String brandName, Pageable pageable, CarRepository carRepository) {

        return carRepository.findByInUsageAndBrand_Name(IS_IN_USAGE, brandName, pageable);

    }
}
