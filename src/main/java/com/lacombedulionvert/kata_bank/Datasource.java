package com.lacombedulionvert.kata_bank;

import java.util.ArrayList;
import java.util.List;

public class Datasource {
    private final List<AccountOperation> operations;

    public Datasource() {
        operations = new ArrayList<>();
    }

    public List<AccountOperation> getData(){
        return operations;
    }
}