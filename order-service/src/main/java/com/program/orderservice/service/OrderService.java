package com.program.orderservice.service;

import com.program.orderservice.dto.OrderLineItemsDto;
import com.program.orderservice.dto.OrderRequest;
import com.program.orderservice.model.Order;
import com.program.orderservice.model.OrderLineItems;
import com.program.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

       List<OrderLineItems> orderLineItemsList =  orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map((OrderLineItemsDto orderLineItemsDto) -> mapToDto(orderLineItemsDto, order))
                .toList();
       order.setOrderLineItemsList(orderLineItemsList);
       orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto, Order order) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setOrder(order);
        return orderLineItems;
    }
}
