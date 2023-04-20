package com.roberta.builderpay.services;

import com.roberta.builderpay.domain.BankSlip;
import com.roberta.builderpay.domain.dto.BankSlipDto;
import com.roberta.builderpay.payload.request.BankSlipRequest;
import com.roberta.builderpay.payload.response.BankSlipResponse;
import com.roberta.builderpay.repository.BankSlipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.roberta.builderpay.utils.FormatDouble.formatDoubles;
import static com.roberta.builderpay.utils.ValidationBankSlip.*;




@Service
public class BankSlipService {
    @Value("${url.bankslip}")
    private String url;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BankSlipRepository bankSlipRepository;

    /**
     * Salva os dados no banco
     * @param bankSlip entidade a ser persistida
     */
    public void save(BankSlip bankSlip){
        bankSlipRepository.save(bankSlip);
    }

    /**
     * Pega o histórico de calculos no banco
     * @return dados salvos
     */
    public Page<BankSlip> findAll(int page, int size){
        Sort sort = Sort.by("due_date").ascending();
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                sort);

        return bankSlipRepository.findAll(pageRequest);
    }

    /**
     * Consome a API de boletos para obtenção dos mesmos
     *
     * @param bankSlipRequest Payload com o código de barras
     * @param token           token obtido pela api de autenticação
     * @return Um BankSlipResponse com os dados do boleto
     */
    public BankSlipResponse searchBankSlip(BankSlipRequest bankSlipRequest, String token) {

        bankSlipValidate(bankSlipRequest.getCode());

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<BankSlipRequest> request = new HttpEntity<>(bankSlipRequest, headers);
        return restTemplate.postForObject(url, request, BankSlipResponse.class);
    }

    /**
     * Gera as taxas e atualiza os valores
     *
     * @param bankSlipResponse Dados do boleto obtido pela api de boletos
     * @param payment_date     Data de pagamento do boleto recebido no CalculateRequest
     * @return Dto com os dados atualizados
     */
    public BankSlipDto generateRates(BankSlipResponse bankSlipResponse, String payment_date) {

        bankSlipType(bankSlipResponse.getType());

        long expireDays = bankSlipExpired(bankSlipResponse.getDue_date(), payment_date);

        double interestAmount = interestCalculator(expireDays, bankSlipResponse.getAmount());

        double finesAmount = finesCalculator(bankSlipResponse.getAmount());

        // Montante atualizado
        double amountUpdated = bankSlipResponse.getAmount() + interestAmount + finesAmount;

        BankSlip bankSlip = new BankSlip();
        bankSlip.setOriginal_amount(bankSlipResponse.getAmount());
        bankSlip.setAmount(amountUpdated);
        bankSlip.setDue_date(bankSlipResponse.getDue_date());
        bankSlip.setPayment_date(payment_date);
        bankSlip.setInterest_amount_calculated(interestAmount);
        bankSlip.setFine_amount_calculated(finesAmount);

        save(bankSlip);

        return mapper.map(bankSlip, BankSlipDto.class);
    }

    /**
     * Calcula os juros por dia
     *
     * @param expireDays dias vencidos após validação do vencimento
     * @param amount     montante obtido por bankSlipResponse
     * @return total de juros
     */
    public double interestCalculator(long expireDays, double amount) {
        double interest = 0.0033;
        return formatDoubles(amount * interest * expireDays);
    }

    /**
     * Calcula a multa por atraso
     *
     * @param amount montante obtido por bankSlipResponse
     * @return retorna a multa
     */
    public double finesCalculator(double amount) {
        return formatDoubles(amount * 0.02);
    }
}
