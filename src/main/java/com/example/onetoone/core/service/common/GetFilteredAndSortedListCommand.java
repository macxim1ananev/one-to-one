package com.example.onetoone.core.service.common;

import com.example.onetoone.core.service.command_bus.Command;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@SuperBuilder
public abstract class GetFilteredAndSortedListCommand implements Command {
    Integer page;
    Integer size;
    String sort;
    Set<SearchCriteria> criteria;
}
