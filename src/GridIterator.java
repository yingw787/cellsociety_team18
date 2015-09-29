import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class GridIterator implements
                    Iterator<Cell> {
        private List<List<Cell>> cells;
        private int i, j;

        public GridIterator(List<List<Cell>> c) {
            cells=c;
            i=0;
            j=0;
        }

        public boolean hasNext() {
            if (i>=cells.size()-1 && j>=cells.get(0).size()-1) {
                return false;
            }
            return true;
        }

        public Cell next() {
            if(this.hasNext()) {
                try {
                    Cell out = cells.get(i+1).get(j);
                    i++;
                    return out;
                }
                catch (Exception e) {
                    try {
                        Cell out = cells.get(0).get(j+1);
                        i=0;
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

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    