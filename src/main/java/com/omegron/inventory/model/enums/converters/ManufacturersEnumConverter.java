package com.omegron.inventory.model.enums.converters;

import com.omegron.inventory.model.enums.ManufacturersEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ManufacturersEnumConverter implements Converter<String, ManufacturersEnum> {

    @Override
    public ManufacturersEnum convert(String source) {
        return ManufacturersEnum.valueOf(source.replaceAll(" ", ""));
    }
}