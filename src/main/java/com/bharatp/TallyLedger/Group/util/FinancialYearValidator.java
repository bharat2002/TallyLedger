package com.bharatp.TallyLedger.Group.util;


import com.bharatp.TallyLedger.Group.dto.GroupDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class FinancialYearValidator implements ConstraintValidator<ValidFinancialYear, GroupDTO> {


    @Override
    public void initialize(ValidFinancialYear constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(GroupDTO companyDTO, ConstraintValidatorContext constraintValidatorContext) {
        if(null == companyDTO || companyDTO.getFinancialYearStart() == null ||companyDTO.getFinancialYearEnd() == null)
        {
            return true;
        }

        return !companyDTO.getFinancialYearStart().isAfter(companyDTO.getFinancialYearEnd());
    }
}
