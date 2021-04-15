package org.kosiuk.webApp.controllers;

import org.kosiuk.webApp.dto.CarCreationDto;
import org.kosiuk.webApp.dto.CarDto;
import org.kosiuk.webApp.entity.Car;
import org.kosiuk.webApp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/app/car")
@PreAuthorize("hasAuthority('ADMIN')")
public class CarController {

    @Value("${application.itemsOnPageNumber}")
    public int pageSize;

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/new")
    public String getCarCreationForm(@ModelAttribute("carCreateDto") CarCreationDto carCreateDto) {
        return "newCar";
    }

    @PostMapping("/new")
    public String createCar(@ModelAttribute("carCreateDto") CarCreationDto carCreateDto,
                            @RequestParam("image") MultipartFile imageFile, Model model) {
        try {
            carService.createCar(carCreateDto, imageFile);
            model.addAttribute("carCreateMessage", "You've successfully created a new Car");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("carCreateMessage", "The car with this name already exists");
            return "newCar";
        } catch (IOException ioe) {
            model.addAttribute("carCreateMessage", ioe.getMessage());
            return "newCar";
        }
        return "redirect:/app/car";
    }

    @GetMapping
    public String showAllCars(Model model) {
        return showAllCarsPage(1, model);
    }

    @GetMapping("/page/{pageNumber}")
    public String showAllCarsPage(@PathVariable("pageNumber") Integer pageNumber, Model model) {

        Page<Car> carPage = carService.getAllCarsPage(pageNumber, pageSize / 2);
        List<Car> carList = carPage.getContent();

        model.addAttribute("curPage", pageNumber);
        model.addAttribute("totalPages", carPage.getTotalPages());
        model.addAttribute("totalItems", carPage.getTotalElements());
        model.addAttribute("cars", carList);

        return "showAllCars";
    }

    @GetMapping("{carId}")
    public String getCarEditForm(@PathVariable("carId") Integer carId, Model model) {
        model.addAttribute("carDto", carService.convertCarToDTO(carService.getCarById(carId)));
        return "editCar";
    }

    @PostMapping("/{carId}/edit")
    public String updateCar(@ModelAttribute("carDto") CarDto carDto, @RequestParam("image") MultipartFile imageFile,
                            @PathVariable("carId") Integer carId, Model model) {
        try {
            carService.updateCar(carDto, imageFile);
            model.addAttribute("updateCarMessage", "You've successfully updated the car!");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("updateCarMessage", "Car with this name already exists.");
            return "editCar";
        } catch (IOException ioe) {
            model.addAttribute("updateCarMessage", ioe.getMessage());
            return "editCar";
        }

        return "redirect:/app/car";
    }

    @GetMapping("/{carId}/delete")
    public String deleteCar(@PathVariable Integer carId) {

        carService.deleteCar(carId);

        return "redirect:/app/car";
    }

}
