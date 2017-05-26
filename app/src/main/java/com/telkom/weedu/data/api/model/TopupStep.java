package com.telkom.weedu.data.api.model;

/**
 * Created by ghiyatshanif on 4/4/17.
 */

public class TopupStep {
    private String merchantName;
    private String steps;

    public TopupStep(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
