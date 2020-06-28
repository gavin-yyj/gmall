package com.gavin.gmall.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 22:08 2020/6/28
 * @Description:
 */
@Controller
public class CartController {

    @RequestMapping("addToCart")
    public String addToCart(){
        return "redirect:/success.html";
    }
}
