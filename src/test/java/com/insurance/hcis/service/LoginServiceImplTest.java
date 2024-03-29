/**
 * 
 */
package com.insurance.hcis.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.insurance.hcis.dto.RequestApproverDto;
import com.insurance.hcis.dto.ResponseApproverDto;
import com.insurance.hcis.entity.Approver;
import com.insurance.hcis.exception.CommonException;
import com.insurance.hcis.repository.ApproverRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author SubhaMaheswaran
 * @Description This class is used for to do test operation for login service
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class LoginServiceImplTest {

	@Mock
	ApproverRepository approverRepository;
	@InjectMocks
	LoginServiceImpl loginServiceImpl;

	/**
	 * Test method for
	 * {@link com.insurance.hcis.service.LoginServiceImpl#login(com.insurance.hcis.dto.RequestApproverDto)}.
	 */
	@Before
	public void setup() {

	}

	@Test
	public void testLogin() throws CommonException {
		log.info(":: Enter into LoginServiceTEst----------::testLogin()");
		ResponseApproverDto responseApproverDto = new ResponseApproverDto();
		responseApproverDto.setRole("Approver1");
		responseApproverDto.setAppoverId(1);
		responseApproverDto.setApproverName("sharath");
		responseApproverDto.setRole("Approver1");
		responseApproverDto.setStatusCode(200);

		RequestApproverDto requestApproverDto = new RequestApproverDto();
		requestApproverDto.setEmail("sharath@gmail.com");
		requestApproverDto.setPassword("1234");
		Approver approverDetails = new Approver();
		approverDetails.setAppoverId(1);
		approverDetails.setEmail("sharath@gmail.com");
		approverDetails.setPassword("1234");
		approverDetails.setRole("Approver1");

		Mockito.when(approverRepository.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(Optional.of(approverDetails));
		Optional<ResponseApproverDto> actual = loginServiceImpl.login(requestApproverDto);
		assertNotNull(actual);
	}

	@Test(expected = CommonException.class)
	public void testLoginNegative() throws CommonException {
		log.info(":: Enter into LoginServiceTEst----------::testLogin()");
		ResponseApproverDto responseApproverDto = new ResponseApproverDto();
		responseApproverDto.setRole("Approver1");
		responseApproverDto.setAppoverId(1);
		responseApproverDto.setApproverName("sharath");
		responseApproverDto.setRole("Approver1");
		responseApproverDto.setStatusCode(200);

		RequestApproverDto requestApproverDto = new RequestApproverDto();
		requestApproverDto.setEmail("sharath@gmail.com");
		requestApproverDto.setPassword("1234");
		Approver approverDetails = null;

		Mockito.when(approverRepository.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(Optional.ofNullable(approverDetails));
		Optional<ResponseApproverDto> actual = loginServiceImpl.login(requestApproverDto);
		assertNotNull(actual);
	}

}
