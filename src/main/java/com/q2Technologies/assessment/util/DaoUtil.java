package com.q2Technologies.assessment.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Sort.Direction;

public abstract class DaoUtil{
	
	protected <T, R> Path<R> getPath(Class<R> resultType, Path<T> root, String path) {
	    String[] pathElements = path.split("\\.");
	    Path<?> retVal = root;
	    for (String pathEl : pathElements) {
	        retVal = (Path<R>) retVal.get(pathEl);
	    }
	    return (Path<R>) retVal;
	}
	
	protected List<Predicate> getEqParamsCriteria(List<Predicate> criteria, CriteriaBuilder cb, Root<?> root,
				String[] eqColumns, Object[] eqValues){
		
		if(eqColumns != null && eqValues != null &&  eqColumns.length > 0 && eqValues.length > 0){
			Path path = null;
			if(eqColumns.length != eqValues.length){
				return criteria;
			}
			for(int i = 0;i < eqColumns.length; i++){
				path =  getPath(String.class, root, eqColumns[i]);
				criteria.add(cb.equal(path, eqValues[i]));
			}					
		
		}
		
		return criteria;
	}
	
	protected List<Predicate> getLikeParamsCriteria(List<Predicate> criteria, CriteriaBuilder cb, Root<?> root,
			String[] likeColumns, Object[] likeValues){
		
		if(likeColumns != null && likeValues != null && likeColumns.length > 0 && likeValues.length > 0){
			Path path = null;
			if(likeColumns.length != likeValues.length){
				return criteria;
			}
			for(int i = 0;i < likeColumns.length; i++){
				path =  getPath(String.class, root, likeColumns[i]);
				criteria.add(cb.like(path, "%"+likeValues[i]+"%"));
			}			
		}
	
		return criteria;
	}
	
	protected CriteriaQuery getOrder(Root<?> root, String direction, String orderBy, CriteriaQuery<?> cq, CriteriaBuilder cb){
		Path path =  getPath(String.class, root, orderBy);
		if(direction.equals(Direction.ASC.name())){			
			cq.orderBy(cb.asc(path));
		}else{
			cq.orderBy(cb.desc(path));
		}
		return cq;
	}
	
	public static void lazyInit(String[] need, Object elem) {
		if (need == null)
			return;
		for (int ii = 0; ii < need.length; ii++) {
			String[] strings = need[ii].split("[.]");
			Object obj = elem;
			Object objtmp = null;
			
			if (obj != null){
				if (obj.getClass().getSimpleName().equals(strings[0])) {
					for (int i = 1; i < strings.length; i++) {
						try {
							if (obj instanceof Collection) {
								Collection coll = (Collection) obj;
								ArrayList ls = new ArrayList(coll.size());
								for (Iterator iter = coll.iterator(); iter
										.hasNext();) {
									Object element = (Object) iter.next();
									
									Method method = element.getClass().getMethod(
											"get" + strings[i], null);
									objtmp = method.invoke(element, null);									
									
									ls.add(objtmp);
									// break;
								}
								obj = ls;
								if (obj == null)
									break;
							} else {
								Method method = obj.getClass().getMethod(
										"get" + strings[i], null);
								obj = method.invoke(obj, null);
								if (obj == null)
									break;
							}

							
							
							
//							System.out.println("get" + strings[i] + " = " + obj);
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	
	public static <T> List<T> map(Class<T> type, List<Object[]> records){
	   List<T> result = new LinkedList<T>();
	   for(Object[] record : records){
	      result.add(map(type, record));
	   }
	   return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getResultList(Query query, Class<T> type){
		List<Object[]> records = query.getResultList();
		return map(type, records);
	}
		
	public static <T> T map(Class<T> type, Object[] tuple){
	   List<Class<?>> tupleTypes = new ArrayList<Class<?>>();
	   for(Object field : tuple){
	      tupleTypes.add(field.getClass());
	   }
	   try {
	      Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
	      return ctor.newInstance(tuple);
	   } catch (Exception e) {
	      throw new RuntimeException(e);
	   }
	}

}
