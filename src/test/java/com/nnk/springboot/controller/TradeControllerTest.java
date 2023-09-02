package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(TradeController.class)
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService mockTradeService;

    @Test
    public void testPage() throws Exception {
        // Setup
        // Configure TradeService.getPage(...).
        final Trade trade = new Trade();
        trade.setTradeId(0);
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(0.0);
        trade.setSellQuantity(0.0);
        final Page<Trade> trades = new PageImpl<>(List.of(trade));
        when(mockTradeService.getPage(PageRequest.of(1, 10))).thenReturn(trades);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/trade/list")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }


    @Test
    public void testPage_TradeServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTradeService.getPage(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/trade/list")
                        .param("page", "1")
                        .param("size", "10")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testAddUser() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/trade/add")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testValidate() throws Exception {
        // Setup
        // Configure TradeService.getPage(...).
        final Trade trade = new Trade();
        trade.setTradeId(0);
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(0.0);
        trade.setSellQuantity(0.0);
        final Page<Trade> trades = new PageImpl<>(List.of(trade));
        when(mockTradeService.getPage(PageRequest.of(1, 10))).thenReturn(trades);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/trade/validate")
                        .param("tradeId", "0")
                        .param("account", "account")
                        .param("type", "type")
                        .param("buyQuantity", "0")
                        .param("sellQuantity", "0")
                        .param("buyPrice", "0")
                        .param("sellPrice", "0")
                        .param("benchmark", "benchmark")
                        .param("tradeDate", "tradeDate")
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
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testValidate_TradeServiceGetPageReturnsNoItems() throws Exception {
        // Setup
        when(mockTradeService.getPage(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/trade/validate")
                        .param("tradeId", "0")
                        .param("account", "account")
                        .param("type", "type")
                        .param("buyQuantity", "0")
                        .param("sellQuantity", "0")
                        .param("buyPrice", "0")
                        .param("sellPrice", "0")
                        .param("benchmark", "benchmark")
                        .param("tradeDate", "tradeDate")
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
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        // Setup
        // Configure TradeService.findById(...).
        final Trade trade1 = new Trade();
        trade1.setTradeId(0);
        trade1.setAccount("account");
        trade1.setType("type");
        trade1.setBuyQuantity(0.0);
        trade1.setSellQuantity(0.0);
        final Optional<Trade> trade = Optional.of(trade1);
        when(mockTradeService.findById(0)).thenReturn(trade);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/trade/update/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testShowUpdateForm_TradeServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockTradeService.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/trade/update/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testUpdateTrade() throws Exception {
        // Setup
        // Configure TradeService.getPage(...).
        final Trade trade = new Trade();
        trade.setTradeId(0);
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(0.0);
        trade.setSellQuantity(0.0);
        final Page<Trade> trades = new PageImpl<>(List.of(trade));
        when(mockTradeService.getPage(PageRequest.of(1, 10))).thenReturn(trades);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/trade/update/1")
                        .param("tradeId", "0")
                        .param("account", "account")
                        .param("type", "type")
                        .param("buyQuantity", "0")
                        .param("sellQuantity", "0")
                        .param("buyPrice", "0")
                        .param("sellPrice", "0")
                        .param("benchmark", "benchmark")
                        .param("tradeDate", "tradeDate")
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
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testUpdateTrade_TradeServiceGetPageReturnsNoItems() throws Exception {
        // Setup
        when(mockTradeService.getPage(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/trade/update/1")
                        .param("tradeId", "0")
                        .param("account", "account")
                        .param("type", "type")
                        .param("buyQuantity", "0")
                        .param("sellQuantity", "0")
                        .param("buyPrice", "0")
                        .param("sellPrice", "0")
                        .param("benchmark", "benchmark")
                        .param("tradeDate", "tradeDate")
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
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testDeleteTrade() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/trade/delete/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }
}
