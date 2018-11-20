package com.cgwx.dao;

import com.cgwx.data.entity.PdmProducerInfoIdSeq;
import java.util.List;

public interface PdmProducerInfoIdSeqMapper {
    int insert(PdmProducerInfoIdSeq record);

    List<PdmProducerInfoIdSeq> selectAll();
}