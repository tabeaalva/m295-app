package ch.tabea.reiffer.calendar;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import ch.tabea.reiffer.calendar.Category.Category;
import ch.tabea.reiffer.calendar.Category.CategoryRepository;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class RestControllerTest {
    @Autowired
    private MockMvc api;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeAll
    void setup() {
        this.categoryRepository.save(new Category("category1"));
        this.categoryRepository.save(new Category("category2"));
    }

    @Test
    @Order(1)
    void testGetCategory() throws Exception {

        String accessToken = obtainAccessToken();

        api.perform(get("/api/category").header("Authorization", "Bearer " + accessToken)
                 .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }

        private String obtainAccessToken() {

            RestTemplate rest = new RestTemplate();
    
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    
            String body = "client_id=demoapp&" +
                    "grant_type=password&" +
                    "scope=openid profile roles offline_access&" +
                    "username=user&" +
                    "password=user";
    
            HttpEntity<String> entity = new HttpEntity<>(body, headers);
    
            ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/ILV/protocol/openid-connect/token", entity, String.class);
    
            JacksonJsonParser jsonParser = new JacksonJsonParser();
            return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
        }
}
