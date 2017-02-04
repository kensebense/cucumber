package org.kense.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    List<Long> partIds;

    public List<Long> getPartIds() {
        if (partIds == null) {
            partIds = new ArrayList<>();
        }
        return partIds;
    }
}
