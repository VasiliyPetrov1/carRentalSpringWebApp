package org.kosiuk.webApp.carSearchStrategy;

import org.kosiuk.webApp.entity.Car;
import org.kosiuk.webApp.repositories.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class DefaultStrategy implements CarSearchStrategy {

    @Override
    public Page<Car> execute(String qualClass, String brandName, Pageable pageable, CarRepository carRepository) {
        return carRepository.findByInUsage(IS_IN_USAGE, pageable);
    }
}
