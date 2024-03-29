/**
 * 
 */
package com.insurance.hcis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.insurance.hcis.dto.RequestClaimApproveDto;
import com.insurance.hcis.dto.ResponseClaimApproveDto;
import com.insurance.hcis.dto.ResponsePolicyClaim;
import com.insurance.hcis.dto.ResponsePolicyClaimDto;
import com.insurance.hcis.exception.CommonException;
import com.insurance.hcis.service.ApproverServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author SubhaMaheswaran
 * @Description This class is used for to do get claim and approve claim test
 *              operations
 */

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class ApproveControllerTest {

	@Mock
	ApproverServiceImpl approverServiceImpl;

	@InjectMocks
	ApproveController approveController;

	MockMvc mockMvc;

	RequestClaimApproveDto requestClaimApproveDto;
	RequestClaimApproveDto requestClaimApproveDto1;

	List<ResponsePolicyClaim> list;
	ResponsePolicyClaim responsePolicyClaim;
	ResponseClaimApproveDto responseClaimApproveDto;

	ResponseClaimApproveDto responseClaimApproveDto1;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(approveController).build();

		requestClaimApproveDto = new RequestClaimApproveDto();
		requestClaimApproveDto.setApproverId(1001);
		requestClaimApproveDto.setClaimId(1);
		requestClaimApproveDto.setComments("test");
		requestClaimApproveDto.setLevelOneStatus("Approved");
		requestClaimApproveDto.setLevelTwoStatus("complete");

		responsePolicyClaim = new ResponsePolicyClaim();
		responsePolicyClaim.setAilment("Cavities");
		responsePolicyClaim.setClaimId(1);
		list = new ArrayList<>();
		list.add(responsePolicyClaim);

		responseClaimApproveDto = new ResponseClaimApproveDto();
		responseClaimApproveDto.setApprovedLevelStatus("Approved");
		responseClaimApproveDto.setMessage("success");
		responseClaimApproveDto.setStatusCode(200);

		responseClaimApproveDto1 = new ResponseClaimApproveDto();
		responseClaimApproveDto1.setMessage("success");
		responseClaimApproveDto1.setStatusCode(200);
		requestClaimApproveDto1 = new RequestClaimApproveDto();

	}

	@Test
	public void testGetClaims() throws Exception {
		log.info(":: Enter into ApproveControllerTest--------::testGetClaims()");
		Mockito.when(approverServiceImpl.getClaims(1, "pending L1")).thenReturn(Optional.of(list));
		ResponseEntity<ResponsePolicyClaimDto> responseClaimApproveDto = approveController.getClaims(1, "pending L1");
		Assert.assertNotNull(responseClaimApproveDto);
	}

	@Test(expected = CommonException.class)
	public void testGetClaimsNegative() throws Exception {
		log.info(":: Enter into ApproveControllerTest--------::testGetClaimsNegative()");
		Mockito.when(approverServiceImpl.getClaims(1, "pending L1")).thenReturn(Optional.ofNullable(null));
		ResponseEntity<ResponsePolicyClaimDto> responseClaimApproveDto = approveController.getClaims(1, "pending L1");
		Assert.assertNotNull(responseClaimApproveDto);
	}

	@Test
	public void testApproveClaim() throws CommonException {
		log.info(":: Enter into ApproveControllerTest--------::testApproveClaim()");
		Mockito.when(approverServiceImpl.approveClaim(Mockito.any())).thenReturn(responseClaimApproveDto);
		ResponseEntity<ResponseClaimApproveDto> response = approveController.approveClaim(requestClaimApproveDto);
		Assert.assertNotNull(response);
	}

}
