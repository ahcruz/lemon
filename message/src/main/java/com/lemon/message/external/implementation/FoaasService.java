package com.lemon.message.external.implementation;

import com.lemon.message.external.IFoaasService;
import com.lemon.message.external.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
@Service
public class FoaasService implements IFoaasService {
    private RestTemplate clientHttp;
    private String url;
    private final String ACTION = "/logs/Angel";

    @Autowired
    public FoaasService(RestTemplate clientHttp, @Value("${foaas.url}")String url) {
        this.clientHttp = clientHttp;
        this.url = url;
    }

    @Override
    public String getMessage() {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Message> entity = new HttpEntity<>(headers);

            String getStringUrl = String.format(url + ACTION);

            Message result = clientHttp.exchange(getStringUrl, HttpMethod.GET, entity, Message.class).getBody();

            return result.getMessage() + " - " + result.getSubtitle() ;
        }
        catch (Exception ex){
            log.error("Error in service : " + url + ACTION);
            return null;
        }
    }
}
