package com.tourguide.library.type_adapters;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * Allows Gson to deal with {@code javax.money} API.
 */
public class MoneyTypeAdapterFactory implements TypeAdapterFactory {

    private final MonetaryAmountFactory<? extends MonetaryAmount> monetaryFactory;

    /**
     * This is the Default implementation. Here, the implementation of the {@code javax.money} defined in the classpath
     * will be used.
     */
    public MoneyTypeAdapterFactory() {
        this(Monetary.getDefaultAmountFactory());
    }

    public MoneyTypeAdapterFactory(final MonetaryAmountFactory<? extends MonetaryAmount> monetaryFactory) {
        this.monetaryFactory = monetaryFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {

        final Class<T> clazz = (Class<T>) typeToken.getRawType();

        if (MonetaryAmount.class.isAssignableFrom(clazz)) {
            return (TypeAdapter<T>) new MonetaryAmountAdapter(monetaryFactory);
        } else if (CurrencyUnit.class.isAssignableFrom(clazz)) {
            return (TypeAdapter<T>) new CurrencyUnitAdapter();
        }

        return null;
    }
}