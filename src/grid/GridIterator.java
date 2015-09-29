package grid;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import cell.Cell;


/**
 * The iterator for the GridOfCells class. Is not currently used due to the functionality bugs it
 * introduces.
 */
public class GridIterator implements
        Iterator<Cell> {
    private List<List<Cell>> cells;
    private int i, j;

    /**
     * Instantiates a new grid iterator.
     *
     * @param c the c
     */
    public GridIterator (List<List<Cell>> c) {
        cells = c;
        i = 0;
        j = 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext () {
        if (i >= cells.size() - 1 && j >= cells.get(0).size() - 1) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Iterator#next()
     */
    @Override
    public Cell next () {
        if (hasNext()) {
            try {
                Cell out = cells.get(i + 1).get(j);
                i++;
                return out;
            }
            catch (Exception e) {
                try {
                    Cell out = cells.get(0).get(j + 1);
                    i = 0;
                    j++;
                    return out;
                }
                catch (Exception ee) {
                    throw new NoSuchElementException();
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Iterator#remove()
     */
    @Override
    public void remove () {
        throw new UnsupportedOperationException();
    }
}
