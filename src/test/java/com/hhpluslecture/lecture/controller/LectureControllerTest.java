package com.hhpluslecture.lecture.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hhpluslecture.lecture.aggregate.command.ApplyLectureCommand;
import com.hhpluslecture.lecture.aggregate.command.RegisterLectureCommand;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String lectureTitle = "Test Title";
    private final int capacity = 30;
    private final String userId = "test-user";
    private final LocalDateTime startAt = LocalDateTime.of(2024, 6, 1, 12, 0);
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
         objectMapper = new ObjectMapper();
         objectMapper.registerModule(new JavaTimeModule());
    }


    @Test
    @Order(1)
    @DisplayName("step2 - 특강생성 검증")
    public void create() throws Exception {
        RegisterLectureCommand command = new RegisterLectureCommand(lectureTitle, capacity, startAt);
        mockMvc.perform(
                post("/lectures")
                        .content(objectMapper.writeValueAsString(command))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    @DisplayName("step2 - 특강목록 조회 검증")
    public void loadLectureList() throws Exception {
        mockMvc.perform(get("/lectures?userId=" + userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(lectureTitle))
                .andExpect(jsonPath("$[0].capacity").value(capacity));
    }

    @Test
    @Order(3)
    @DisplayName("step3 - 특강 신청 검증")
    public void apply() throws Exception {
        ApplyLectureCommand command = new ApplyLectureCommand(1, userId);
        mockMvc.perform(post("/lectures/apply")
                        .content(objectMapper.writeValueAsString(command))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    @DisplayName("step4 - 특강 신청여부 조회 검증")
    public void applyComplete() throws Exception {
        String res = mockMvc.perform(get(String.format("/lectures/application/%s?lectureId=%d", userId, 1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(res, "true");
    }

    @Test
    @Order(5)
    @DisplayName("step5 - 특강 신청후 목록 조회 노출 여부 검증")
    public void loadEmptyLectureList() throws Exception {
        mockMvc.perform(get("/lectures?userId=" + userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));;
    }
}
