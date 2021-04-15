package org.kosiuk.webApp.service;

import org.kosiuk.webApp.carSearchStrategy.*;
import org.kosiuk.webApp.dto.CarCreationDto;
import org.kosiuk.webApp.dto.CarDto;
import org.kosiuk.webApp.entity.Brand;
import org.kosiuk.webApp.entity.Car;
import org.kosiuk.webApp.entity.QualityClass;
import org.kosiuk.webApp.repositories.BrandRepository;
import org.kosiuk.webApp.repositories.CarRepository;
import org.kosiuk.webApp.utils.FileUploadUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final BrandRepository brandRepository;

    public CarService(CarRepository carRepository, BrandRepository brandRepository) {
        this.carRepository = carRepository;
        this.brandRepository = brandRepository;
    }

    public Car createCar(Car car){
        return carRepository.save(car);
    }

    public Car createCar(CarCreationDto carCreationDto, MultipartFile imageFile) throws IOException {

        QualityClass qualityClass = QualityClass.PREMIUM;

        if (carCreationDto.isEconom()) {
            qualityClass = QualityClass.ECONOM;
        } else if (carCreationDto.isPremium()) {
            qualityClass = QualityClass.PREMIUM;
        } else if (carCreationDto.isLuxury()) {
            qualityClass = QualityClass.LUXURY;
        }

        Car car = new Car(carCreationDto.getName(), qualityClass, carCreationDto.getRentalPrice(),
                carCreationDto.getRepairPrice(), carCreationDto.getMileage(), false);

        Brand existedBrand = brandRepository.findByName(carCreationDto.getBrandName());

        if (existedBrand != null) {
            car.setBrand(existedBrand);
        } else {
            Brand brand = new Brand(carCreationDto.getBrandName());
            car.setBrand(brand); // setting brand
        }

        String imageFileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        car.setImageFileName(imageFileName);
        car = carRepository.save(car);

        String uploadDir = "car-images/" + car.getId();
        FileUploadUtil.saveFile(uploadDir, imageFileName, imageFile);

        return car;
    }

    public Page<Car> getAllCarsPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return carRepository.findAll(pageable);
    }

    public Page<Car> getAllUnusedCarsPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return carRepository.findByInUsage(false, pageable);
    }

    public Page<Car> getAllUnusedFilteredCarsPage(String qualClass, String brandName, String sortParameter,
                                                  int pageNo, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.ASC, sortParameter);

        CarSearchContext carSearchContext = new CarSearchContext(carRepository);

        if (!qualClass.equals("NONE") && !brandName.equals("NONE")) {
            carSearchContext.setStrategy(new SearchByQualClassAndBrandStrategy());
        } else if (!qualClass.equals("NONE")) {
            carSearchContext.setStrategy(new SearchByQualClassStrategy());
        } else if (!brandName.equals("NONE")) {
            carSearchContext.setStrategy(new SerchByBrandStrategy());
        } else {
            carSearchContext.setStrategy(new DefaultStrategy());
        }

        return carSearchContext.executeStrategy(qualClass, brandName, pageRequest, carRepository);

    }

    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Integer id) {
        return carRepository.findById(id).get(); // findById returns Optional container here so we need to use get method to retrieve the value
    }


    public CarDto convertCarToDTO(Car car) {
        CarDto carDto = new CarDto(car.getId(), car.getName(), car.getBrand().getName(), car.getRentalPrice(), car.getRepairPrice(),
                car.getMileage(), car.getInUsage());

        switch (car.getQualityClass()) {
            case ECONOM:
                carDto.setEconom(true);
                break;
            case PREMIUM:
                carDto.setPremium(true);
                break;
            case LUXURY:
                carDto.setLuxury(true);
                break;
            default:
        }

        // adding image to DTO
        carDto.setImageFileName(car.getImageFileName());

        return carDto;
    }

    public void updateCar(CarDto carDto, MultipartFile imageFile) throws IOException {

        QualityClass qualityClass = QualityClass.PREMIUM;

        if (carDto.isEconom()) {
            qualityClass = QualityClass.ECONOM;
        } else if (carDto.isPremium()) {
            qualityClass = QualityClass.PREMIUM;
        } else if (carDto.isLuxury()) {
            qualityClass = QualityClass.LUXURY;
        }

        Car car = new Car(carDto.getId(), carDto.getName(), qualityClass, carDto.getRentalPrice(),
                carDto.getRepairPrice(), carDto.getMileage(), carDto.isInUsage());

        Brand existedBrand = brandRepository.findByName(carDto.getBrandName());

        if (existedBrand != null) {
            car.setBrand(existedBrand);
        } else {
            Brand brand = new Brand(carDto.getBrandName());
            car.setBrand(brand); // setting brand
        }

        String imageFileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        car.setImageFileName(imageFileName);
        car = carRepository.save(car);

        String uploadDir = "car-images/" + car.getId();
        FileUploadUtil.saveFile(uploadDir, imageFileName, imageFile);
    }

    public void deleteCar(Integer id) {

        Integer brandId = carRepository.findById(id).get().getBrand().getId();

        if (carRepository.getCarsByBrandId(brandId).size() > 1) {
            carRepository.deleteCarNonCascade(id);
        } else {
            carRepository.deleteById(id);
        }
    }

}
