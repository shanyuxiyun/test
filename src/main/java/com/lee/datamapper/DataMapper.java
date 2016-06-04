package com.lee.datamapper;

import java.util.List;

public interface DataMapper<S,D> {
	
	D mapper(S s);
	
	List<D> mapper(List<S> s);
	
}
