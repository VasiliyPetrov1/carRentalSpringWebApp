package org.kosiuk.webApp.carSearchStrategy;

import org.kosiuk.webApp.entity.Car;
import org.kosiuk.webApp.entity.QualityClass;
import org.kosiuk.webApp.repositories.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class SearchByQualClassStrategy implements CarSearchStrategy{

    @Override
    public Page<Car> execute(String qualClass, String brandName, Pageable pageable, CarRepository carRepository) {
        return carRepository.findByInUsageAndQualityClass(IS_IN_USAGE, QualityClass.valueOf(qualClass), pageable);
    }
}
