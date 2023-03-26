package com.example.onetoone.core.feedback.commands.statistics;

import com.example.onetoone.core.service.common.GetFilteredAndSortedListCommand;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class GetFilteredAndSortedUserStatisticsListCommand extends GetFilteredAndSortedListCommand {
}
