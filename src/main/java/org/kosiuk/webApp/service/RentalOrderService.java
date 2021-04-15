package org.kosiuk.webApp.service;

import org.kosiuk.webApp.dto.FormedOrderDto;
import org.kosiuk.webApp.dto.OrderDetailsDto;
import org.kosiuk.webApp.entity.*;
import org.kosiuk.webApp.repositories.RentalOrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalOrderService {

    @Value("${application.driverDailySalary}")
    public Double driverDailySalary;

    private final CarService carService;
    private final UserService userService;
    private final BrandService brandService;
    private final RentalOrderRepository rentalOrderRepository;


    public RentalOrderService(CarService carService, UserService userService,
                              BrandService brandService, RentalOrderRepository rentalOrderRepository) {
        this.carService = carService;
        this.userService = userService;
        this.brandService = brandService;
        this.rentalOrderRepository = rentalOrderRepository;
    }

    public OrderDetailsDto createOrderDetailsDTO(Integer carId, User client) {
        Car car = carService.getCarById(carId);
        OrderDetailsDto orderDetailsDto;
        PassportData paspData = client.getPassportData();

        if (paspData == null) {
            orderDetailsDto = new OrderDetailsDto(car.getId(), car.getName(), car.getBrand().getName(), car.getRentalPrice(),
                    car.getRepairPrice(), car.getMileage(), car.getQualityClass().name(), client.getId(), driverDailySalary);
        } else {
            orderDetailsDto = new OrderDetailsDto(car.getId(), car.getName(), car.getBrand().getName(), car.getRentalPrice(),
                    car.getRepairPrice(), car.getMileage(), car.getQualityClass().name(), client.getId(), paspData.getName(),
                    paspData.getSurname(), paspData.getPatronicalName(), paspData.getPassportNumber(), paspData.getRNTRC(),
                    driverDailySalary);
        }

        RentalOrder curClientOrder = rentalOrderRepository.getClientsOrderByCarId(client.getId(), car.getId());

        if (curClientOrder != null) {
            orderDetailsDto.setDriverFlag(curClientOrder.getDriverFlag());
            orderDetailsDto.setTerm(curClientOrder.getTerm());
        }

        orderDetailsDto.setImageFileName(car.getImageFileName());
        return orderDetailsDto;
    }

    public FormedOrderDto convertFormedOrderToDto(RentalOrder rentalOrder) {

        Car car = rentalOrder.getCar();

        User client = rentalOrder.getUser();

        FormedOrderDto formedOrderDto = new FormedOrderDto(client.getUsername(), car.getId(), car.getName(),
                car.getRepairPrice(), rentalOrder.getId(), rentalOrder.getDriverFlag(), rentalOrder.getTerm(),
                rentalOrder.getStatus().name(), rentalOrder.getTotalPrice());

        if (rentalOrder.getOrderRejectReason() != null) {
            formedOrderDto.setRejectMessageText(rentalOrder.getOrderRejectReason().getExplanation());
        }

        formedOrderDto.setImageFileName(car.getImageFileName());

        return formedOrderDto;

    }

    public RentalOrder getOrderById(Integer id) {
        return rentalOrderRepository.findById(id).get();
    }

    public Iterable<FormedOrderDto> getAllUserOrders(Integer userId) {

        User user = userService.getUserById(userId);

        List<RentalOrder> orderList = user.getRentalOrderList();
        List<FormedOrderDto> orderDtoList = new ArrayList<>();

        for (RentalOrder order : orderList) {
            orderDtoList.add(convertFormedOrderToDto(order));
        }

        return orderDtoList;

    }

    public Iterable<RentalOrder> getAllOrders() {
        return rentalOrderRepository.findAll();
    }

    public Iterable<FormedOrderDto> getAllOrderDtos() {

        List<RentalOrder> orderList = (List) getAllOrders();
        List<FormedOrderDto> orderDtoList = new ArrayList<>();

        for (RentalOrder order : orderList) {
            orderDtoList.add(convertFormedOrderToDto(order));
        }

        return orderDtoList;

    }


    public RentalOrder createOrder(OrderDetailsDto orderDetailsDto) {

        RentalOrder rentalOrder = new RentalOrder(orderDetailsDto.isDriverFlag(), orderDetailsDto.getTerm(), RentalOrderStatus.ON_CHECK);

        double totalPrice = orderDetailsDto.getRentalPrice() * orderDetailsDto.getTerm();

        if (orderDetailsDto.isDriverFlag()) {
            totalPrice += driverDailySalary * orderDetailsDto.getTerm();
        }

        rentalOrder.setTotalPrice(totalPrice);

        PassportData passportData = new PassportData(orderDetailsDto.getPassportName(), orderDetailsDto.getSurname(),
                orderDetailsDto.getPatronicalName(), orderDetailsDto.getPassportNumber(), orderDetailsDto.getRNTRC());

        User client = userService.getUserById(orderDetailsDto.getUserId());

        if (client.getPassportData() == null) {
            passportData.setUser(client);

            client.setPassportData(passportData);
        }

        rentalOrder.setUser(client);

        rentalOrder.setCar(carService.getCarById(orderDetailsDto.getCarId()));

        rentalOrderRepository.deleteClientsOrderByCarId(orderDetailsDto.getUserId(), orderDetailsDto.getCarId());

        return rentalOrderRepository.save(rentalOrder);

    }

    public void setOrderStatusById(Integer orderId, String orderStatusName) {
        rentalOrderRepository.setOrderStatusById(orderId, orderStatusName);
    }

    public void payOrder(Integer orderId) {
        setOrderStatusById(orderId, RentalOrderStatus.PAID.name());
    }

    public void confirmOrder(Integer orderId) {
        setOrderStatusById(orderId, RentalOrderStatus.CONFIRMED.name());

        RentalOrder order = getOrderById(orderId);

        Car car = order.getCar();

        car.setInUsage(true);

        rentalOrderRepository.rejectAllBesidesOneByCarId(order.getId(), car.getId());

        rentalOrderRepository.save(order);
    }

    public void rejectOrder(Integer orderId, String rejectMessageText) {

        RentalOrder order = getOrderById(orderId);

        RentalOrderRejectReason rejectReason = new RentalOrderRejectReason(rejectMessageText);

        rejectReason.setRentalOrder(order);

        order.setOrderRejectReason(rejectReason);

        order.setStatus(RentalOrderStatus.REJECTED);

        rentalOrderRepository.save(order);
    }

    public void returnOrder(Integer orderId) {
        setOrderStatusById(orderId, RentalOrderStatus.USED.name());
    }

    public void presentRepairInvoice(Integer orderId) {
        setOrderStatusById(orderId, RentalOrderStatus.USED_NEED_REPAIR.name());
    }

    public void confirmReturn(Integer orderId) {
        RentalOrder order = getOrderById(orderId);

        order.setStatus(RentalOrderStatus.RETURNED);

        Car car = order.getCar();

        car.setInUsage(false);

        rentalOrderRepository.save(order);
    }

    public void deleteAllReturnedOrders() {
        rentalOrderRepository.deleteAllByStatus(RentalOrderStatus.RETURNED);
    }
}

