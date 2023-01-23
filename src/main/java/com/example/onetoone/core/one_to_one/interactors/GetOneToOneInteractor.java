package com.example.onetoone.core.one_to_one.interactors;

import com.example.onetoone.core.one_to_one.OneToOneMapper;
import com.example.onetoone.core.one_to_one.commands.GetOneToOneCommand;
import com.example.onetoone.core.one_to_one.results.OneToOneResult;
import com.example.onetoone.core.service.common.Interactor;
import com.example.onetoone.core.service.error.ServiceException;
import com.example.onetoone.core.service.interfaces.OneToOnes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetOneToOneInteractor implements Interactor<GetOneToOneCommand, OneToOneResult> {

    private final OneToOnes oneToOnes;
    private final OneToOneMapper mapper;

    @Override
    public OneToOneResult execute(GetOneToOneCommand command) {
        log.info("Executing command {}", command);

        var entity = oneToOnes.get(command.getId())
                .orElseThrow(()-> new ServiceException(ServiceException.Exception.ONE_TO_ONE_NOT_FOUND));
        return mapper.toResult(entity);
    }
}
