package com.autobots.automanager.models;


import java.util.List;

public interface AddLink<T> {
    public void linkAdd(List<T> list);
    public void linkAdd(T objeto);

}
