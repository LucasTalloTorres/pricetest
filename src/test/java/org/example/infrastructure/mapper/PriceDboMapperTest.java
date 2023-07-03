package org.example.infrastructure.mapper;


import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.domain.model.Price;
import org.example.infraastructure.repository.entity.PriceDbo;
import org.example.infraastructure.repository.mapper.PriceDboMapper;
import org.example.infraastructure.repository.mapper.PriceDboMapperImpl;
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

public class PriceDboMapperTest {

    private final PriceDboMapper mapper;
    private final ObjectMapper objectMapper;

    public PriceDboMapperTest() {
        this.mapper = new PriceDboMapperImpl();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of("price1.json","priceDbo1.json"),
                Arguments.of("price2.json","priceDbo2.json")
        );
    }
    @ParameterizedTest
    @MethodSource("parameters")
    public void map(String domainModel, String entityModel) throws IOException, JSONException {
        String sourceModelJson = FileUtils.readFileToString(ResourceUtils.getFile(ResourceTestPathEnum.ENTITIES_PATH.get() + entityModel), Charset.defaultCharset());
        String targetModelJson = FileUtils.readFileToString(ResourceUtils.getFile(ResourceTestPathEnum.DOMAIN_MODEL_PATH.get() + domainModel), Charset.defaultCharset());

        PriceDbo sourceModel = objectMapper.readValue(sourceModelJson, PriceDbo.class);
        Price targetModel = mapper.map(sourceModel);

        JSONAssert.assertEquals(targetModelJson, objectMapper.writeValueAsString(targetModel), JSONCompareMode.LENIENT);
    }


}
