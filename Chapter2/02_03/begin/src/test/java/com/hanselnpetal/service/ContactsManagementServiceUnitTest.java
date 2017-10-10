package com.hanselnpetal.service;

import com.hanselnpetal.data.repos.CustomerContactRepository;
import com.hanselnpetal.domain.CustomerContact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ContactsManagementServiceUnitTest {
    @Mock
	private CustomerContactRepository customerContactRepository;
	@InjectMocks
	private ContactsManagementService contactsManagementService;
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testAddContactHappyPath() {
		// Create a contact
		CustomerContact aMockContact = new CustomerContact();
		aMockContact.setFirstName("Jenny");
		aMockContact.setLastName("Johnson");
		
		when(customerContactRepository.save(any(CustomerContact.class))).thenReturn(aMockContact);
				
		// Save the contact
        // NULL потому что не важно что мы сохраним вернется все равно aMockContact
        CustomerContact newContact = contactsManagementService.add(null);

        // Verify the save
        assertEquals("Jenny", newContact.getFirstName());
        assertEquals("Johnson", newContact.getLastName());
	}
}
