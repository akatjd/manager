package kr.co.manager;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static kr.co.manager.ApiDocumentUtils.getDocumentRequest;
import static kr.co.manager.ApiDocumentUtils.getDocumentResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "15.165.254.229")
public class JUnit5ExampleTests{
    private MockMvc mockMvc;
    
    private RestDocumentationResultHandler document;
    
    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
            RestDocumentationContextProvider restDocumentation) {
        this.document = document(
                "{class-name}/{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        );
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)) 
                .addFilters(new CharacterEncodingFilter("UTF-8", true)) // ????????? utf-8 ??????????????? ???
                .build();
    }
    
    @Test
    public void getAlarmHistoryList() throws Exception {
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/external/getAlarmHistory/{startDate}/{endDate}", "20220629120000", "20220629120500")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("alarm-history-get",
                        getDocumentRequest(),
                        getDocumentResponse(),
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("startDate").description("??????????????????"),
                                parameterWithName("endDate").description("??????????????????")
                                ),
                        responseFields(
                                fieldWithPath("[].alarmMsg").description("???????????????"),
                                fieldWithPath("[].endDate").description("??????????????????"),
                                fieldWithPath("[].factoryName").description("?????????"),
                                fieldWithPath("[].lineName").description("?????????"),
                                fieldWithPath("[].machineName").description("?????????"),
                                fieldWithPath("[].alarmType").description("????????????"),
                                fieldWithPath("[].gapSec").description("??????????????????(???)"),
                                fieldWithPath("[].startDate").description("??????????????????"),
                                fieldWithPath("[].factoryId").description("??????????????? ?????? ?????????"),
                                fieldWithPath("[].alarmId").description("??????????????? ?????? ?????????"),
                                fieldWithPath("[].alarmTypeId").description("??????????????? ?????? ?????????"),
                                fieldWithPath("[].alarmStatusId").description("??????????????? ?????? ?????????"),
                                fieldWithPath("[].machineId").description("??????????????? ?????? ?????????"),
                                fieldWithPath("[].lineId").description("??????????????? ?????? ?????????")
                                )
                        ));
    }

//    @Test
//    public void getPerson() throws Exception {
//        this.mockMvc.perform(get("/person/{name}", "seungwoo")
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document.document(
//                        pathParameters(parameterWithName("name").description("PathVariable name")),
//                        /*requestFields(
//                                fieldWithPath("name").description("The name of the input")),*/
//                        responseFields(
//                                fieldWithPath("id").description("The id of the output"),
//                                fieldWithPath("name").description("The name of the output"),
//                                fieldWithPath("email").description("The email of the output"),
//                                fieldWithPath("date").description("The date of the output"))))
//                .andExpect(jsonPath("$.id", is(notNullValue())))
//                .andExpect(jsonPath("$.name", is(notNullValue())))
//                .andExpect(jsonPath("$.email", is(notNullValue())))
//                .andExpect(jsonPath("$.date", is(notNullValue())));
//    }
}
