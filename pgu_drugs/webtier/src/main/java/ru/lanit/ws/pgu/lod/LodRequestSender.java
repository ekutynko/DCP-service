package ru.lanit.ws.pgu.lod;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import ru.lanit.ws.pgu.PGUExceptions;
import ru.lanit.ws.pgu.journalling.JournalEntry;
import ru.lanit.ws.pgu.lod.request.LodResponseStatus;
import ru.lanit.ws.pgu.requests.BaseRequestComposite;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;

/**
 * Created by user06 on 25.08.2014.
 */
public class LodRequestSender {

    /*
    Класс, отправляющий заявку в ЛОД, и обрабатывающий ответ
     */

    private String address;

    private HttpClient httpClient;

    public LodRequestSender(String host, String port) { // или сразу одним параметром?
        this.address = "http://" + (port == null ? host : host + ":" + port) + "/";
        this.httpClient = HttpClientBuilder.create().build();
    }

    public Long sendLicenseRequest(String uri, BaseRequestComposite requestComposite) throws Exception {

        ru.lanit.ws.pgu.lod.request.LodResponse lodResponse = sendHttpRequest(uri, requestComposite,
                ru.lanit.ws.pgu.lod.request.LodResponse.class);

        return lodResponse.getLicenseRequestId();
    }

    public ru.lanit.ws.pgu.lod.catalog.LodResponse sendCatalogRequest(String uri,
            ru.lanit.ws.pgu.lod.catalog.LodRequest request) throws Exception {
        ru.lanit.ws.pgu.lod.catalog.LodResponse lodResponse = sendHttpRequest(uri, request,
                ru.lanit.ws.pgu.lod.catalog.LodResponse.class);
        return lodResponse;
    }

    /*
     * Добавить другие методы сервиса (статус, справочник, отмена заявки)
     */

    public void sendJournalEntry(String uri, JournalEntry journalEntry) throws Exception {
        sendHttpRequest(uri, journalEntry, LodBaseResponse.class);
    }

    /* *********************************************************************** */

    private <T extends LodBaseResponse> T sendHttpRequest(String uri, Object requestData, Class<T> responseDataClazz)
            throws Exception {
        //HttpPost request = new HttpPost(address + uri);
        HttpPost request = new HttpPost("http://localhost:8082/" + uri);
        request.setHeader("Content-type", "application/json");

        ObjectMapper mapper = new ObjectMapper();
//        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

        BasicHttpEntity entity = new BasicHttpEntity();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        mapper.writeValue(baos, requestData);
        entity.setContent(new ByteArrayInputStream(baos.toByteArray()));

        request.setEntity(new BufferedHttpEntity(entity));

        HttpResponse response = httpClient.execute(request); //TODO: добавить обработку ошибки "ЛОД недоступен"

        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF8"));
        StringBuilder json = new StringBuilder();
        String str;
        while ((str = br.readLine()) != null) {
            json.append(str);
        }
        br.close();

        T lodResponse = mapper.readValue(json.toString(), responseDataClazz);

        if (lodResponse.getStatus() != LodResponseStatus.ACCEPT) {
            throw new PGUExceptions.InternalError(lodResponse.getComment());
        }
        return lodResponse;
    }

}
