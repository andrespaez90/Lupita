package com.example.lupita.ui.adapters.list.models;

public interface GenericCategoryItem<T> extends GenericItem<T> {

    String getCategoryName();

    int compareTo(GenericCategoryItem var1);
}
