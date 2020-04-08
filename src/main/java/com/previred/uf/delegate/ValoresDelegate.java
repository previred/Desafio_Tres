package com.previred.uf.delegate;

import com.previred.desafio.tres.uf.vo.Ufs;
import org.springframework.stereotype.Repository;

@Repository
public interface ValoresDelegate {
    Ufs getRango() throws Exception;
}
