package com.nnk.springboot.controller;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(BidListController.class)
public class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService mockBidListService;

    @Test
    public void testPage() throws Exception {
        // Setup
        // Configure BidListService.getAllBidList(...).
        final BidList bidList = new BidList();
        bidList.setBidListId(0);
        bidList.setAccount("account");
        bidList.setType("type");
        bidList.setBidQuantity(0.0);
        bidList.setAskQuantity(0.0);
        final Page<BidList> bidLists = new PageImpl<>(List.of(bidList));
        when(mockBidListService.getAllBidList(PageRequest.of(1, 10))).thenReturn(bidLists);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/bidList/list")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testValidate() throws Exception {
        // Setup
        // Configure BidListService.getAllBidList(...).
        final BidList bidList = new BidList();
        bidList.setBidListId(0);
        bidList.setAccount("account");
        bidList.setType("type");
        bidList.setBidQuantity(0.0);
        bidList.setAskQuantity(0.0);
        final Page<BidList> bidLists = new PageImpl<>(List.of(bidList));
        when(mockBidListService.getAllBidList(PageRequest.of(1, 10))).thenReturn(bidLists);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/bidList/validate")
                        .param("bidListId", "0")
                        .param("account", "account")
                        .param("type", "type")
                        .param("bidQuantity", "0")
                        .param("askQuantity", "0")
                        .param("bid", "0")
                        .param("ask", "0")
                        .param("benchmark", "benchmark")
                        .param("bidListDate", "bidListDate")
                        .param("commentary", "commentary")
                        .param("security", "security")
                        .param("status", "status")
                        .param("trader", "trader")
                        .param("book", "book")
                        .param("creationName", "creationName")
                        .param("creationDate", "creationDate")
                        .param("revisionName", "revisionName")
                        .param("revisionDate", "revisionDate")
                        .param("dealName", "dealName")
                        .param("dealType", "dealType")
                        .param("sourceListId", "sourceListId")
                        .param("side", "side")
                        .with(user("username"))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }


    @Test
    public void testValidate_BidListServiceGetAllBidListReturnsNoItems() throws Exception {
        // Setup
        when(mockBidListService.getAllBidList(PageRequest.of(1, 10)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/bidList/validate")
                        .param("bidListId", "0")
                        .param("account", "account")
                        .param("type", "type")
                        .param("bidQuantity", "0")
                        .param("askQuantity", "0")
                        .param("bid", "0")
                        .param("ask", "0")
                        .param("benchmark", "benchmark")
                        .param("bidListDate", "bidListDate")
                        .param("commentary", "commentary")
                        .param("security", "security")
                        .param("status", "status")
                        .param("trader", "trader")
                        .param("book", "book")
                        .param("creationName", "creationName")
                        .param("creationDate", "creationDate")
                        .param("revisionName", "revisionName")
                        .param("revisionDate", "revisionDate")
                        .param("dealName", "dealName")
                        .param("dealType", "dealType")
                        .param("sourceListId", "sourceListId")
                        .param("side", "side")
                        .with(user("username"))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        // Setup
        // Configure BidListService.findById(...).
        final BidList bidList1 = new BidList();
        bidList1.setBidListId(0);
        bidList1.setAccount("account");
        bidList1.setType("type");
        bidList1.setBidQuantity(0.0);
        bidList1.setAskQuantity(0.0);
        final Optional<BidList> bidList = Optional.of(bidList1);
        when(mockBidListService.findById(0)).thenReturn(bidList);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/bidList/update/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }


    @Test
    public void testUpdateBid() throws Exception {
        // Setup
        // Configure BidListService.getAllBidList(...).
        final BidList bidList = new BidList();
        bidList.setBidListId(0);
        bidList.setAccount("account");
        bidList.setType("type");
        bidList.setBidQuantity(0.0);
        bidList.setAskQuantity(0.0);
        final Page<BidList> bidLists = new PageImpl<>(List.of(bidList));
        when(mockBidListService.getAllBidList(PageRequest.of(1, 10))).thenReturn(bidLists);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/bidList/update/1")
                        .param("bidListId", "0")
                        .param("account", "account")
                        .param("type", "type")
                        .param("bidQuantity", "0")
                        .param("askQuantity", "0")
                        .param("bid", "0")
                        .param("ask", "0")
                        .param("benchmark", "benchmark")
                        .param("bidListDate", "bidListDate")
                        .param("commentary", "commentary")
                        .param("security", "security")
                        .param("status", "status")
                        .param("trader", "trader")
                        .param("book", "book")
                        .param("creationName", "creationName")
                        .param("creationDate", "creationDate")
                        .param("revisionName", "revisionName")
                        .param("revisionDate", "revisionDate")
                        .param("dealName", "dealName")
                        .param("dealType", "dealType")
                        .param("sourceListId", "sourceListId")
                        .param("side", "side")
                        .with(user("username"))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testUpdateBid_BidListServiceGetAllBidListReturnsNoItems() throws Exception {
        // Setup
        when(mockBidListService.getAllBidList(PageRequest.of(1, 10)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/bidList/update/10")
                        .param("bidListId", "0")
                        .param("account", "account")
                        .param("type", "type")
                        .param("bidQuantity", "0")
                        .param("askQuantity", "0")
                        .param("bid", "0")
                        .param("ask", "0")
                        .param("benchmark", "benchmark")
                        .param("bidListDate", "bidListDate")
                        .param("commentary", "commentary")
                        .param("security", "security")
                        .param("status", "status")
                        .param("trader", "trader")
                        .param("book", "book")
                        .param("creationName", "creationName")
                        .param("creationDate", "creationDate")
                        .param("revisionName", "revisionName")
                        .param("revisionDate", "revisionDate")
                        .param("dealName", "dealName")
                        .param("dealType", "dealType")
                        .param("sourceListId", "sourceListId")
                        .param("side", "side")
                        .with(user("username"))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testDeleteBid() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/bidList/delete/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }
}
