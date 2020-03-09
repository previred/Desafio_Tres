import com.previred.desafio.tres.uf.vo.Uf;

public class UfComparable extends Uf implements Comparable<UfComparable> {

	@Override
	public int compareTo(UfComparable o) {
		return this.getFecha().compareTo(o.getFecha());
	}

}
