package org.projecthusky.codegenerator.cda.rest;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.projecthusky.valueset.api.ValueSetManager;
import org.projecthusky.valueset.config.ValueSetConfig;
import org.projecthusky.valueset.model.ValueSet;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * husky-codegenerator
 *
 * @author Quentin Ligier
 **/
public class ValueSetRestClient {

    private final HttpClient httpClient;
    private final ValueSetManager valueSetManager = new ValueSetManager();

    public ValueSetRestClient() {
        final var requestConfig = RequestConfig.custom()
                .setConnectTimeout(10*1000)
                .setConnectionRequestTimeout(10*1000)
                .setSocketTimeout(10*1000)
                .build();
        this.httpClient = HttpClientBuilder.create()
                .setDefaultHeaders(List.of(new BasicHeader(HttpHeaders.USER_AGENT, "Husky/1.0")))
                .setDefaultRequestConfig(requestConfig)
                .build();
    }


    public ValueSet fetchValueSet(final ValueSetConfig valueSetConfig)
            throws IOException, ParserConfigurationException, SAXException {
        final HttpGet getRequest = new HttpGet(valueSetConfig.getSourceUrl());
        getRequest.addHeader("Accept", "*/*");
        final HttpResponse response = this.httpClient.execute(getRequest);

        // Check for HTTP response code: 200 = success
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException(
                    "Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
        final var stream = response.getEntity().getContent();
        final ValueSet valueSet;
        switch (valueSetConfig.getSourceFormatType()) {
            case JSON:
                valueSet = this.valueSetManager.loadValueSetJson(stream);
                break;
            case XML:
                valueSet = this.valueSetManager.loadValueSetXml(stream);
                break;
            case IHESVS:
                valueSet = this.valueSetManager.loadValueSetIheSvs(stream);
                break;
            default:
                throw new RuntimeException("Not implemented");
        }
        stream.close();
        getRequest.releaseConnection();
        return valueSet;
    }
}
