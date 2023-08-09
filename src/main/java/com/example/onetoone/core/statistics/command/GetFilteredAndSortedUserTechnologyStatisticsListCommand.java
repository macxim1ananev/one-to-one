package com.example.onetoone.core.statistics.command;

import com.example.onetoone.core.service.common.GetFilteredAndSortedListCommand;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class GetFilteredAndSortedUserTechnologyStatisticsListCommand extends GetFilteredAndSortedListCommand {
}
