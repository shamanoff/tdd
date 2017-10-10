package com.hanselnpetal.service;

import com.hanselnpetal.domain.CustomerContact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
// NONE значит что никакие контролеры не буду загружены
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ContactsManagementServiceIntegrationTest {
	// минимум нужен сервисы который мы будем тестировать
	@Autowired
	private ContactsManagementService contactsManagementService;

	@Test
	public void testAddContactHappyPath() {
				// Create a contact
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("Jenny");
		aContact.setLastName("Johnson");

		// Test adding the contact
        CustomerContact newContact = contactsManagementService.add(aContact);
		
		// Verify the addition

		assertNotNull(newContact);
		assertNotNull(newContact.getId());
		assertEquals("Jenny", newContact.getFirstName());
		
	}
}
