package com.solvd.tacoursesolvd.wearshopproject.shop;

import com.solvd.tacoursesolvd.wearshopproject.shop.catalog.Basket;
import com.solvd.tacoursesolvd.wearshopproject.shop.catalog.Product;
import com.solvd.tacoursesolvd.wearshopproject.shop.catalog.Wear;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCart<E extends Position> implements Sellable {

    private static final Logger LOGGER = LogManager.getLogger();

    private double totalPrice;
    private List<Double> prices;
    private Basket<Wear> wearBasket;

    public void addWears(List<Wear> wears) {
        wearBasket.setProduct(wears);
        for (Wear wear : wearBasket.getProduct()) {
            this.prices.add(wear.getProductCost());
        }
    }

    public void calculatedTotalPrice() {
        for (Double price : prices) {
            this.totalPrice += price;
        }
    }

    public void printCheck(Shop shop, E employee) {
        LOGGER.debug("\n" + shop.getShopName());
        LOGGER.debug(shop.getAddresses().getStreet() + " " + shop.getAddresses().getHouseNumber());
        LOGGER.debug("Seller:" + shop.getDepartment().getEmployee(employee).getName() + shop.getDepartment().getEmployee(employee).getSurname());
        LOGGER.debug("Date: " + LocalDateTime.now().toLocalDate());
        LOGGER.debug("Time: " + LocalDateTime.now().toLocalTime().withNano(0));
        int i = 1;
        for (Product wear : wearBasket.getProduct()) {
            LOGGER.debug((i) + ". ......" + wear.getProductCost());
            i++;
        }
        LOGGER.debug("Total...." + totalPrice + "\n\n\n");
    }

    @Override
    public void printCheck() {
        LOGGER.debug("Date: " + LocalDateTime.now().toLocalDate());
        LOGGER.debug("Time: " + LocalDateTime.now().toLocalTime().withNano(0));
        int i = 1;
        for (Product wear : wearBasket.getProduct()) {
            LOGGER.debug((i) + ". ......" + wear.getProductCost());
            i++;
        }
        LOGGER.debug("Total...." + totalPrice);
    }

    @Override
    public String toString() {
        return "Total price: " + totalPrice + " for " + wearBasket;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Basket<Wear> getWearBasket() {
        return wearBasket;
    }

    public void setWearBasket(Basket<Wear> wearBasket) {
        this.wearBasket = wearBasket;
    }

    public List<Double> getPrices() {
        return prices;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }
}