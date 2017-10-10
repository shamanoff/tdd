package com.hanselnpetal.controller;

import com.hanselnpetal.domain.CustomerContact;
import com.hanselnpetal.service.ContactsManagementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ContactsManagementController.class)
public class ContactsManagementControllerUnitTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ContactsManagementService contactsManagementService;
	@InjectMocks
	private ContactsManagementController contactsManagementController;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);	
	}
	@Test
	public void testAddContactHappyPath() throws Exception {
		// setup mock Contact returned the mock service component
		CustomerContact mockCustomerContact = new CustomerContact();
		mockCustomerContact.setFirstName("Fred");
		//просто когда добавляется любой контакт возвращать мок
		when(contactsManagementService.add(any(CustomerContact.class)))
			.thenReturn(mockCustomerContact);
		// simulate the form bean that would POST from the web page
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("Fred");
		aContact.setEmail("fred@email.com");
		// simulate the form submit (POST)
		mockMvc
			.perform(post("/addContact", aContact))
            .andExpect(status().isOk())
            .andReturn();
	}
	
	@Test
	public void testAddContactBizServiceRuleNotSatisfied() throws Exception {
		
		// setup a mock response of NULL object returned from the mock service component
		when(contactsManagementService.add(any(CustomerContact.class)))
			.thenReturn(null);
		
		// simulate the form bean that would POST from the web page
		CustomerContact aContact = new CustomerContact();
		
		
		// simulate the form submit (POST)
		mockMvc
			.perform(post("/addContact", null))
			.andExpect(status().is(null))
			.andReturn();
	}
	
	@Test
	public void testAddContactOccasionHappyPath() throws Exception {
		// implement this
	}
}
