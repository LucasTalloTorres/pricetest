package org.example.infrastructure.rest.mapper;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.io.FileUtils;
import org.example.domain.model.Price;
import org.example.rest.model.PriceDto;
import org.example.util.ResourceTestPathEnum;
import org.json.JSONException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.stream.Stream;

public class PriceDtoMapperTest {

    private final PriceDtoMapper mapper;
    private final ObjectMapper objectMapper;

    public PriceDtoMapperTest() {
        this.mapper = new PriceDtoMapperImpl();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of("priceDto1.json","price1.json"),
                Arguments.of("priceDto2.json","price2.json")
        );
    }
    @ParameterizedTest
    @MethodSource("parameters")
    public void map(String domainModel, String entityModel) throws IOException, JSONException {
        String sourceModelJson = FileUtils.readFileToString(ResourceUtils.getFile(ResourceTestPathEnum.DOMAIN_MODEL_PATH.get() + entityModel), Charset.defaultCharset());
        String targetModelJson = FileUtils.readFileToString(ResourceUtils.getFile(ResourceTestPathEnum.REST_MODEL_PATH.get() + domainModel), Charset.defaultCharset());

        Price sourceModel = objectMapper.readValue(sourceModelJson, Price.class);
        PriceDto targetModel = mapper.map(sourceModel);

        JSONAssert.assertEquals(targetModelJson, objectMapper.writeValueAsString(targetModel), JSONCompareMode.LENIENT);
    }


}
