package app.web.config;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/*@Component
public class FormUrlencodedHttpMessageConverter extends
        AbstractGenericHttpMessageConverter<MultiValueMap<String, String>> {

    private static final MediaType CONVERTER_MEDIA_TYPE = MediaType.APPLICATION_FORM_URLENCODED;

    public FormUrlencodedHttpMessageConverter() {
        super(CONVERTER_MEDIA_TYPE);
    }

    @Override
    public MultiValueMap<String, String> read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {

        String urlEncodedData = new BufferedReader(
                new InputStreamReader(inputMessage.getBody(), StandardCharsets.UTF_8)).readLine();

        String[] keyValuePairs = urlEncodedData.split("&");
        MultiValueMap<String, String> keyValuePairsMap = new LinkedMultiValueMap<>();

        for (String keyValuePair : keyValuePairs) {
            String[] pairElements = keyValuePair.split("=");
            String key = pairElements[0];
            String value = pairElements[1];
            keyValuePairsMap.add(key, value);
        }

        return keyValuePairsMap;
    }

    @Override
    protected boolean canRead(@Nullable MediaType mediaType) {

        return CONVERTER_MEDIA_TYPE.includes(mediaType);
    }

    @Override
    protected boolean canWrite(@Nullable MediaType mediaType) {

        return CONVERTER_MEDIA_TYPE.includes(mediaType);
    }

    @Override
    protected void writeInternal(MultiValueMap<String, String> t, Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        throw new RuntimeException("Method 'writeInternal' in " + this.getClass().getSimpleName() + " is not implemented");
    }

    @Override
    protected MultiValueMap<String, String> readInternal(Class<? extends MultiValueMap<String, String>> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        throw new RuntimeException("Method 'readInternal' in " + this.getClass().getSimpleName() + " is not implemented");
    }
 */