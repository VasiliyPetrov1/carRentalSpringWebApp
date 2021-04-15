package org.kosiuk.webApp.controllers;

import org.kosiuk.webApp.dto.OrderDetailsDto;
import org.kosiuk.webApp.entity.User;
import org.kosiuk.webApp.service.CarService;
import org.kosiuk.webApp.service.RentalOrderService;
import org.kosiuk.webApp.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/app/order")
public class RentalOrderController {

    private final CarService carService;
    private final UserService userService;
    private final RentalOrderService rentalOrderService;

    public RentalOrderController(CarService carService, UserService userService, RentalOrderService rentalOrderService) {
        this.carService = carService;
        this.userService = userService;
        this.rentalOrderService = rentalOrderService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{carId}")
    public String showOrderPanel(@PathVariable("carId") Integer carId,
                                 Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("orderDetailsDto", rentalOrderService.createOrderDetailsDTO(carId, user));
        return "newOrder";
    }

    @PostMapping()
    public String createNewOrder(@ModelAttribute("orderDetailsDto") OrderDetailsDto orderDetailsDto, Model model) {

        rentalOrderService.createOrder(orderDetailsDto);
        return "redirect:/app";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/account")
    public String showUserOrders(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("formedOrderList", rentalOrderService.getAllUserOrders(user.getId()));
        return "showAllUserOrders";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping()
    public String showAllOrders(Model model) {
        model.addAttribute("allOrdersList", rentalOrderService.getAllOrderDtos());
        return "showAllOrders";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{orderId}/pay")
    public String payOrder(@PathVariable("orderId") Integer orderId) {
        rentalOrderService.payOrder(orderId);
        return "redirect:/app/order/account";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/{orderId}/confirm")
    public String confirmOrder(@PathVariable("orderId") Integer orderId) {
        rentalOrderService.confirmOrder(orderId);
        return "redirect:/app/order";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/{orderId}/reject")
    public String showRejectForm(@PathVariable("orderId") Integer orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "rejectReason";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/{orderId}/reject")
    public String rejectOrder(@PathVariable("orderId") Integer orderId,
                              @RequestParam("rejectMessageText") String rejectMessageText) {
        rentalOrderService.rejectOrder(orderId, rejectMessageText);
        return "redirect:/app/order";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{orderId}/return")
    public String returnCarOrder(@PathVariable("orderId") Integer orderId) {
        rentalOrderService.returnOrder(orderId);
        return "redirect:/app/order/account";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/{orderId}/presentRepInv")
    public String presentRepairInvoice(@PathVariable("orderId") Integer orderId) {
        rentalOrderService.presentRepairInvoice(orderId);
        return "redirect:/app/order";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{orderId}/confirmReturn")
    public String confirmReturn(@PathVariable("orderId") Integer orderId) {
        rentalOrderService.confirmReturn(orderId);
        return "redirect:/app/order";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/deleteAllReturnedOrders")
    public String deleteAllReturnedOrders() {
        rentalOrderService.deleteAllReturnedOrders();
        return "redirect:/app/order";
    }
}
