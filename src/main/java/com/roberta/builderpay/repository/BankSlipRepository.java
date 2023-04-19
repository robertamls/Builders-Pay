package com.roberta.builderpay.repository;

import com.roberta.builderpay.domain.BankSlip;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankSlipRepository extends MongoRepository<BankSlip, String> { }
