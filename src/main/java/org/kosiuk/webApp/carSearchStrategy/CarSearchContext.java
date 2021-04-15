package org.kosiuk.webApp.carSearchStrategy;

import org.kosiuk.webApp.entity.Car;
import org.kosiuk.webApp.entity.QualityClass;
import org.kosiuk.webApp.repositories.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CarSearchContext {

    private CarSearchStrategy strategy;
    private CarRepository carRepository;

    public CarSearchContext(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void setStrategy(CarSearchStrategy strategy) {
        this.strategy = strategy;
    }

    public Page<Car> executeStrategy(String qualClass, String brandName, Pageable pageable, CarRepository carRepository){
        return strategy.execute(qualClass, brandName, pageable, carRepository);
    }
}
