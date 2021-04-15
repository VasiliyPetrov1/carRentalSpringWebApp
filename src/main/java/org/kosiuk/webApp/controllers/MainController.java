package org.kosiuk.webApp.controllers;

import org.kosiuk.webApp.dto.CarSearchAndSortCritereaDto;
import org.kosiuk.webApp.entity.Car;
import org.kosiuk.webApp.service.BrandService;
import org.kosiuk.webApp.service.CarService;
import org.kosiuk.webApp.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller // This means that this class is a Controller
@RequestMapping(path = "/app") // This means URL's start with /demo (after Application path)
public class MainController {

    @Value("${application.itemsOnPageNumber}")
    public int pageSize;

    private final UserService userService;
    private final CarService carService;
    private final BrandService brandService;

    public MainController(UserService userService, CarService carService, BrandService brandService) {
        this.userService = userService;
        this.carService = carService;
        this.brandService = brandService;
    }

    @GetMapping
    public String homePage(@ModelAttribute("filterCritereaDto") CarSearchAndSortCritereaDto filterCritereaDto, Model model) {
        return showUserPage(1, filterCritereaDto, model);
    }

    @GetMapping("/page/{pageNumber}")
    public String showUserPage(@PathVariable("pageNumber") Integer pageNumber,
                               @ModelAttribute("filterCritereaDto") CarSearchAndSortCritereaDto filterCritereaDto, Model model) {
        int pageSize = 6;

        Page<Car> carPage;

        try {
            if (filterCritereaDto.getSearchQualClass() == null
                    || filterCritereaDto.getSearchBrandName() == null || filterCritereaDto.getSortingParameter() == null) {
                carPage = carService.getAllUnusedCarsPage(pageNumber, pageSize);
            } else {
                carPage = carService.getAllUnusedFilteredCarsPage(filterCritereaDto.getSearchQualClass(),
                        filterCritereaDto.getSearchBrandName(), filterCritereaDto.getSortingParameter(), pageNumber, pageSize);
            }
        } catch (Exception e) {
            carPage = carService.getAllUnusedCarsPage(pageNumber, pageSize);
        }

        List<Car> carList = carPage.getContent();

        model.addAttribute("curPage", pageNumber);
        model.addAttribute("totalPages", carPage.getTotalPages());
        model.addAttribute("totalItems", carPage.getTotalElements());
        model.addAttribute("cars", carList);

        model.addAttribute("brandNamesList", brandService.getAllBrandNames());

        model.addAttribute("filterCritereaDto", filterCritereaDto);

        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}