package com.roberta.builderpay.controllers;

import com.roberta.builderpay.domain.BankSlip;
import com.roberta.builderpay.domain.dto.BankSlipDto;
import com.roberta.builderpay.payload.request.BankSlipRequest;
import com.roberta.builderpay.payload.request.CalculateRequest;
import com.roberta.builderpay.payload.response.BankSlipResponse;
import com.roberta.builderpay.services.AuthService;
import com.roberta.builderpay.services.BankSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BankSlipController {

    @Autowired
    private AuthService authService;

    @Autowired
    private BankSlipService bankSlipService;

    /**
     * (POST) Gera as multas e taxas do boleto
     *
     * @param calculateRequest Payload com os dados para autenticação, busca do boleto e geração das taxas
     * @return Dto com os dados atualizados de taxas e multas ou internal server error
     * @throws Exception
     */
    @PostMapping("/calculate")
    public ResponseEntity<?> calculateRates(@RequestBody CalculateRequest calculateRequest) {
        String token = authService.authenticateClient();

        if (token == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BankSlipRequest bankSlipRequest = new BankSlipRequest();
        bankSlipRequest.setCode(calculateRequest.getBar_code());
        BankSlipResponse response = bankSlipService.searchBankSlip(bankSlipRequest, token);

        if (response == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BankSlipDto dto = bankSlipService.generateRates(response, calculateRequest.getPayment_date());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * (GET) Pega todos os calculos realizados no banco
     *
     * @return lista de com as entidades persistidas
     * @throws Exception
     */
    @GetMapping("/all-calculations")
    public ResponseEntity<?> allCalculations(
            @RequestParam(
                value = "page",
                required = false,
                defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size) {
        try {
            Page<BankSlip> bankSlipList = bankSlipService.findAll(page, size);
            return new ResponseEntity<>(bankSlipList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
