package zyx.existent.utils.misc;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListUtils {
	  private static final Iterator<Object> EMPTY_MODIFIABLE_ITERATOR =
		      new Iterator<Object>() {
		        @Override
		        public boolean hasNext() {
		          return false;
		        }

		        @Override
		        public Object next() {
		          throw new NoSuchElementException();
		        }

		        @Override
		        public void remove() {
		        }
		      };
		      static <T> Iterator<T> emptyModifiableIterator() {
		    	    return (Iterator<T>) EMPTY_MODIFIABLE_ITERATOR;
		    	  }
	  public static <T> Iterator<T> cycle(final Iterable<T> iterable) {
		    return new Iterator<T>() {
		      Iterator<T> iterator = emptyModifiableIterator();

		      @Override
		      public boolean hasNext() {
		        /*
		         * Don't store a new Iterator until we know the user can't remove() the last returned
		         * element anymore. Otherwise, when we remove from the old iterator, we may be invalidating
		         * the new one. The result is a ConcurrentModificationException or other bad behavior.
		         *
		         * (If we decide that we really, really hate allocating two Iterators per cycle instead of
		         * one, we can optimistically store the new Iterator and then be willing to throw it out if
		         * the user calls remove().)
		         */
		        return iterator.hasNext() || iterable.iterator().hasNext();
		      }

		      @Override
		      public T next() {
		        if (!iterator.hasNext()) {
		          iterator = iterable.iterator();
		          if (!iterator.hasNext()) {
		            throw new NoSuchElementException();
		          }
		        }
		        return iterator.next();
		      }

		      @Override
		      public void remove() {
		        iterator.remove();
		      }
		    };
		  }
}
