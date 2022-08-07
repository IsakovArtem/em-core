package com.isakov.emcore.job.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isakov.emcore.job.dto.headhunter.vacancies.HeadHunterVacanciesDTO;
import com.isakov.emcore.job.dto.headhunter.vacancies.HeadHunterVacancyDTO;
import com.isakov.emcore.job.exceptions.HeadHunterException;
import com.isakov.emcore.job.service.HeadHunterApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class HeadHunterApiServiceImpl implements HeadHunterApiService {

    private final ObjectMapper objectMapper;

    @Value("${isakov.hh.vacancy.base-url}")
    private String headhunterVacancyUrl;

    /**    Default pagination params */
    @Override
    public List<HeadHunterVacancyDTO> getVacancies(String queryParam) {
        return this.getVacancies(queryParam,"0","3");
    }

    @Override
    public List<HeadHunterVacancyDTO> getVacancies(String queryParam,String page, String perPage) {
        HeadHunterVacanciesDTO headHunterVacanciesDTO = new HeadHunterVacanciesDTO();
        try {
            URIBuilder uriBuilder = new URIBuilder(headhunterVacancyUrl);
            URI uri = uriBuilder
                    .setParameter("text", queryParam)
                    .setParameter("page", page)
                    .setParameter("per_page", perPage)
                    .build();
            try {
                headHunterVacanciesDTO = objectMapper.readValue(requestServer(uri), HeadHunterVacanciesDTO.class);
            } catch (JsonProcessingException e) {
                log.debug(e.getMessage(), e);
                throw new HeadHunterException("JsonProcessingException", e);
            }
        } catch (URISyntaxException e) {
            log.debug(e.getMessage(), e);
        }
        return headHunterVacanciesDTO.getItems();
    }

    @Override
    public HeadHunterVacancyDTO getVacancy(long id) {
        URI uri;
        try {
            URIBuilder uriBuilder = new URIBuilder(headhunterVacancyUrl + "/" + id);
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new HeadHunterException("problems with uri", e);
        }

        HeadHunterVacancyDTO headHunterVacancyDTO;
        try {
            headHunterVacancyDTO = objectMapper.readValue(requestServer(uri), HeadHunterVacancyDTO.class);
        } catch (JsonProcessingException e) {
            log.debug(e.getMessage(), e);
            throw new HeadHunterException("json mapping problem", e);
        }
        return headHunterVacancyDTO;
    }

    private String requestServer(URI uri) {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        log.debug("uri=" + uri);
        HttpGet httpGet = new HttpGet(uri);
        httpGet.addHeader("Accept", "application/json");
        String response;
        try {
            response = EntityUtils.toString(httpClient.execute(httpGet).getEntity());
        } catch (IOException e) {
            log.debug(e.getMessage(), e);
            throw new HeadHunterException("httpClient.execute problem", e);
        }
        log.debug("response=" + response);
        return response;
    }

}
