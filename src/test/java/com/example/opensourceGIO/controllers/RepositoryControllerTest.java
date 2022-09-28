package com.example.opensourceGIO.controllers;

import com.example.opensourceGIO.controllers.requests.CreateRepositoryRequest;
import com.example.opensourceGIO.models.Repository;
import com.example.opensourceGIO.services.RepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RepositoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RepositoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RepositoryService repositoryService;

    @Test
    public void it_should_list_repositories() throws Exception {
        Repository repository = Repository.builder().organization("mucahit").repository("youcontribute").build();
        given(this.repositoryService.list()).willReturn(Collections.singletonList(repository));

        this.mockMvc.perform(get("/repositories")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("youcontribute"))
                .andExpect(jsonPath("$[0].organisation").value("mucahit"));
    }

    @Test
    public void it_should_create_repository() throws Exception{
        //given
        String organization = "github";
        String name = "octobat";
        CreateRepositoryRequest request = CreateRepositoryRequest.builder().organization(organization)
                .repository(name).build();

        doNothing().when(this.repositoryService).create(organization, name);

        //when
        this.mockMvc.perform(post("/repositories").content(this.objectMapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated() );

    }
}
