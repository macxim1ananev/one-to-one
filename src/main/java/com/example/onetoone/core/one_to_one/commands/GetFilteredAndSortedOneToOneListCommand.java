package com.example.onetoone.core.one_to_one.commands;

import com.example.onetoone.core.service.common.GetFilteredAndSortedListCommand;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class GetFilteredAndSortedOneToOneListCommand  extends GetFilteredAndSortedListCommand {
}
