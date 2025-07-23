import jakarta.persistence.EntityNotFoundException;
import kpo.entities.OrderEntity;

import kpo.enums.OrderStatus;
import kpo.repositories.OrderRepository;

import kpo.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;


    @Test
    void getOrder_Success() {
        OrderEntity order = new OrderEntity();
        order.setId(1);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        OrderEntity result = orderService.getOrder(1);
        assertEquals(1, result.getId());
    }

    @Test
    void getOrder_NotFound() {
        when(orderRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                orderService.getOrder(1)
        );
    }

    @Test
    void getOrdersByUserId() {
        List<OrderEntity> orders = List.of(
                new OrderEntity(1, 100, OrderStatus.NEW, "A"),
                new OrderEntity(1, 200, OrderStatus.NEW, "B")
        );
        when(orderRepository.findAllByUserId(1)).thenReturn(orders);

        List<OrderEntity> result = orderService.getOrdersByUserId(1);
        assertEquals(2, result.size());
    }
}