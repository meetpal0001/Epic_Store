package com.example.epic_store.model;

import com.example.epic_store.model.FmtPrice;

import java.io.Serializable;

public class TotalPrice implements Serializable {
    FmtPrice fmtPrice;

    public FmtPrice getFmtPrice() {
        return fmtPrice;
    }

    public void setFmtPrice(FmtPrice fmtPrice) {
        this.fmtPrice = fmtPrice;
    }
}
