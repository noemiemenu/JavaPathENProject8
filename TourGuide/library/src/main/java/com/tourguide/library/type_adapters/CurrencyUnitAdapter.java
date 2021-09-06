package com.tourguide.library.type_adapters;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.UnknownCurrencyException;
import java.io.IOException;

/**
 * The type Currency unit adapter.
 */
public final class CurrencyUnitAdapter extends TypeAdapter<CurrencyUnit> {

    @Override
    public void write(final JsonWriter writer, final CurrencyUnit value) throws IOException {

        if (value == null) {
            writer.nullValue();
            return;
        }

        writer.value(value.getCurrencyCode());
    }

    @Override
    public CurrencyUnit read(final JsonReader reader) throws IOException {

        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }

        try {
            return Monetary.getCurrency(reader.nextString());
        } catch (UnknownCurrencyException e) {
            throw new JsonSyntaxException(e);
        }
    }
}