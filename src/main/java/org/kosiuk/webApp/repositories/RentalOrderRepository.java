package org.kosiuk.webApp.repositories;

import org.kosiuk.webApp.entity.RentalOrder;
import org.kosiuk.webApp.entity.RentalOrderStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface RentalOrderRepository extends CrudRepository<RentalOrder, Integer> {

    @Query(value = "SELECT * FROM rental_order WHERE account_id = :account_id AND car_id = :car_id", nativeQuery = true)
    RentalOrder getClientsOrderByCarId(@Param("account_id") Integer clientId, @Param("car_id") Integer carId);

    @Transactional
    @Query(value = "DELETE FROM rental_order WHERE account_id = :account_id AND car_id = :car_id", nativeQuery = true)
    @Modifying
    void deleteClientsOrderByCarId(@Param("account_id") Integer clientId, @Param("car_id") Integer carId);

    @Transactional
    @Query(value = "UPDATE rental_order SET status = :status WHERE id = :id", nativeQuery = true)
    @Modifying
    void setOrderStatusById(@Param("id") Integer id, @Param("status") String status);

    @Transactional
    @Query(value = "UPDATE rental_order SET status = 'REJECTED' WHERE id != :order_id AND car_id = :car_id", nativeQuery = true)
    @Modifying
    void rejectAllBesidesOneByCarId(@Param("order_id") Integer orderId, @Param("car_id") Integer carId);

    @Transactional
    void deleteAllByStatus(RentalOrderStatus status);

}
