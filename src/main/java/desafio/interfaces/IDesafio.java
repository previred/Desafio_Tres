package desafio.interfaces;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import java.util.List;
import java.util.logging.Logger;

public interface IDesafio {

    public Logger getLogger();

    public Ufs getRango();

    public void completeListUfs(Ufs ufs);

    public List<Uf> listUfs(Ufs ufs, String order);

    public String stringFromListUfs(List<Uf> listUfs);

    public String jsonFromListUfs(List<Uf> listUfs);
}
