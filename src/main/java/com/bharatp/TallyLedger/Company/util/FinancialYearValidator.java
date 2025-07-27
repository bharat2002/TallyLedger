package com.bharatp.TallyLedger.Company.util;


import com.bharatp.TallyLedger.Company.dto.CompanyDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class FinancialYearValidator implements ConstraintValidator<ValidFinancialYear, CompanyDTO> {


    @Override
    public void initialize(ValidFinancialYear constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CompanyDTO companyDTO, ConstraintValidatorContext constraintValidatorContext) {
        if(null == companyDTO || companyDTO.getFinancialYearStart() == null ||companyDTO.getFinancialYearEnd() == null)
        {
            return true;
        }

        return !companyDTO.getFinancialYearStart().isAfter(companyDTO.getFinancialYearEnd());
    }
}
