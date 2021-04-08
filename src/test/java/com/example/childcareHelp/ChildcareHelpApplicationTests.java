package com.example.childcareHelp;

import com.example.childcareHelp.DAO.BabysitterRepository;
import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.service.BabysitterService;
import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootTest
@EnableWebMvc
class ChildcareHelpApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;


	@Test
	void contextLoads() {
	}

	@Test
	void loginTest() throws Exception {

		mockMvc = webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(get("/login"))
				.andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	void listOfBabysitterTest() throws Exception {

		mockMvc = webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(get("/babysitter/listOfBabysitters"))
				.andExpect(status().isOk()); // check its return code is 200

	}


}
