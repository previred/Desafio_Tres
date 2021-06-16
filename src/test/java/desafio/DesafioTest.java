package desafio;

import com.previred.desafio.tres.uf.vo.Ufs;
import desafio.dao.Desafio;
import desafio.enumerators.ConstantesStr;
import desafio.interfaces.IDesafio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DesafioTest {

    private IDesafio desafio;

    @Before
    public void setup() {
        desafio = new Desafio();
    }

    @Test
    public void getRangoTest() {
        Assert.assertTrue(!desafio.getRango().getUfs().isEmpty());
    }

    @Test
    public void listUfsTest() {
        Ufs ufs = desafio.getRango();
        String order = ConstantesStr.ORDER_FECHA.toString();
        Assert.assertTrue(!desafio.listUfs(ufs, order).isEmpty());
    }

}
