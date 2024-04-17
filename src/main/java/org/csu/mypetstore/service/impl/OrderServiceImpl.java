package org.csu.mypetstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Update;
import org.csu.mypetstore.entity.LineItem;
import org.csu.mypetstore.entity.Order;
import org.csu.mypetstore.entity.Product;
import org.csu.mypetstore.entity.Sequence;
import org.csu.mypetstore.persistence.LineItemMapper;
import org.csu.mypetstore.persistence.OrderMapper;
import org.csu.mypetstore.persistence.ProductMapper;
import org.csu.mypetstore.persistence.SequenceMapper;
import org.csu.mypetstore.service.OrderService;
import org.csu.mypetstore.vo.LineItemVO;
import org.csu.mypetstore.vo.OrderVO;
import org.csu.mypetstore.vo.SequenceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private LineItemMapper lineItemMapper;
    @Autowired
    private SequenceMapper sequenceMapper;


    @Override
    public List<OrderVO> getOrdersByUsername(String username) {
        List<OrderVO> orderVOs = new ArrayList<>();

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid" , username);
        List<Order> orders = orderMapper.selectList(queryWrapper);
        for (Order order : orders) {
            OrderVO orderVO = new OrderVO();

            orderVO.setOrderId(order.getOrderId());
            orderVO.setOrderDate(order.getOrderDate());
            orderVO.setBillAddress1(order.getBillAddress1());
            orderVO.setBillAddress2(order.getBillAddress2());
            orderVO.setBillCity(order.getBillCity());
            orderVO.setBillCountry(order.getBillCountry());
            orderVO.setBillState(order.getBillState());
            orderVO.setBillZip(order.getBillZip());
            orderVO.setCourier(order.getCourier());
            orderVO.setBillToFirstName(order.getBillToFirstName());
            orderVO.setBillToLastName(order.getBillToLastName());
            orderVO.setCardType(order.getCardType());
            orderVO.setCreditCard(order.getCreditCard());
            orderVO.setExpiryDate(order.getExpiryDate());
            orderVO.setLocale(order.getLocale());
            orderVO.setShipAddress1(order.getShipAddress1());
            orderVO.setShipAddress2(order.getShipAddress2());
            orderVO.setShipCity(order.getShipCity());
            orderVO.setShipCountry(order.getShipCountry());
            orderVO.setShipState(order.getShipState());
            orderVO.setShipZip(order.getShipZip());
            orderVO.setShipToFirstName(order.getShipToFirstName());
            orderVO.setShipToLastName(order.getShipToLastName());
            orderVO.setUsername(order.getUsername());
            orderVO.setTotalPrice(order.getTotalPrice());

            orderVOs.add(orderVO);
        }
        return orderVOs;
    }


    @Override
    public Order getOrder(int orderId) {
        Order orderVO = new Order();
        Order order = orderMapper.selectById(orderId);

        orderVO.setOrderId(orderId);
        orderVO.setOrderDate(order.getOrderDate());
        orderVO.setBillAddress1(order.getBillAddress1());
        orderVO.setBillAddress2(order.getBillAddress2());
        orderVO.setBillCity(order.getBillCity());
        orderVO.setBillCountry(order.getBillCountry());
        orderVO.setBillState(order.getBillState());
        orderVO.setBillZip(order.getBillZip());
        orderVO.setCourier(order.getCourier());
        orderVO.setBillToFirstName(order.getBillToFirstName());
        orderVO.setBillToLastName(order.getBillToLastName());
        orderVO.setCardType(order.getCardType());
        orderVO.setCreditCard(order.getCreditCard());
        orderVO.setExpiryDate(order.getExpiryDate());
        orderVO.setLocale(order.getLocale());
        orderVO.setShipAddress1(order.getShipAddress1());
        orderVO.setShipAddress2(order.getShipAddress2());
        orderVO.setShipCity(order.getShipCity());
        orderVO.setShipCountry(order.getShipCountry());
        orderVO.setShipState(order.getShipState());
        orderVO.setShipZip(order.getShipZip());
        orderVO.setShipToFirstName(order.getShipToFirstName());
        orderVO.setShipToLastName(order.getShipToLastName());
        orderVO.setUsername(order.getUsername());
        orderVO.setTotalPrice(order.getTotalPrice());

        return orderVO;
    }

    @Override
    public void insertOrder(Order order) {
        if(order.getShipAddress1() == null) {
            order.setShipAddress1(order.getBillAddress1());
        }
        if(order.getShipAddress2() == null) {
            order.setShipAddress2(order.getBillAddress2());
        }
        if(order.getShipCity() == null) {
            order.setShipCity(order.getBillCity());
        }
        if(order.getShipCountry() == null) {
            order.setShipCountry(order.getBillCountry());
        }
        if(order.getShipState() == null) {
            order.setShipState(order.getBillState());
        }
        if(order.getShipZip() == null) {
            order.setShipZip(order.getBillZip());
        }
        if(order.getShipToFirstName() == null) {
            order.setShipToFirstName(order.getBillToFirstName());
        }
        if(order.getShipToLastName() == null) {
            order.setShipToLastName(order.getBillToLastName());
        }
        int result = orderMapper.insert(order);
        if (result != 1) {
            throw new RuntimeException("插入订单失败");
        }
    }

    @Override
    public void insertOrderStatus(Order order) {

    }

    @Override
    public Sequence getSequence(String name) {
        Sequence sequenceVO = new Sequence();
        Sequence sequence = sequenceMapper.selectById(name);
        sequenceVO.setName(sequence.getName());
        sequenceVO.setNextId(sequence.getNextId());

        return sequenceVO;
    }

    @Override
    public void updateSequence(String name) {
        Sequence sequence = sequenceMapper.selectById(name);
        sequence.setNextId(sequence.getNextId() + 1);
        sequenceMapper.updateById(sequence);
    }

    @Override
    public List<LineItemVO> getLineItemsByOrderId(int orderId) {
        List<LineItemVO> lineItemVOs = new ArrayList<>();

        QueryWrapper<LineItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderId" , orderId);
        List<LineItem> lineItems = lineItemMapper.selectList(queryWrapper);
        for (LineItem lineItem : lineItems) {
            LineItemVO lineItemVO = new LineItemVO();
            lineItemVO.setOrderId(lineItem.getOrderId());
            lineItemVO.setLineNum(lineItem.getLineNum());
            lineItemVO.setItemId(lineItem.getItemId());
            lineItemVO.setQuantity(lineItem.getQuantity());
            lineItemVO.setUnitPrice(lineItem.getUnitPrice());
            lineItemVOs.add(lineItemVO);
        }
        return lineItemVOs;
    }
}
