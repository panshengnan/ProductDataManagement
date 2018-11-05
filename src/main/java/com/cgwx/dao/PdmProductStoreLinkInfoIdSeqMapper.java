package com.cgwx.dao;

import com.cgwx.data.entity.PdmProductStoreLinkInfoIdSeq;
import java.util.List;

public interface PdmProductStoreLinkInfoIdSeqMapper {
    int insert(PdmProductStoreLinkInfoIdSeq record);

    List<PdmProductStoreLinkInfoIdSeq> selectAll();
}