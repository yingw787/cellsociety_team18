//import java.util.Iterator;
//
//public class GridIterator implements
//                    Iterator<Integer> {
//        private int cursor;
//
//        public GridIterator() {
//            this.cursor = Range.this.start;
//        }
//
//        public boolean hasNext() {
//            return this.cursor < Range.this.end;
//        }
//
//        public Integer next() {
//            if(this.hasNext()) {
//                int current = cursor;
//                cursor ++;
//                return current;
//            }
//            throw new NoSuchElementException();
//        }
//
//        public void remove() {
//            throw new UnsupportedOperationException();
//        }
//    }
//    