package org.kosiuk.webApp.service;

import org.kosiuk.webApp.entity.Brand;
import org.kosiuk.webApp.repositories.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand getBrandByName(String brandName) {
        return brandRepository.findByName(brandName);
    }

    public Iterable<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Iterable<String> getAllBrandNames() {
        List<String> brandNames = new ArrayList<>();

        for (Brand brand : getAllBrands()) {
            brandNames.add(brand.getName());
        }

        return brandNames;
    }
}
