package com.github.chotkiymaster;


public class GenieJan extends PlayerJan {

    @Override
    public Wall step(Field field) {

        return new StepGenieJan(field).step();
    }
    
    @Override
    public String getName(){
        return "GenieJan";
    }
}
