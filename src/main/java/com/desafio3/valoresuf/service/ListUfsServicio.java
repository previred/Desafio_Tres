package com.desafio3.valoresuf.service;

import com.previred.desafio.tres.uf.vo.Uf;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ListUfsServicio {

    private List<Uf> ufs;

    //Convert Set<Uf> to List<Uf>
    public void convertSetToList(Set<Uf> setUf) {
        ufs = new ArrayList<>(setUf);
    }

    public List<Uf> getUfs() {
        return ufs;
    }

    public void setUfs(List<Uf> ufs) {
        this.ufs = ufs;
    }
}
