package com.qjl.iot.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @业务描述：
 * @package_name： com.lenovo.bpms.common.utils
 * @project_name： lenovo-bms
 * @create_time： 2019-12-03
 */
public class BigDecimalSerialize extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        if (value != null && !"".equals(value)) {
            gen.writeString(value.setScale(2, BigDecimal.ROUND_HALF_DOWN) + "");
        } else {
            gen.writeString(value + "");
        }
    }
}
