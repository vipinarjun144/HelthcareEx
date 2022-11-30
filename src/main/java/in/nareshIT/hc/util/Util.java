package in.nareshIT.hc.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import in.nareshIT.hc.entity.Doctor;

public interface Util {

	public static Map<Long,String> convertListToMap(List<Object[]> list){

		Map<Long,String> map=list
				.stream()
				.collect(Collectors.toMap(
						ob->Long.valueOf(ob[0].toString()),
						ob->ob[1].toString())
						);

		return map ;
	}

	public static Map<Long,String> convertListToIndex(List<Object[]> list){
		Map<Long,String> map=list
				.stream()
				.collect(Collectors.toMap(
						ob->Long.valueOf(ob[0].toString()),
						ob->ob[1].toString()+" "+ob[2].toString())
						);

		return map ;
	}

	public static List<Doctor> convertList(List<Object[]> list){
		List<Doctor> doclist= new ArrayList<>();
		for(Object[] ob:list) {
			for(Object doc:ob) {
				Doctor d=(Doctor)doc;
				doclist.add(d);
			}
		}
		return doclist;
	}

}
