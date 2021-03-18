package com.tools.st.test;

import com.tools.st.entity.Result;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;


public class BaseTest {
    protected static final RestTemplate rt = new RestTemplate();
    static CloseableHttpClient client = HttpClients.createDefault();

    public static String CONTEXT_PATH = "/mesmold";
    public static String TOKEN = "auth-token";

    protected static String LOGIN_HOST = "localhost";
    protected static int LOGIN_PORT = 9081;

    protected static String HOST = "localhost";
    protected static int PORT = 9090;
    protected static String USER = "admin";
    protected static String PWD = "111111";
    public static String jwtToken = "";

    @BeforeAll
    public static void init() {
        for (HttpMessageConverter converter : rt.getMessageConverters()) {
            //spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
            //spring.jackson.time-zone=GMT+8
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ((MappingJackson2HttpMessageConverter)converter).getObjectMapper()
                        .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                        .setTimeZone(TimeZone.getTimeZone("GMT+8"));
            }
        }
        jwtToken = doLogin();
    }

    public URI getUrl(String uri, List<NameValuePair> params) throws URISyntaxException {
        //uri不能包括参数
        return new URIBuilder().setScheme("http").setHost(HOST).setPort(PORT).setPath(CONTEXT_PATH + uri)
                .setParameters(params == null ? Lists.newArrayList() : params).build();
    }

    public URI getUrl(String uri) throws URISyntaxException {
        return getUrl(uri, null);
    }

    public Result post(URI uri, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(TOKEN, jwtToken);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        return rt.postForObject(uri, entity, Result.class);
    }

    public Result delete(String url) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(TOKEN, jwtToken);
        RequestEntity entity = new RequestEntity(headers, HttpMethod.DELETE, getUrl(url));
        ResponseEntity<Result> ret = rt.exchange(entity, Result.class);
        return ret.getBody();
    }

    public Result get(URI uri) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(TOKEN, jwtToken);
        RequestEntity entity = new RequestEntity(headers, HttpMethod.GET, uri);
        return rt.exchange(entity, Result.class).getBody();
    }

    public Result post(String uri, Object body) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(TOKEN, jwtToken);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        return rt.postForObject(getUrl(uri, null), entity, Result.class);
    }

    public Result get(String uri, List<NameValuePair> params) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(TOKEN, jwtToken);
        RequestEntity entity = new RequestEntity(headers, HttpMethod.GET, getUrl(uri, params));
        return rt.exchange(entity, Result.class).getBody();
    }

    public <T> T get(String uri, List<NameValuePair> params, ParameterizedTypeReference<T> type) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(TOKEN, jwtToken);
        RequestEntity entity = new RequestEntity(headers, HttpMethod.GET, getUrl(uri, params));
        return rt.exchange(entity, type).getBody();
    }

    public <T> T get(String uri, Class<T> clz) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(TOKEN, jwtToken);
        RequestEntity entity = new RequestEntity(headers, HttpMethod.GET, getUrl(uri, null));
        return rt.exchange(entity, clz).getBody();
    }

    public <T> T get(String uri, ParameterizedTypeReference<T> type) throws URISyntaxException {
        return get(uri, null, type);
    }

    public Result get2(String uri, List<NameValuePair> params) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(TOKEN, jwtToken);
        RequestEntity entity = new RequestEntity(headers, HttpMethod.GET, getUrl(uri, params));
        return rt.exchange(entity, Result.class).getBody();
    }

    public Result get(String uri) throws URISyntaxException {
        return get(uri, Lists.newArrayList());
    }

    public Result postFile(String file, String uri) throws URISyntaxException, FileNotFoundException {
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("fileType", "user");
        parts.add("file", new File(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add(TOKEN, jwtToken);
        parts.add("xx", new HttpEntity<>("xxxx", headers));
//        HttpEntity entity = new HttpEntity<>(parts, headers);
//        RequestEntity requestEntity = new RequestEntity(parts, headers, HttpMethod.POST, getUrl(uri));
//        return rt.exchange(requestEntity, new ParameterizedTypeReference<Result<FileVo>>(){}).getBody();
        return rt.postForObject(getUrl(uri), parts, Result.class);
    }

    public Result put(String uri, Object body) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(TOKEN, jwtToken);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        rt.put(getUrl(uri, null), entity);
        return new Result();
    }

    //@Test
    public static String doLogin() {
//        SysUserVO user = new SysUserVO();
//        user.setUsername(USER);
//        user.setPassword(PWD);
//        HttpEntity<SysUserVO> entity = new HttpEntity<>(user);
//        ResponseEntity<Object> response = rt.exchange("http://{host}:{port}/mesbasic/login", HttpMethod.POST, entity, Object.class, LOGIN_HOST, LOGIN_PORT);
//        List<String> tokens = response.getHeaders().get(TOKEN);
//        if (tokens == null || tokens.isEmpty()) {
//            throw new RuntimeException("login fail, token not return");
//        }
//        String jwt = tokens.get(0);
//        if (StringUtils.isBlank(jwt)) {
//            throw new RuntimeException("login fail, null token");
//        }
//        return jwt;
        return "";
        //log.info("jwt token {}", jwt);
    }
//
//    protected Result<FileVo> uploadDoc(String file, String uri) throws Exception {
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            builder.addBinaryBody("file", new File(file));
//            builder.addTextBody("fileType", "user");
//            //multipart/form-data
//            org.apache.http.HttpEntity entity = builder.build();
//            HttpPost post = new HttpPost(getUrl(uri));
//            //You should NEVER set that header yourself. We set the header properly with the boundary. !!!!
//            //post.addHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.getMimeType());
//            post.setEntity(entity);
//            post.addHeader(TOKEN, jwtToken);
//
//            return client.execute(post, (r) -> JsonUtl.readValue(r.getEntity().getContent(), new TypeReference<Result<FileVo>>(){}));
//    }
}
