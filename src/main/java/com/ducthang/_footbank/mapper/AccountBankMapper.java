package com.ducthang._footbank.mapper;

import com.ducthang._footbank.dto.AccountBankDTO;
import com.ducthang._footbank.entity.AccountBank;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountBankMapper{
    AccountBankDTO toDTO(AccountBank accountBank);
    AccountBank toEntity(AccountBankDTO accountBankDTO);
}
