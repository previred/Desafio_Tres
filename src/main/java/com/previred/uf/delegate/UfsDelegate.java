package com.previred.uf.delegate;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UfsDelegate {
    List<Uf> getUfs(Date inicio, Date fin);
}
