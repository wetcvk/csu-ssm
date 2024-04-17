package org.csu.mypetstore.controller;

import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.entity.Item;
import org.csu.mypetstore.entity.Order;
import org.csu.mypetstore.entity.Sequence;
import org.csu.mypetstore.persistence.OrderMapper;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.vo.*;
import org.springframework.ui.Model;
import org.csu.mypetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CatalogService catalogService;

    @GetMapping("mainForm")
    public String mainForm() {
        return "catalog/main";
    }

    @GetMapping("NewOrderForm")
    public String newOrder(Model model,HttpSession session){
        Order order = new Order();
        model.addAttribute("order",order);
        return "order/NewOrderForm";
    }

    @PostMapping("ConfirmOrder")
    public String ConfirmOrder(HttpSession session,Model model,@ModelAttribute Order order){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(new java.util.Date().getTime());
        order.setOrderDate(date);
        session.setAttribute("order",order);
        model.addAttribute("order",order);
        return "order/ConfirmOrder";
    }


    @GetMapping ("ViewOrder")
    public String ViewOrder(HttpSession session, Model model){
        Order order = (Order)session.getAttribute("order");
        int orderId1 = order.getOrderId();
        if(order.getOrderId() != 0){
            Order orderVO = orderService.getOrder(orderId1);
            session.setAttribute("order",orderVO);
            model.addAttribute("order",orderVO);
        }
        else {
            AccountVO accountVO = (AccountVO)session.getAttribute("account");
            order.setUsername(accountVO.getUsername());
            Sequence sequence = orderService.getSequence("ordernum");
            order.setOrderId(sequence.getNextId());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(new java.util.Date().getTime());
            order.setOrderDate(date);
            orderService.insertOrder(order);
            session.setAttribute("order",order);
            model.addAttribute("order",order);
            orderService.updateSequence("ordernum");
        }
        return "/order/ViewOrder";
    }

    @GetMapping("listOrders")
    public String listOrders(HttpSession session,Model model){
        AccountVO account = (AccountVO) session.getAttribute("account");
        String username = account.getUsername();
        List<OrderVO> orderVO = orderService.getOrdersByUsername(username);
        session.setAttribute("orderList",orderVO);
        model.addAttribute("orderList", orderVO);
        return "order/ListOrders";
    }
}
