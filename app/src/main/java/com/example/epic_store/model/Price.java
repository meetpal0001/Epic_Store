package com.example.epic_store.model;

import java.io.Serializable;

public class Price implements Serializable {
    TotalPrice totalPrice;

    public TotalPrice getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(TotalPrice totalPrice) {
        this.totalPrice = totalPrice;
    }
}
