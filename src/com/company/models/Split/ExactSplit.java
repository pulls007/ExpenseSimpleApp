package com.company.models.Split;

import com.company.models.User;

public class ExactSplit extends Split {
    public ExactSplit(User user, double amount) {
        super(user);
        this.setAmount(amount); ;
    }
}
