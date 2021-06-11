package com.desafio3.valoresuf.service;

import com.previred.desafio.tres.uf.vo.Uf;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UfsServicio {

    private List<Uf> UFs;

    //Convert Set<Uf> to List<Uf>
    public void convertSetToList(Set<Uf> setUf) {
        UFs = new ArrayList<>(setUf);
    }

    public List<Uf> getUFs() {
        return UFs;
    }

    public void setUFs(List<Uf> UFs) {
        this.UFs = UFs;
    }
}
