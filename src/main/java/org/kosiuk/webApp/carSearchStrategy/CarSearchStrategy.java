package org.kosiuk.webApp.carSearchStrategy;

import org.kosiuk.webApp.entity.Car;
import org.kosiuk.webApp.repositories.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarSearchStrategy {

    boolean IS_IN_USAGE = false;

    Page<Car> execute(String qualClass, String brandName, Pageable pageable, CarRepository carRepository);

}

